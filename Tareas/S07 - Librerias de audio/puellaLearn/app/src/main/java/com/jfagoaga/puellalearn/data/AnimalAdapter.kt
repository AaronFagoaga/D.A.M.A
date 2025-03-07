package com.jfagoaga.puellalearn.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jfagoaga.puellalearn.DetailActivity
import com.jfagoaga.puellalearn.R


class AnimalAdapter(
    private val context: Context,
    private val animalList: List<Animal>
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.animalImage.setImageResource(animal.imageResId)
        holder.animalName.text = animal.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("animal", animal)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animalImage: ImageView = itemView.findViewById(R.id.animalImage)
        val animalName: TextView = itemView.findViewById(R.id.animalName)
    }
}