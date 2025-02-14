package com.example.tabs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class TechnologiesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_technologies, container, false)
        val tvTecnologias = view.findViewById<TextView>(R.id.tvTecnologias)
        tvTecnologias.text = "- Kotlin\n- Android\n- Java\n- PHP\n- C#\n- JavaScript\n- HTML\n- Dormir"

        view.findViewById<Button>(R.id.btnContactame).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL,
                    arrayOf("sraaron1@outlook.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Me interesa tus servicios UwU")
            }
            startActivity(intent)
        }
        return view
    }
}
