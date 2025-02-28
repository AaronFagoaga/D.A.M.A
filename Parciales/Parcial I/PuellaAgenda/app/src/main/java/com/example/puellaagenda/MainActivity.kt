package com.example.puellaagenda

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puellaagenda.adapter.ProfessionalAdapter
import com.example.puellaagenda.model.Professional

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerProfessionals: RecyclerView
    private lateinit var adapter: ProfessionalAdapter
    private lateinit var edtSearch: EditText
    private lateinit var professionalList: List<Professional>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtSearch = findViewById(R.id.edtSearch)
        recyclerProfessionals = findViewById(R.id.recyclerProfessional)
        recyclerProfessionals.layoutManager = LinearLayoutManager(this)

        professionalList = listOf(
            Professional("Dr. Tego Calderón", "Cirujano", "8 años", "2500", R.drawable.dr_tego, "john@example.com", "77341732"),
            Professional("Dra. Susana Marielos", "Cardióloga", "5 años", "903", R.drawable.dra_susana, "susanita@example.com", "77341732"),
            Professional("Dra. Paula Cuevas", "Psicóloga", "5 años", "9923", R.drawable.dra_paula, "paulita@example.com", "77341732"),
            Professional("Dra. Erika Fagoaga", "Médico internista", "5 años", "903", R.drawable.dra_erika, "erika@example.com", "77341732"),
            Professional("Dr. Petunio Pastizales", "Pepenador", "5 años", "903", R.drawable.dr_cesar, "petunio@example.com", "77341732")
        )

        adapter = ProfessionalAdapter(this, professionalList)
        recyclerProfessionals.adapter = adapter

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        val filteredList = professionalList.filter {
            it.pName.contains(query, ignoreCase = true) || it.pProfession.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }
}
