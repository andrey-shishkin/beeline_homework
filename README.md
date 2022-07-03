### beeline_homework

##Входные данные
#Customer.csv – информация о клиентах
Имя поля: формат

id: Int,
name: String,
email: String,
joinDate: Date,
status: String

#Product.csv – информация о товарах

id: Int
name: String
price: Double
numberOfProducts: Int

#Order.csv – информация о заказах

customerID: Int
orderID: Int
productID: Int
numberOfProduct: Int – кол-во товара в заказе
orderDate: Date
status: String

Необходимо определить самый популярный продукт у клиента
Итоговое множество содержит поля: customer.name, product.name
Результат записать в файл
