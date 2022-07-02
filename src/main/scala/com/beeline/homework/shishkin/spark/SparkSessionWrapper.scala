package com.beeline.homework.shishkin.spark

import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {

  implicit lazy val spark: SparkSession = {
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("Beeline Homework")
      .getOrCreate

    val ss = spark.sparkContext
    ss.setLogLevel("ERROR")
    spark
  }
}
