package com.jchunga.salesapp.core.utils

import com.jchunga.salesapp.data.entity.PointSale
import com.jchunga.salesapp.data.entity.Product
import com.jchunga.salesapp.data.entity.User


sealed class Screen(val route: String) {
    object Login : Screen("Login Screen")
    object Home : Screen("Home Screen")
    object Splash : Screen("Splash Screen")
    object Support : Screen("Support Screen")
    object PointSale : Screen("PointSale Screen")
    object Map : Screen("Map Screen")
    object Visit : Screen("Visit Screen")
    object Product : Screen("Product Screen")
}

// Create a User List
val usersModel: List<User> = listOf(
    User( name = "admin", email = "admin@admin.com", username = "admin", password = "admin"),
    User( name = "Luis Advincula", email = "ladvincula@xplora.com", username = "ladvincula", password = "ladvincula"),
    User( name = "Bill Gates", email = "bgates@xplora.com", username = "bgates", password = "bgates")
)

//Create some Point of Sale
val pointSalesModel: List<PointSale> = listOf(
    PointSale(name = "YOSLY AMALI AGUILAR", code = 409183, address = "REAL SN Urb: ESQ. 10 NOVIEMBRE", lat = "-12.06009180063092", longi = "-76.99888653133722"),
    PointSale(name = "METRO ALFONSO UGARTE", code = 409184, address = "AV. ALFONSO UGARTE 740", lat = "-12.048527143138703", longi = "-77.04283147551946"),
    PointSale(name = "TOTTUS ZORRITO", code = 409185, address = "AV. COLONIAL 1520", lat = "-12.048065378088255", longi = "-77.05880615039264"),
)


//Create some Products
val productsModel: List<Product>
    get() = listOf(
        Product(name = "aceite Cil    x 12 Bot", cost = 45.20, rutaCost = 35.40, stock = 100, idPointSale = 1),
        Product(name = "aceite Sao    x 12 Bot", cost = 44.20, rutaCost = 44.40, stock = 100, idPointSale = 1),
        Product(name = "aceite Primor x 12 Bot", cost = 43.20, rutaCost = 43.40, stock = 100, idPointSale = 1),
        Product(name = "aceite Light  x 12 Bot", cost = 42.20, rutaCost = 35.40, stock = 100, idPointSale = 1),
        Product(name = "aceite Faraon x 12 Bot", cost = 41.20, rutaCost = 35.40, stock = 100, idPointSale = 1),

        Product(name = "Arroz Faraon Saco 60 Kg", cost = 45.20, rutaCost = 35.40, stock = 100, idPointSale = 2),
        Product(name = "Arroz Luisina Saco 60 Kg", cost = 44.20, rutaCost = 44.40, stock = 100, idPointSale = 2),
        Product(name = "Arroz Rompeolla 60 Kg", cost = 43.20, rutaCost = 43.40, stock = 100, idPointSale = 2),
        Product(name = "Arroz Rompe  Saco 60 Kg", cost = 42.20, rutaCost = 35.40, stock = 100, idPointSale = 2),
        Product(name = "Arroz Eduardo Saco 60 Kg", cost = 41.20, rutaCost = 35.40, stock = 100, idPointSale = 2),

        Product(name = "Gaseosa Inka Cola 3 Lt", cost = 45.20, rutaCost = 35.40, stock = 100, idPointSale = 3),
        Product(name = "Gaseosa Cola Cola 3 Lt", cost = 44.20, rutaCost = 44.40, stock = 100, idPointSale = 3),
        Product(name = "Gaseosa Sprite 3 Lt", cost = 43.20, rutaCost = 43.40, stock = 100, idPointSale = 3),
        Product(name = "Gaseosa SevenUp 3 Lt", cost = 42.20, rutaCost = 35.40, stock = 100, idPointSale = 3),
        Product(name = "Gaseosa Fanta 3 Lt", cost = 41.20, rutaCost = 35.40, stock = 100, idPointSale = 3),
    )