package com.jfagoaga.puellacam

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var btnTakePhoto: Button
    private lateinit var btnShareEmail: ImageButton
    private lateinit var btnShareWhatsApp: ImageButton
    private lateinit var imgPhoto: ImageView
    private lateinit var currentPhotoPath: String

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            imgPhoto.setImageURI(Uri.fromFile(File(currentPhotoPath)))
            Toast.makeText(this, "Foto tomada correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al tomar la foto", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            takePhoto()
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnTakePhoto = findViewById(R.id.btnTakePhoto)
        imgPhoto = findViewById(R.id.imgPhoto)
        btnShareEmail = findViewById(R.id.btnShareEmail)
        btnShareWhatsApp = findViewById(R.id.btnShareWhatsApp)


        btnTakePhoto.setOnClickListener{
            takePhoto()
        }
        btnShareEmail.setOnClickListener {
            shareViaEmail()
        }

        btnShareWhatsApp.setOnClickListener {
            shareViaWhatsApp()
        }

    }
    private fun takePhoto() {
        when{
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                val photoFile = createImageFile()
                val photoURI = FileProvider.getUriForFile(this, "com.jfagoaga.puellacam.fileprovider", photoFile)
                currentPhotoPath = photoFile.absolutePath
                takePictureLauncher.launch(photoURI)
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(this, "Se necesita el permiso de cámara", Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun createImageFile(): File{
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir("Pictures")
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply { currentPhotoPath = absolutePath }
    }

    private fun shareViaEmail() {
        val photoUri = FileProvider.getUriForFile(this, "com.jfagoaga.puellacam.fileprovider", File(currentPhotoPath))

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_STREAM, photoUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(Intent.createChooser(emailIntent, "Enviar foto por correo"))
    }

    private fun shareViaWhatsApp() {
        val photoUri = FileProvider.getUriForFile(this, "com.jfagoaga.puellacam.fileprovider", File(currentPhotoPath))

        val whatsappIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_STREAM, photoUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setPackage("com.whatsapp")
        }

        try {
            startActivity(whatsappIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
        }
    }


}