package com.binaracademy.chapterfour

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.media.Image
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

data class Function(
    val ivImage: ImageView,
    val context: Context
) {

    fun getImage(ivImage : ImageView,context : Context) {
        Glide.with(context)
            .load("https://akcdn.detik.net.id/community/media/visual/2022/03/25/manga-one-piece_169.webp?w=700&q=90")
            .circleCrop()
            .into(ivImage)
    }

    @SuppressLint("MissingPermission")
     fun getLongLat(context: Context) {
//
//        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_REQ_CODE)
//
//            return
//        }
//
//        fusedLocation.lastLocation.addOnSuccessListener {
//            location -> latitude = location.latitude
//            longitude = location.longitude
//
//            Toast.makeText(this,"Lat : ${location.latitude}, Long: ${location.longitude}",Toast.LENGTH_LONG).show()
//        }
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location: Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            Toast.makeText(context,"Lat: ${location.latitude} Long:${location.longitude}", Toast.LENGTH_LONG).show()
        }
    }

}