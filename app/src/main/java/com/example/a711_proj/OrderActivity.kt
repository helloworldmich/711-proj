package com.example.a711_proj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.a711_proj.databinding.ActivityMainBinding

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var sharedPref = getSharedPreferences("pizzaPref", MODE_PRIVATE)
        var pizzaType = sharedPref.getString("pizzaType", "")
        var pizzaChoices = arrayOf("")
        var description = arrayOf(" ")
        var imageId =  arrayOf(10)
        val listView: ListView = findViewById(R.id.list)

        //handling radio buttons
        val group = findViewById<View>(R.id.radioGroup) as RadioGroup
        group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                val pizzaType = findViewById<View>(checkedId) as RadioButton
                if (pizzaType != null) {

                    if (pizzaType.text == "Meat") {
                        pizzaChoices = arrayOf<String>(
                            "PEPPERONI",
                            "SAUSAGE",
                            "SALAMI",
                            "BACON",
                            "SPICY HAWAIIAN",
                            "MEAT LOVERS",
                            "Sichuan Spicy Chicken"
                        )
                         description = arrayOf<String>(
                            "tomato, garlic, cheese, oregano         $18 / 32",
                            " tomato, cheese, spicy peppers, basil   $18 / 32",
                            "tomato, cheese, salami, olives, honey, chili     $18 / 32",
                            "cheese, garlic, egg yolk, herbs, black pepper    $18 / 32",
                            "tomato, cheese, capicollo, spicy peppers, pineapple, basil   $18 / 32",
                            "tomato, cheese, pepperoni, sausage, bacon, garlic, oregano $18 / 32",
                            "chicken, Sichuan chili, sesame oil, pineapple    $18 / 32"
                        )
                         imageId = arrayOf<Int>(
                            R.drawable.pepperoni_img,
                            R.drawable.sausage_img,
                            R.drawable.salami_img,
                            R.drawable.bacon_img,
                            R.drawable.spicy_hawaiian_img,
                            R.drawable.meatlover_img,
                            R.drawable.sichuan_img
                        )
                    } else if (pizzaType.text == "Vegetarian") {
                        pizzaChoices = arrayOf<String>(
                            "TOMATO",
                            "ANCHOVY",
                            "CHEESE",
                            "MARGHERITA",
                            "WHITE",
                            "KALE",
                            "MUSHROOM",
                            "ARTICHOKE & OLIVE",
                            "VEGETARIAN"
                        )
                         description = arrayOf<String>(
                            "tomato, garlic, basil, caper, olive oil (no cheese)  $14 / 24",
                            "tomato, garlic, caper, olive (no cheese)  $15 / 26",
                            "tomato, cheese    $14 / 24",
                            "tomato, cheese, basil, olive oil   $15 / 26",
                            "cream, cheese, ricotta, caramelized onions, arugula  $17 / 30",
                            "cream, cheese, garlic chili, lemon   $17 / 30",
                            "cream, cheese, parmesan, herbs   $17 / 30",
                            "tomato, cheese, red onion, basil, garlic, caper  $18 / 32",
                            "tomato, cheese, kale, mushroom, olive, onion   $18 / 32"

                        )
                         imageId = arrayOf<Int>(R.drawable.pizza_img,
                            R.drawable.anchovy_img,
                            R.drawable.pizza_img,
                            R.drawable.margherita_img,
                            R.drawable.white_img,
                            R.drawable.kale_img,
                            R.drawable.mushroom_img,
                            R.drawable.artichoke_img,
                            R.drawable.vegetarian_img
                        )
                    }
//                    val pList = ArrayList<String>(listOf(*pizzaChoices))
//                    val arrayAdapter =
//                        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pList)


                    val myListAdapter = ListAdapter(this,pizzaChoices,description,imageId)
                    //attach the list view to the array adapter
                    listView.adapter = myListAdapter

                    // onclick event
                    listView.setOnItemClickListener { parent, _, position, _ ->
                        val listValue = parent.getItemAtPosition(position) as String // better to call itemAtPos
                        val itemIdAtPos = parent.getItemIdAtPosition(position)// parent = adapterView
                        Toast.makeText(this, "You choose $listValue", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }


        //handling check boxes for side dishes

        fun onCheckboxClicked(view: View) {
            if (view is CheckBox) {
                val checked: Boolean = view.isChecked
                var result = ""
                if (view.id == R.id.smashed_potato)  {  result += "\n smashed potato";  }
                else  if (view.id == R.id.garlic_bread)  {   result += "\n garlic bread" }
                else  if (view.id == R.id.house_salad)  {  result += "\n house salad" }
                else  if (view.id == R.id.mushroom_salad)  {  result += "\n mushroom salad" }
                else if (view.id == null)  {  result += "\n no side dishes" }
                Toast.makeText(this, "You chose $result" , Toast.LENGTH_LONG).show();

                }
            }


        //handling chip group and chips

        }
    }

// ref: customed expandable list, not listview
//https://androidexample.com/custom-expandable-listview-tutorial
//to add subitem (not subView), search keyword:  customed listview ,  SectionListView
// exactly i need: https://www.javatpoint.com/kotlin-android-custom-listview
//http://www.standardpizza.ca/menu-full


//                when (view.id) {
//                    R.id.smashed_potato -> {
//                        if (checked) {
//                            result += "\n smashed potato";
//                        }
////                        else {
////
////                        }
//                    }
//                    R.id.garlic_bread -> {
//                        if (checked) {
//                            result += "\n garlic bread"
//                        }
////                        else {
////
////                        }
//                    }
//
//                    R.id.house_salad -> {
//                        if (checked) {
//                            result += "\n house salad"
//                        }
////                        else {
////
////                        }
//                    }
//
//                    R.id.mushroom_salad -> {
//                        if (checked) {
//                            result += "\n mushroom salad"
//
//                        }
////                        else {
////
////                        }
//                    }
//                    Toast.makeText(this, "You chose $result" , Toast.LENGTH_LONG).show();    // ??????????why incompatible type Int -->string, result is string
//
//                    else -> {
//                        Toast.makeText(this, "You didn't choose side dishes", Toast.LENGTH_LONG)
//                            .show()
//                    }
