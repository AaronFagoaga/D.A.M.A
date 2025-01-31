package com.example.puellamemorygame

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var cuadrilla: GridLayout
    private lateinit var textoNivel: TextView
    private lateinit var botones: List<View>
    private lateinit var botonIniciar: Button
    private var nivelActual = 1
    private var secuencia = mutableListOf<Int>()
    private var indiceSecuencia = 0
    private val manejador = Handler()
    private val colores = listOf(
        R.drawable.color_red, R.drawable.color_blue, R.drawable.color_green,
        R.drawable.color_yellow, R.drawable.color_orange, R.drawable.color_purple,
        R.drawable.color_cyan, R.drawable.color_pink, R.drawable.color_brown
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cuadrilla = findViewById(R.id.gridLayout)
        textoNivel = findViewById(R.id.headerText)
        botonIniciar = findViewById(R.id.buttonIniciar)
        botones = List(9) { crearBoton(it) }
        botones.forEach { cuadrilla.addView(it) }

        botonIniciar.setOnClickListener {
            reiniciarJuego()
        }
    }

    private fun crearBoton(index: Int): View {
        val boton = View(this)
        val tamaño = resources.getDimensionPixelSize(R.dimen.button_size)
        boton.layoutParams = GridLayout.LayoutParams().apply {
            width = tamaño
            height = tamaño
            columnSpec = GridLayout.spec(index % 3, 1f)
            rowSpec = GridLayout.spec(index / 3, 1f)
        }
        boton.background = ContextCompat.getDrawable(this, colores[index])
        boton.setOnClickListener { onBotonClick(index) }
        return boton
    }

    private fun onBotonClick(index: Int) {
        if (index == secuencia[indiceSecuencia]) {
            indiceSecuencia++
            if (indiceSecuencia == secuencia.size) {
                if (nivelActual == 5) {
                    finalizarJuego(true)
                } else {
                    nivelActual++
                    iniciarNivel()
                }
            }
        } else {
            finalizarJuego(false)
        }
    }

    private fun iniciarNivel() {
        textoNivel.text = "Nivel $nivelActual"
        secuencia.add((0..8).random())
        indiceSecuencia = 0
        mostrarSecuencia()
    }

    private fun mostrarSecuencia() {
        for (i in secuencia.indices) {
            manejador.postDelayed({
                resaltarBoton(secuencia[i])
            }, (i + 1) * 1000L)
        }
    }

    private fun resaltarBoton(index: Int) {
        val boton = botones[index]
        boton.alpha = 0.5f
        manejador.postDelayed({
            boton.alpha = 1f
        }, 500)
    }

    private fun finalizarJuego(ganado: Boolean) {
        textoNivel.text = if (ganado) "¡Ganaste!" else "¡Perdiste!"
        Toast.makeText(this, if (ganado) "¡Felicidades!" else "Inténtalo de nuevo", Toast.LENGTH_SHORT).show()
        botones.forEach { it.isClickable = false }
        botonIniciar.text = "Reiniciar Juego"
    }

    private fun reiniciarJuego() {
        nivelActual = 1
        secuencia.clear()
        indiceSecuencia = 0
        botones.forEach { it.isClickable = true }
        botonIniciar.text = "Iniciar Juego"
        iniciarNivel()
    }
}
