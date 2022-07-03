package com.beeline.homework.shishkin.fixture

import com.beeline.homework.shishkin.domain.{MostPopularProduct, MostPopularProductWithDetails}
import com.beeline.homework.shishkin.fixture.BasicFixture._

object MostPopularProductFixture {

  val defaultMostPopularProductSeq = Seq(
    MostPopularProduct(CustomerId1, ProductId1, 10),
    MostPopularProduct(CustomerId2, ProductId1, 20)
  )

  val defaultMostPopularProductWithDetailsSeq = Seq(
    MostPopularProductWithDetails(
      CustomerFixture.Customer1.id,
      CustomerFixture.Customer1.name,
      CustomerFixture.Customer1.status,
      ProductFixture.Product1.id,
      ProductFixture.Product1.name,
      10),
    MostPopularProductWithDetails(
      CustomerFixture.Customer2.id,
      CustomerFixture.Customer2.name,
      CustomerFixture.Customer2.status,
      ProductFixture.Product1.id,
      ProductFixture.Product1.name,
      20)
  )

}
