package com.beeline.homework.shishkin.fixture

import com.beeline.homework.shishkin.domain.Product
import com.beeline.homework.shishkin.fixture.BasicFixture.{ProductId1, ProductId2}

object ProductFixture {

  val Product1 = Product(ProductId1, "Apple iPhone 7", 45990, 50000)
  val Product2 = Product(ProductId2, "Apple iPhone 8", 49990, 100000)

}