package com.beeline.homework.shishkin

import java.sql.Date

package object domain {

  case class Customer(id: Int, name: String, email: String, joinDate: Date, status: String)

  case class Product(id: Int, name: String, price: Double, numberOfProducts: Int)

  case class Order(customerId: Int, orderId: Int, productId: Int, numberOfProduct: Int, orderDate: Date, status: String)

  case class MostPopularProduct(customerId: Int, productId: Int, totalProductAmount: Long)

  case class MostPopularProductWithDetails(customerId: Int, customerName: String, customerStatus: String, productId: Int, productName: String,  totalProductAmount: Long)
}
