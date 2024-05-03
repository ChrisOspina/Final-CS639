package com.example.plateperf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plateperf.R
import java.util.ArrayList

class RecipeAdapter(private val recipeList: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        var recipeName: TextView = itemView.findViewById(R.id.recipeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]

        // Set recipe name
        holder.recipeName.text = currentRecipe.name

        // Set recipe image using Glide or any other image loading library
        Glide.with(holder.itemView.context)
            .load(currentRecipe.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.recipeImage)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
