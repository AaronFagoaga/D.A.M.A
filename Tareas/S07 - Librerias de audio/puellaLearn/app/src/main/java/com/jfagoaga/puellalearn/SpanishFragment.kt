package com.jfagoaga.puellalearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jfagoaga.puellalearn.data.Animal
import com.jfagoaga.puellalearn.data.AnimalAdapter

class SpanishFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalAdapter
    private val animalList = mutableListOf<Animal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spanish, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        animalList.add(Animal("Gato", "GA-TO", R.drawable.cat, R.raw.gato_sound))
        animalList.add(Animal("Cocodrilo", "CO-CO-DRI-LO", R.drawable.crocodile, R.raw.cocordilo_sound))
        animalList.add(Animal("Águila", "Á-GUI-LA", R.drawable.eagle, R.raw.aguila_sound))
        animalList.add(Animal("Elefante", "E-LE-FAN-TE", R.drawable.elephant, R.raw.elefante_sound))
        animalList.add(Animal("Zorro", "ZO-RRO", R.drawable.fox, R.raw.zorro_sound))
        animalList.add(Animal("León", "LE-ÓN", R.drawable.lion, R.raw.leon_sound))
        animalList.add(Animal("Ratón", "RA-TÓN", R.drawable.mouse, R.raw.raton_sound))
        animalList.add(Animal("Pantera", "PAN-TE-RA", R.drawable.phanter, R.raw.pantera_sound))
        animalList.add(Animal("Tigre", "TI-GRE", R.drawable.tiger, R.raw.tigre_sound))
        animalList.add(Animal("Tiburón", "TI-BU-RÓN", R.drawable.shark, R.raw.tiburon_sound))

        adapter = AnimalAdapter(requireContext(), animalList)
        recyclerView.adapter = adapter

        return view
    }
}