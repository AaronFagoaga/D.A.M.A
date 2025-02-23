package com.example.puellaimageslibrary

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.sera_moon1, "30 aniversario Sailor Moon!"))
        imageList.add(SlideModel(R.drawable.sera_moon2, "Sailor Moon Crystal"))
        imageList.add(SlideModel(R.drawable.sera_moon3, "Sailor Guardians"))
        imageList.add(SlideModel(R.drawable.sera_moon4, "Sailor Moon Classic"))
        imageList.add(SlideModel(R.drawable.sera_moon5, "Súper Sailor Moon"))

        imageSlider.setImageList(imageList)

        val btnWebSite = findViewById<Button>(R.id.btnWebSite)
        btnWebSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sailormoon-official.com/animation/en/"))
            startActivity(intent)
        }
    }
}