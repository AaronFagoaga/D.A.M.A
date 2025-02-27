package com.example.imagepractice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.hutao1 to "Imagén seleccionada por Ariel 1",
            R.drawable.hutao2 to "Imagén seleccionada por Ariel 2",
            R.drawable.hutao3 to "Imagén seleccionada por Ariel 3"
        )
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ImagePagerAdapter(images)
        viewPager.adapter = adapter
    }
}