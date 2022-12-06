package com.example.a711_proj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val listView: ListView = findViewById(R.id.list)

        val regularPizza = arrayOf<String>("TOMATO","CHEESE","WHITE","PEPPERONI","SAUSAGE","SPICY HAWAIIAN","MEATLOVERS","SALAMI")

        val pList = ArrayList<String>(listOf(*regularPizza))

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, pList)

        //attach the list view to the arrayadapter
        listView.adapter = arrayAdapter

        // onclick event
        listView.setOnItemClickListener { parent, _, position, _ ->
            val listValue = parent.getItemAtPosition(position) as String
            Toast.makeText(this,"Selected item is $listValue", Toast.LENGTH_LONG).show()
        }
    }
}

//TOMATO
//tomato, garlic, basil, caper, olive oil (no cheese)
//15 / 26
//ANCHOVY
//tomato, garlic, caper, olive (no cheese)
//14 / 24
//CHEESE
//tomato, cheese
//15 / 26
//MARGHERITA
//tomato, cheese, basil, olive oil
//17 / 30
//WHITE
//cream, cheese, ricotta, caramelized onions, arugula
//17 / 30
//KALE
//cream, cheese, garlic chili, lemon
//17 / 30
//MUSHROOM
//cream, cheese, parmesan, herbs
//18 / 32
//ARTICHOKE & OLIVE
//tomato, cheese, red onion, basil, garlic, caper
//18 / 32
//"VEGETARIAN"
//tomato, cheese, kale, mushroom, olive, onion
//17 / 30
//PEPPERONI
//tomato, garlic, cheese, oregano
//18 / 32
//SAUSAGE
//tomato, cheese, spicy peppers, basil
//18 / 32
//SALAMI
//tomato, cheese, salami, olives, honey, chili
//
//18 / 32
//BACON
//cheese, garlic, egg yolk, herbs, black pepper
//18 / 32
//SPICY HAWAIIAN
//tomato, cheese, capicollo, spicy peppers, pineapple, basil
//18 / 32
//MEATLOVERS
//tomato, cheese, pepperoni, sausage, bacon, garlic, oregano
//1 / 2
//TOPPINGS (S/L)
//arugula, caramelized onion, spicy peppers, mushrooms, anchovy, olives, kale, egg yolk, parmesan