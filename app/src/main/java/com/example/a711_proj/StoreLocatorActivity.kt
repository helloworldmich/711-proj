package com.example.a711_proj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.OnMapReadyCallback

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

//abstract class StoreLocatorActivity : AppCompatActivity(), OnMapReadyCallback {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_store_locator)
//    }
//}


class StoreLocatorActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_locator)

        //binding = ActivityMapsBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

        //   val intent = intent
        //   val selectedBrand = intent.getStringExtra("selectedBrand")


        //   if (selectedBrand == "Store") {
        val Pizza_Store_1 = LatLng(43.65286019794639, -79.38056071911612)
        mMap.addMarker(MarkerOptions().position(Pizza_Store_1).title("Pizza_Store_1 Store in Eaton center").snippet("Apple store1 \n opening hours"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pizza_Store_1), )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Pizza_Store_1, 10f,))

        val Pizza_Store_2 = LatLng(43.7791536146208, -79.34344577761483)
        mMap.addMarker(MarkerOptions().position(Pizza_Store_2).title("Pizza_Store_2 Store in Fairview Mall"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pizza_Store_2))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Pizza_Store_2, 10f))

        val Pizza_Store_3 = LatLng(43.725781358178764, -79.4528616083024)
        mMap.addMarker(MarkerOptions().position(Pizza_Store_3).title("Pizza_Store_3 Store in Yorkdale mall"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pizza_Store_3))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Pizza_Store_3, 10f))
    }


    fun onClickNormal(view: View) {
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL;
    }
    fun onSatelliteMap(view: View) {
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE;
    }
    fun onHybridMap(view: View) {
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID;
    }
    fun onTerrainMap(view: View) {
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN;
    }

}