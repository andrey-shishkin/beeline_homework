package com.beeline.homework.shishkin.business

import com.beeline.homework.shishkin.domain.MostPopularProduct
import com.beeline.homework.shishkin.fixture.BasicFixture.{CustomerId1, CustomerId2, ProductId1}
import com.beeline.homework.shishkin.fixture.MostPopularProductFixture.defaultMostPopularProductSeq
import com.beeline.homework.shishkin.fixture.OrderFixture.defaultOrderSeq
import com.beeline.homework.shishkin.fixture.{CustomerFixture, MostPopularProductFixture, ProductFixture}
import com.beeline.homework.shishkin.spark.SparkSessionWrapper
import com.github.mrpowers.spark.daria.sql.transformations._
import com.github.mrpowers.spark.fast.tests.DatasetComparer
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class BusinessLogicTestSuite extends AnyFlatSpec with Matchers with SparkSessionWrapper with DatasetComparer {

  spark.sparkContext.setLogLevel("ERROR")

  import spark.implicits._

  // Testing getMostPopularProducts method
  "Test" should "return most popular products ignoring status" in {

    // Given
    val orderDS = defaultOrderSeq.toDS

    // When
    val expected = Seq(
      MostPopularProduct(CustomerId1, ProductId1, 18),
      MostPopularProduct(CustomerId2, ProductId1, 20)
    ).toDS

    val actual = getMostPopularProducts(orderDS, filterDelivered = false)

    // Then
    assertSmallDatasetEquality(actual, expected, orderedComparison = false, ignoreNullable = true)
  }

  "Test" should "return most popular products using only delivered orders" in {

    // Given
    val orderDS = defaultOrderSeq.toDS

    // When
    val expected = defaultMostPopularProductSeq.toDS

    val actual = getMostPopularProducts(orderDS, filterDelivered = true)

    // Then
    assertSmallDatasetEquality(actual, expected, orderedComparison = false, ignoreNullable = true)
  }

  // Testing joinOrdersToCustomersAndProducts method
  "Test" should "return most popular products with details" in {

    // Given
    val mostPopularDS = defaultMostPopularProductSeq.toDS

    val customerDS = Seq(
      CustomerFixture.Customer1,
      CustomerFixture.Customer2,
    ).toDS

    val productDS = Seq(
      ProductFixture.Product1,
      ProductFixture.Product2
    ).toDS

    // When
    val expected = MostPopularProductFixture.defaultMostPopularProductWithDetailsSeq.toDS

    val actual = joinOrdersToCustomersAndProducts(mostPopularDS, customerDS, productDS, onlyActiveCustomers = false)

    // Then
    assertSmallDatasetEquality(actual.toDF.transform(sortColumns()), expected.toDF.transform(sortColumns()), orderedComparison = false, ignoreNullable = true)
  }

  "Test" should "return most popular products with details using only active customers" in {

    // Given
    val mostPopularDS = defaultMostPopularProductSeq.toDS

    val customerDS = Seq(
      CustomerFixture.Customer1,
      CustomerFixture.Customer2,
    ).toDS

    val productDS = Seq(
      ProductFixture.Product1,
      ProductFixture.Product2
    ).toDS

    // When
    val expected = MostPopularProductFixture.defaultMostPopularProductWithDetailsSeq.slice(0, 1).toDS

    val actual = joinOrdersToCustomersAndProducts(mostPopularDS, customerDS, productDS, onlyActiveCustomers = true)

    // Then
    assertSmallDatasetEquality(actual.toDF.transform(sortColumns()), expected.toDF.transform(sortColumns()), orderedComparison = false, ignoreNullable = true)
  }
}
