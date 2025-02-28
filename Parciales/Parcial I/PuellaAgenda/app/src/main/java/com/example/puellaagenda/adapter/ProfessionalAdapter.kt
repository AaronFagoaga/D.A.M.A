package com.example.puellaagenda.adapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puellaagenda.R
import com.example.puellaagenda.model.Professional
import com.example.puellaagenda.DetailsActivity

class ProfessionalAdapter(private val context: Context, private var professionalList: List<Professional>) :
    RecyclerView.Adapter<ProfessionalAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProfessional: ImageView = view.findViewById(R.id.imgProfessional)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtProfession: TextView = view.findViewById(R.id.txtProfession)
        val txtExperience: TextView = view.findViewById(R.id.txtExperience)
        val txtPacients: TextView = view.findViewById(R.id.txtPacients)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_professional, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val professional = professionalList[position]

        holder.txtName.text = professional.pName
        holder.txtProfession.text = professional.pProfession
        holder.txtExperience.text = "Años de experiencia: \n" + professional.pExperience
        holder.txtPacients.text = "Pacientes atendidos: \n" + professional.pClients

        Glide.with(context).load(professional.pImage).into(holder.imgProfessional)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("PROFESSIONAL", professional)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = professionalList.size

    fun updateList(newList: List<Professional>) {
        professionalList = newList
        notifyDataSetChanged()
    }
}
