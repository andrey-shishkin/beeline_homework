package com.beeline.homework.shishkin

import com.beeline.homework.shishkin.domain._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, row_number, sum}
import org.apache.spark.sql.{Dataset, SparkSession}

package object business {

  /**
   * This method returns a Dataset of customer's most popular product.
   *
   * @param orderDS         Source Dataset of orders.
   * @param filterDelivered If this flag is set to `true`, then most popular product will be calculated only
   *                        for `delivered` orders.
   * @param spark           Spark session implicit parameter
   * @return Calculated Dataset of customer's most popular product.
   * */
  def getMostPopularProducts(orderDS: Dataset[Order], filterDelivered: Boolean)(implicit spark: SparkSession): Dataset[MostPopularProduct] = {

    import spark.implicits._

    // Filtering out non delivered orders if needed
    val orders = if (filterDelivered) {
      orderDS
        .filter(col("status") === "delivered")
    } else {
      orderDS
    }

    // First we group rows by customerId and productId, calculate sum for such groups.
    // Then we use window function row_number. The most popular product for each customer will have a row_number == 1
    // Then we'll just get all rows with row_number == 1
    val winSpec = Window.partitionBy("customerId").orderBy("productId")
    orders.groupBy("customerId", "productId")
      .agg(sum("numberOfProduct").alias("totalProductAmount"))
      .withColumn("row_number", row_number().over(winSpec))
      .select("customerId", "productId", "totalProductAmount")
      .where(col("row_number") === 1)
      .as[MostPopularProduct]
  }

  /**
   * This method returns a Dataset of customer's most popular
   * product with detailed information like product name and customer name.
   *
   * @param mostPopularProduct  Source Dataset of customer's most popular product.
   * @param customerDataset     Source Dataset with customer information
   * @param productDataset      Source Dataset with product information
   * @param onlyActiveCustomers If this flag is set to `true`, then the only customers with `active` status will be
   *                            taken into account during calculation.
   * @param spark               Spark session implicit parameter
   * @return Calculated Dataset of customer's most popular product with detailed information.
   * */
  def joinOrdersToCustomersAndProducts(mostPopularProduct: Dataset[MostPopularProduct],
                                       customerDataset: Dataset[Customer],
                                       productDataset: Dataset[Product], onlyActiveCustomers: Boolean)
                                      (implicit spark: SparkSession): Dataset[MostPopularProductWithDetails] = {
    import spark.implicits._

    val result = mostPopularProduct
      .join(
        customerDataset.select(
          col("id"),
          col("name").alias("customerName"),
          col("status").alias("customerStatus")),
        mostPopularProduct("customerId") === customerDataset("id")
      )
      .join(
        productDataset.select(
          col("id"),
          col("name").alias("productName")),
        mostPopularProduct("productId") === productDataset("id")
      )
      .select("customerId", "customerName", "productId", "productName", "totalProductAmount", "customerStatus")

    (if (onlyActiveCustomers) {
      result
        .filter(col("customerStatus") === "active")
    } else {
      result
    })
      .orderBy("customerId")
      .as[MostPopularProductWithDetails]
  }

  /**
   * This method saves a Dataset to CSV format in a single file with header. Overwrites data in a target folder.
   *
   * @param sourceDF     Source Dataset to save
   * @param path         Path to the target folder
   * @param targetFolder Target folder where csv file will be saved
   * */
  def saveToFile(sourceDF: Dataset[MostPopularProductWithDetails], path: String, targetFolder: String): Unit = {
    sourceDF
      .coalesce(1)
      .write
      .mode("overwrite")
      .option("header", "true")
      .csv(path + targetFolder)
  }

}
