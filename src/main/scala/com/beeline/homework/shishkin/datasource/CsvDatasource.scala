package com.beeline.homework.shishkin.datasource

trait CsvDatasource {
  val Format = "csv"
  val DefaultDelimiter = "\t"
  val DefaultHeaderOption = "false"
  val DefaultInferSchemaOption = "true"
}
