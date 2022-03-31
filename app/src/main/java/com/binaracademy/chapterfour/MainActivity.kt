package com.binaracademy.chapterfour

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_REQ_CODE = 201;
    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var btnImage : Button
    private lateinit var btnRequest : Button
    private lateinit var ivImage : ImageView

    private lateinit var tvLatitude : TextView
    private lateinit var tvLongitude : TextView
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnImage = findViewById(R.id.btnImage)
        btnRequest = findViewById(R.id.btnRequest)
        ivImage = findViewById(R.id.ivImage)
        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)
        val func = Function(ivImage,this)

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        btnImage.setOnClickListener {
            func.getImage(ivImage,this)
        }

        btnRequest.setOnClickListener {
            getLongLat()

        }

    }

    @SuppressLint("MissingPermission")
    private fun getLongLat() {

        val permissionCheck = checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permission Location Diizinkan",Toast.LENGTH_LONG).show()
            getLongLat()
        }else{
            Toast.makeText(this,"Permission Location Ditolak",Toast.LENGTH_LONG).show()
        }
//        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_REQ_CODE)
//
//            return
//        }

        fusedLocation.lastLocation.addOnSuccessListener {
            location ->
            latitude = location.latitude
            longitude = location.longitude

            tvLatitude.setText("Latitude:  ${location.latitude}}")
            tvLongitude.setText("Longitude:  ${longitude}")
            Toast.makeText(this,"Lat : ${location.latitude}, Long: ${location.longitude}",Toast.LENGTH_LONG).show()
        }

    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            LOCATION_PERMISSION_REQ_CODE ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION ){
                    Toast.makeText(this,"Permission for Location Permitted",Toast.LENGTH_LONG).show()
                    getLongLat()
                }else{
                    Toast.makeText(this,"Permission for Location Denied",Toast.LENGTH_LONG).show()
                }
            }else ->{
                 Toast.makeText(this,"The Request code doesnt match",Toast.LENGTH_LONG).show()
            }
        }
    }

}