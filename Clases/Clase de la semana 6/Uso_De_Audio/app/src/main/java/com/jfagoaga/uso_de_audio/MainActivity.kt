package com.jfagoaga.uso_de_audio

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.net.ConnectivityManager

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var audioSource = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<ImageView>(R.id.btnPlay)
        val btnStop = findViewById<ImageView>(R.id.btnStop)
        val btnRemote = findViewById<Button>(R.id.btnRemote)
        val btnLocal = findViewById<Button>(R.id.btnLocal)

        mediaPlayer = MediaPlayer()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnLocal.setOnClickListener {
            try {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
                mediaPlayer?.reset()
                try {
                    val idResource = R.raw.fontaine
                    mediaPlayer?.setDataSource(this, android.net.Uri.parse("android.resource://$packageName/$idResource"))
                    mediaPlayer?.setOnPreparedListener {
                        audioSource = true
                        showMessage("Audio local cargado")
                    }
                    mediaPlayer?.setOnErrorListener{ _, _, _ -> audioSource = false
                        showMessage("Error al cargar el audio local")
                        false
                    }
                    showMessage("Cargando audio local...")
                    mediaPlayer?.prepare()
                }catch (ex: Exception){
                    showMessage("Error al cargar el audio local")
                }
            } catch (ex: Exception){
                showMessage("Error al cargar audio.")
            }
        }

        btnRemote.setOnClickListener {
            try {
                if (!isConnected()) {
                    showMessage("No hay conexión a internet")
                    return@setOnClickListener
                }
                if (mediaPlayer?. isPlaying == true) {
                    mediaPlayer?.stop()
                }
                mediaPlayer?.reset()
                val urlAudio = "https://tonosmovil.net/wp-content/uploads/tonosmovil.net_himno_champions_league.mp3"
                mediaPlayer?.setDataSource(urlAudio)
                mediaPlayer?.setOnPreparedListener {
                    audioSource = true
                    showMessage("Audio remoto cargado")
                }
                mediaPlayer?.setOnErrorListener { _, _, _ ->
                    audioSource = false
                    showMessage("Error al cargar el audio remoto")
                    false
                }
                showMessage("Cargando audio remoto...")
            }catch (ex: Exception){
                showMessage("Error al cargar el audio remoto")
            }
        }

        btnStop.setOnClickListener {
            try {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                    mediaPlayer?.reset()
                    audioSource = false
                    showMessage("Audio detenido")
                }else{
                    showMessage("No hay audio que detener")
                }

            } catch (ex: Exception){
                showMessage("Error al detener el audio")
            }
        }

        btnPlay.setOnClickListener {
            try {
                if (mediaPlayer?.isPlaying == true) {
                    showMessage("Audio ya está reproduciendo")
                }else if (audioSource){
                    mediaPlayer?.start()
                    showMessage("Reproduciendo audio")
                }else{
                    showMessage("No hay audio para reproducir")
                }
            }catch (ex: Exception){
                showMessage("Error al reproducir el audio")
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if(networkInfo == null){
            return false
        }
        return networkInfo.isConnected
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}