package com.beeline.homework.shishkin.datasource

import com.beeline.homework.shishkin.domain.Customer
import com.beeline.homework.shishkin.spark.SparkSessionWrapper
import org.apache.spark.sql.{Dataset, Encoders}

class CustomerDatasource(path: String) extends SparkSessionWrapper with CsvDatasource {

  import spark.implicits._

  lazy val customerDataset: Dataset[Customer] = {

    val customerSchema = Encoders.product[Customer].schema

    spark
      .read
      .format(Format)
      .option("header", DefaultHeaderOption)
      .option("delimiter", DefaultDelimiter)
      .schema(customerSchema)
      .load(path)
      .as[Customer]
  }

}
