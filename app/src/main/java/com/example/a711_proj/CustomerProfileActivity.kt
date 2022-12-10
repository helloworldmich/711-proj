package com.example.a711_proj

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a711_proj.CustomerInfoDataClass
import com.example.a711_proj.R
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase


class CustomerProfileActivity : AppCompatActivity() {

    //global declaration
    private lateinit var  fireBase: FirebaseDatabase
    private lateinit var customer: CustomerInfoDataClass
    private lateinit var dataSnapshot: DataSnapshot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_profile)

        //initializing firebase object
        fireBase=FirebaseDatabase.getInstance()

        //initializing button objects
        val btnAdd=findViewById<Button>(R.id.btnAdd)
        val btnShow=findViewById<Button>(R.id.btnShow)

        //creating a data ref object to add the values
        val dbRef=fireBase.getReference()


        //Add button event handler
        btnAdd.setOnClickListener(){

            //initializing edit text objects
            val edtName = findViewById<EditText>(R.id.edtName)
            val edtPhone = findViewById<EditText>(R.id.edtPhone)
            val edtAddress = findViewById<EditText>(R.id.edtAddress)
            val edtPostalCode = findViewById<EditText>(R.id.edtPostalCode)
            val edtCity = findViewById<EditText>(R.id.edtCity)

            //getting the input values from edit text
            val cName = edtName.text.toString()
            val cPhone = edtPhone.text.toString()
            val cAddress = edtAddress.text.toString()
            val cPostalCode = edtPostalCode.text.toString()
            val cCity = edtCity.text.toString()

            //passing the values using student constructor
            customer=CustomerInfoDataClass(cName,cPhone,cAddress,cPostalCode,cCity)

            val field1: DatabaseReference = fireBase.getReference("customerName")
            val field2: DatabaseReference = fireBase.getReference("customerPhone")
            val field3: DatabaseReference = fireBase.getReference("customerAddress")
            val field4: DatabaseReference = fireBase.getReference("customerPostalCode")
            val field5: DatabaseReference = fireBase.getReference("customerCity")


            field1.setValue(cName)
            field2.setValue(cPhone)
            field3.setValue(cAddress)
            field4.setValue(cPostalCode)
            field5.setValue(cCity)

            //add values(student object) into database
            // dbRef.setValue(student)
            //dbRef.push().setValue(student)

        }

        //show button event handler
        btnShow.setOnClickListener(){
            var clientName = ""
            var clientPhone = ""
            var clientAddress = ""
            var clientPostalCode = ""
            var clientCity = ""
            //event handler for dbRef object to read the values
            dbRef.addValueEventListener(object : ValueEventListener {
                //onDataChanged fun to initializing dataSnapshot object
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val customer = dataSnapshot.getValue(CustomerInfoDataClass::class.java)
                    if (customer == null) {
                        return
                    }
                    // reading the values
                     clientName=customer?.customerName.toString()
                     clientPhone=customer?.customerPhone.toString()
                     clientAddress=customer?.customerAddress.toString()
                     clientPostalCode=customer?.customerPostalCode.toString()
                     clientCity=customer?.customerCity.toString()

                    println(clientName)

                    //display as a toast message
                    Toast.makeText(applicationContext, "Welcome to our Pizza App, your info: "+ clientName +" "+clientPhone+" "+clientAddress+" "+clientPostalCode+" "+clientCity, Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }


            })
            println(clientName)
            var showClientInfo = findViewById<TextView>(R.id.showClientInfo)
            showClientInfo.text= clientName
        }

    }

}