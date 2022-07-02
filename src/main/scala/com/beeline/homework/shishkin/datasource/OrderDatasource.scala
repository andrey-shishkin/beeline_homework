package com.beeline.homework.shishkin.datasource

import com.beeline.homework.shishkin.domain.Order
import com.beeline.homework.shishkin.spark.SparkSessionWrapper
import org.apache.spark.sql.{Dataset, Encoders}

class OrderDatasource(path: String) extends SparkSessionWrapper with CsvDatasource {

  import spark.implicits._

  lazy val orderDataset: Dataset[Order] = {
    val orderSchema = Encoders.product[Order].schema

    spark
      .read
      .format(Format)
      .option("header", DefaultHeaderOption)
      .option("delimiter", DefaultDelimiter)
      .schema(orderSchema)
      .load(path)
      .as[Order]
  }

}
