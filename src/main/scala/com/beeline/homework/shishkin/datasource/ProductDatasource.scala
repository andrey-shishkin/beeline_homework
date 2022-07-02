package com.beeline.homework.shishkin.datasource

import com.beeline.homework.shishkin.domain.Product
import com.beeline.homework.shishkin.spark.SparkSessionWrapper
import org.apache.spark.sql.{Dataset, Encoders}

class ProductDatasource(path: String) extends SparkSessionWrapper with CsvDatasource {

  import spark.implicits._

  lazy val productDataset: Dataset[Product] = {

    val productSchema = Encoders.product[Product].schema

    spark
      .read
      .format(Format)
      .option("header", DefaultHeaderOption)
      .option("delimiter", DefaultDelimiter)
      .schema(productSchema)
      .load(path)
      .as[Product]
  }

}
