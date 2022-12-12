package com.example.a711_proj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.IOException
import java.util.logging.Logger
import java.util.zip.CheckedOutputStream

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
        val cvv = findViewById<EditText>(R.id.cardCVV)
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
    val Log = Logger.getLogger(CheckoutActivity::class.java.name)

    fun onClickSaveForAutoFill (view: View){
    val name = findViewById<EditText>(R.id.name)
    val address = findViewById<EditText>(R.id.address)
    val city = findViewById<EditText>(R.id.city)
    val postalCode = findViewById<EditText>(R.id.postalCode)
    val phoneNumber = findViewById<EditText>(R.id.telephone)
    val creditCard = findViewById<EditText>(R.id.cardNumber)
    val expirationDate = findViewById<EditText>(R.id.cardExpiry)
    val cvv = findViewById<EditText>(R.id.cardCVV)
    // get card type from spinner
    val cardTypeText = findViewById<android.widget.Spinner>(R.id.cardType).selectedItem.toString()
        val arr = arrayOf<String>(name!!.text.toString(), address!!.text.toString(),city!!.text.toString(),postalCode!!.text.toString(), phoneNumber!!.text.toString(),creditCard!!.text.toString()  )
val writeString = name!!.text.toString()+","+  address!!.text.toString()+","+ city!!.text.toString()+","+postalCode!!.text.toString() +","+ phoneNumber!!.text.toString()+","+creditCard!!.text.toString()

        val filename = "autoFillInfo.txt"
    Thread(Runnable {
        try {
            val out = openFileOutput(filename, Context.MODE_PRIVATE)
            out.use {
//                out.write(arr.toString().toByteArray())
//                println(arr.toString())
                out.write(writeString.toByteArray())
//                out.write(name!!.text.toString().toByteArray())
//                out.write(address!!.text.toString().toByteArray())
//                out.write(city!!.text.toString().toByteArray())
//                out.write(postalCode!!.text.toString().toByteArray())
//                out.write(phoneNumber!!.text.toString().toByteArray())
//                out.write(creditCard!!.text.toString().toByteArray())
//                out.write(cardTypeText!!.toByteArray())
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

    //Read the text from the file
     fun autoFill (view: View) {
        val name = findViewById<EditText>(R.id.name)
        val address = findViewById<EditText>(R.id.address)
        val city = findViewById<EditText>(R.id.city)
        val postalCode = findViewById<EditText>(R.id.postalCode)
        val phoneNumber = findViewById<EditText>(R.id.telephone)
        val creditCard = findViewById<EditText>(R.id.cardNumber)
        val expirationDate = findViewById<EditText>(R.id.cardExpiry)
        val cvv = findViewById<EditText>(R.id.cardCVV)
        // get card type from spinner
        val cardTypeText = findViewById<android.widget.Spinner>(R.id.cardType).selectedItem.toString()

        val filename = "autoFillInfo.txt"
        Thread(Runnable{
            try {
                val input = openFileInput(filename)
                input.use {
                    var buffer = StringBuilder()
                    var bytes_read = input.read()
                    var mString = arrayOf<String>()
                    while(bytes_read != -1) {
                        buffer.append(bytes_read.toChar())
                        bytes_read = input.read()

                         mString = buffer.toString()!!.split(",").toTypedArray()
                        println(mString)
                    }
                    runOnUiThread(Runnable{
                        name!!.setText(mString[0])
                        address!!.setText(mString[1])
                        city!!.setText(mString[2])
                        postalCode!!.setText(mString[3])
                        phoneNumber!!.setText(mString[4])
                        creditCard!!.setText(mString[5])
//                        name!!.setText(buffer.toString())
//                        address!!.setText(buffer.toString())
//                        city!!.setText(buffer.toString())
//                        postalCode!!.setText(buffer.toString())
//                        phoneNumber!!.setText(buffer.toString())
//                        creditCard!!.setText(buffer.toString())
//                        cardTypeText!!.selectedItem.toString()(buffer.toString())
                    })
                }
            }
            catch(fnfe: FileNotFoundException) {
                Log.warning("file not found, occurs only once")
            }
            catch(ioe: IOException) {
                Log.warning("IOException : $ioe")
            }
        }).start()
    }

}