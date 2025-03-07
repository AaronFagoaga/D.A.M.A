package com.jfagoaga.puellalearn

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jfagoaga.puellalearn.data.Animal
import com.jfagoaga.puellalearn.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animal = intent.getParcelableExtra<Animal>("animal")
        if (animal == null) {
            finish()
            return
        }

        binding.detailImage.setImageResource(animal.imageResId)
        binding.detailName.text = animal.name
        binding.detailPronunciation.text = animal.pronunciation

        binding.playButton.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, animal.audioResId)
            }
            mediaPlayer?.start()
        }

        binding.stopButton.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}