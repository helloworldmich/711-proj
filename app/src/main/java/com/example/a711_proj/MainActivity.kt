package com.example.a711_proj

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    fun navigateToLogin(v: View) {
        var intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intent = Intent(this@MainActivity, OrderActivity::class.java)
//        val sharedPrefPhoneModel: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
//        val editor: SharedPreferences.Editor = sharedPrefPhoneModel.edit()

        when (item.itemId) {
            R.id.order -> {
                setContentView(R.layout.activity_order)
                val i = Intent(this@MainActivity, OrderActivity::class.java)
                startActivity(i)

            }
            R.id.login -> {
                setContentView(R.layout.activity_login)
                val i = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(i)
            }
            R.id.view_order -> {
                setContentView(R.layout.activity_view_order)
                val i = Intent(this@MainActivity, ViewOrderActivity::class.java)
                startActivity(i)
            }
            R.id.store_locations -> {
                setContentView(R.layout.activity_store_locator)
                val i = Intent(this@MainActivity, StoreLocatorActivity::class.java)
                startActivity(i)

            }
            R.id.cutomer_profile -> {
                setContentView(R.layout.activity_customer_profile)
                val i = Intent(this@MainActivity, CustomerProfileActivity::class.java)
                startActivity(i)

            }

        }
//        editor.commit()
//        startActivity(intent)
        return true
    }

}