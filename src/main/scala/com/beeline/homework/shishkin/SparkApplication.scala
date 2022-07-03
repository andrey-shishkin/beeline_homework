package com.beeline.homework.shishkin

import com.beeline.homework.shishkin.business.{getMostPopularProducts, joinOrdersToCustomersAndProducts, saveToFile}
import com.beeline.homework.shishkin.datasource.{CustomerDatasource, OrderDatasource, ProductDatasource}
import com.beeline.homework.shishkin.spark.SparkSessionWrapper

object SparkApplication extends App with SparkSessionWrapper {

  val Path = "src/main/resources/data/"
  val CustomerPath = Path + "customer.csv"
  val OrderPath = Path + "order.csv"
  val ProductPath = Path + "product.csv"
  val OnlyDeliveredOrders = true
  val OnlyActiveCustomers = true

  val customerDatasource = new CustomerDatasource(CustomerPath)
  val orderDatasource = new OrderDatasource(OrderPath)
  val productDatasource = new ProductDatasource(ProductPath)
  val orderDS = orderDatasource.orderDataset
  val customerDS = customerDatasource.customerDataset
  val productDS = productDatasource.productDataset

  val mostPopularDS = getMostPopularProducts(orderDS, OnlyDeliveredOrders)

  val result = joinOrdersToCustomersAndProducts(mostPopularDS, customerDS, productDS, OnlyActiveCustomers)

  result.show

  saveToFile(result, Path, "result")

}
