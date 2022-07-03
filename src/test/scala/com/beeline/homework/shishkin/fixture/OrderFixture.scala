package com.beeline.homework.shishkin.fixture

import com.beeline.homework.shishkin.domain.Order
import com.beeline.homework.shishkin.fixture.BasicFixture._

import java.sql.Date

object OrderFixture {

  val defaultOrderSeq = Seq(
    Order(CustomerId1, OrderId1, ProductId1, 5, Date.valueOf("2022-01-01"), DeliveredOrderStatus),
    Order(CustomerId1, OrderId1, ProductId2, 3, Date.valueOf("2022-01-02"), DeliveredOrderStatus),
    Order(CustomerId1, OrderId2, ProductId1, 8, Date.valueOf("2022-01-03"), CanceledOrderStatus),
    Order(CustomerId1, OrderId3, ProductId1, 5, Date.valueOf("2022-01-02"), DeliveredOrderStatus),

    Order(CustomerId2, OrderId1, ProductId1, 5, Date.valueOf("2022-01-01"), DeliveredOrderStatus),
    Order(CustomerId2, OrderId2, ProductId2, 3, Date.valueOf("2022-01-02"), DeliveredOrderStatus),
    Order(CustomerId2, OrderId3, ProductId1, 10, Date.valueOf("2022-01-03"), DeliveredOrderStatus),
    Order(CustomerId2, OrderId4, ProductId1, 5, Date.valueOf("2022-01-02"), DeliveredOrderStatus)
  )

}
