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

class EnglishFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalAdapter
    private val animalList = mutableListOf<Animal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_english, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        animalList.add(Animal("Cat", "C A T", R.drawable.cat, R.raw.cat_sound))
        animalList.add(Animal("Crocodile", "CRO-CO-DILE", R.drawable.crocodile, R.raw.crocodile_sound))
        animalList.add(Animal("Eagle", "E-AGLE", R.drawable.eagle, R.raw.eagle_sound))
        animalList.add(Animal("Elephant", "E-LE-PHANT", R.drawable.elephant, R.raw.elephant_sound))
        animalList.add(Animal("Fox", "FOX", R.drawable.fox, R.raw.fox_sound))
        animalList.add(Animal("Lion", "LI-ON", R.drawable.lion, R.raw.lion_sound))
        animalList.add(Animal("Mouse", "MA-US", R.drawable.mouse, R.raw.maus_sound))
        animalList.add(Animal("Phanter", "PHAN-TA", R.drawable.phanter, R.raw.panther_sound))
        animalList.add(Animal("Tiger", "TI-GER", R.drawable.tiger, R.raw.tiger_sound))
        animalList.add(Animal("Shark", "SHARK", R.drawable.shark, R.raw.shark_sound))

        adapter = AnimalAdapter(requireContext(), animalList)
        recyclerView.adapter = adapter

        return view
    }
}