package com.beeline.homework.shishkin.fixture

import com.beeline.homework.shishkin.domain.Customer
import com.beeline.homework.shishkin.fixture.BasicFixture.{ActiveCustomerStatus, BannedCustomerStatus, CustomerId1, CustomerId2}

import java.sql.Date

object CustomerFixture {

  val Customer1 = Customer(CustomerId1, "John", "john.cena@gmail.com", Date.valueOf("2018-01-01"), ActiveCustomerStatus)
  val Customer2 = Customer(CustomerId2, "Vasili", "phather.russia@gmail.com", Date.valueOf("2017-04-04"), BannedCustomerStatus)

}