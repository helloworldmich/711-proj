package com.example.a711_proj


import android.location.Address
import com.google.firebase.database.IgnoreExtraProperties

//data class
@IgnoreExtraProperties
data class CustomerInfoDataClass(

//    var customerID:String? = null,
    var customerName:String? = null,
    var customerPhone:String? = null,
    var customerAddress: String? = null,
    var customerPostalCode: String? = null,
    var customerCity: String? = null,
)
