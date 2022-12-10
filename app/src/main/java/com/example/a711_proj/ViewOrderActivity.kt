package com.example.a711_proj

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.io.IOException
import java.util.logging.Logger

class ViewOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_order)
        getSummary()
    }
    var name = ""
    var address = " "
    var city = ""
    var postalCode = ""
    var phoneNumber = ""
    var creditCard = ""
    var cardType = ""
    var expirationDate = ""
    var cvv = ""
    fun getSummary (){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val pizzaName = sharedPref.getString("pizza name", "")
        val sizeChosen = sharedPref.getString("size", "")
//        val toppings = sharedPref.getString("phoneModel", "")
        val totalPrice = sharedPref.getString("total", "")
        val smashed_potato = sharedPref.getString("smashed potato", "")
        val garlic_bread = sharedPref.getString("garlic bread", "")
        val house_salad = sharedPref.getString("house salad", "")
        val mushroom_salad = sharedPref.getString("mushroom salad", "")
        val no_side_dishes = sharedPref.getString("no side dishes", "")
        val sideDishes = "$no_side_dishes $smashed_potato \n $garlic_bread \n $house_salad \n $mushroom_salad \n"

        println(pizzaName)

        println(sizeChosen)
        // get contact information
         name = sharedPref.getString("name", "").toString()
         address = sharedPref.getString("address", "").toString()
         city = sharedPref.getString("city", "").toString()
         postalCode = sharedPref.getString("postalCode", "").toString()
         phoneNumber = sharedPref.getString("telephone", "").toString()
        // get payment information
         creditCard = sharedPref.getString("cardNumber", "").toString()
         cardType = sharedPref.getString("cardType", "").toString()
         expirationDate = sharedPref.getString("cardExpiry", "").toString()
         cvv = sharedPref.getString("cardCvv", "").toString()


        //display chosen food
        findViewById<TextView>(R.id.pizzaName).text = pizzaName
        findViewById<TextView>(R.id.size).text = sizeChosen
//        findViewById<TextView>(R.id.toppings).text = toppings
        findViewById<TextView>(R.id.total).text = totalPrice
        findViewById<TextView>(R.id.sideDishes).text = sideDishes
print(findViewById<TextView>(R.id.size).text)
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
    val Log = Logger.getLogger(ViewOrderActivity::class.java.name)

//    var writeFile = findViewById<View>(R.id.writefile_button) as Button
//    writeFile.setOnclickListener{

        fun writeFile() {
        val filename = "receipt.txt"
//        val txtinput: EditText =findViewById(R.id.txtInput)
        val orderSummaryText = "Thank You For Your Order! \n $name  \n  $address  \n $city  \n $postalCode  \n $phoneNumber  \n $creditCard   \n $cardType"
        Thread(Runnable {
            try {
                val out = openFileOutput(filename, Context.MODE_PRIVATE)
                out.use {
                    out.write(orderSummaryText.toByteArray())     //txtinput!!.text.toString()
                }
                runOnUiThread(Runnable {
                    Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show()
                })
            }
            catch(ioe: IOException) {
                Log.warning("Error while saving ${filename} : ${ioe}")
            }
        }).start()
    }


    override fun onPause() {
        super.onPause()
        writeFile()
    }


}