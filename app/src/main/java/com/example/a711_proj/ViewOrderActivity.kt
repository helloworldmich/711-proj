package com.example.a711_proj

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ViewOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_order)
        getSummary()
    }

    fun getSummary (){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val pizzaName = sharedPref.getString("pizza name", "")
        val size = sharedPref.getString("size", "")
//        val toppings = sharedPref.getString("phoneModel", "")
        val totalPrice = sharedPref.getString("total", "")
        val smashed_potato = sharedPref.getString("smashed potato", "")
        val garlic_bread = sharedPref.getString("garlic bread", "")
        val house_salad = sharedPref.getString("house salad", "")
        val mushroom_salad = sharedPref.getString("mushroom salad", "")
        val no_side_dishes = sharedPref.getString("no side dishes", "")
        val sideDishes = "$no_side_dishes $smashed_potato \n $garlic_bread \n $house_salad \n $mushroom_salad \n"

        // get contact information
        val name = sharedPref.getString("name", "")
        val address = sharedPref.getString("address", "")
        val city = sharedPref.getString("city", "")
        val postalCode = sharedPref.getString("postalCode", "")
        val phoneNumber = sharedPref.getString("telephone", "")
        // get payment information
        val creditCard = sharedPref.getString("cardNumber", "")
        val cardType = sharedPref.getString("cardType", "")
        val expirationDate = sharedPref.getString("cardExpiry", "")
        val cvv = sharedPref.getString("cardCvv", "")


        //display chosen food
        findViewById<TextView>(R.id.pizzaName).text = pizzaName
        findViewById<TextView>(R.id.size).text = size
//        findViewById<TextView>(R.id.toppings).text = toppings
        findViewById<TextView>(R.id.total).text = totalPrice
        findViewById<TextView>(R.id.sideDishes).text = sideDishes

//        findViewById<TextView>(R.id.smashedPotato).text = smashed_potato   // snake case in order activity only
//        findViewById<TextView>(R.id.garlicBread).text = garlic_bread
//        findViewById<TextView>(R.id.houseSalad).text = house_salad
//        findViewById<TextView>(R.id.mushroomSalad).text = mushroom_salad
//        findViewById<TextView>(R.id.no_side_dishes).text = no_side_dishes

// display delivery info
        findViewById<TextView>(R.id.name).text = name
        findViewById<TextView>(R.id.address).text = address
        findViewById<TextView>(R.id.city).text = city
        findViewById<TextView>(R.id.postalCode).text = postalCode
        findViewById<TextView>(R.id.telephone).text = phoneNumber
        findViewById<TextView>(R.id.cardNumber).text = creditCard
        findViewById<TextView>(R.id.cardType).text = cardType
        findViewById<TextView>(R.id.cardExpiry).text = expirationDate
        findViewById<TextView>(R.id.cardCVV).text = cvv


    }
}