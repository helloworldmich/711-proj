package com.example.a711_proj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
    }

    fun placeOrder(view: View){
        val intent = Intent(this, ViewOrderActivity::class.java)

        val name = findViewById<EditText>(R.id.name)
        val address = findViewById<EditText>(R.id.address)
        val city = findViewById<EditText>(R.id.city)
        val postalCode = findViewById<EditText>(R.id.postalCode)
        val phoneNumber = findViewById<EditText>(R.id.telephone)
        val creditCard = findViewById<EditText>(R.id.cardNumber)
        val expirationDate = findViewById<EditText>(R.id.cardExpiry)
        val cvv = findViewById<EditText>(R.id.cardCvv)
        // get card type from spinner
        val cardTypeText = findViewById<android.widget.Spinner>(R.id.cardType).selectedItem.toString()

        // check if the user has entered all the information
        // if not, show a toast message
        if (name.text.toString().isEmpty() || address.text.toString().isEmpty() || city.text.toString().isEmpty() || postalCode.text.toString().isEmpty() || phoneNumber.text.toString().isEmpty() || creditCard.text.toString().isEmpty() || expirationDate.text.toString().isEmpty() || cvv.text.toString().isEmpty()) {
            Toast.makeText(this, "Please fill out all of the fields", Toast.LENGTH_LONG).show()
            return
        }

        // save the information to the shared preferences
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("name", name.text.toString())
        editor.putString("address", address.text.toString())
        editor.putString("city", city.text.toString())
        editor.putString("postalCode", postalCode.text.toString())
        editor.putString("telephone", phoneNumber.text.toString())
        editor.putString("cardNumber", creditCard.text.toString())
        editor.putString("cardType", cardTypeText)
        editor.putString("cardExpiry", expirationDate.text.toString())
        editor.putString("cardCvv", cvv.text.toString())
        editor.commit()

        // start the summary activity
        startActivity(intent)
    }
}