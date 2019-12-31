package com.example.rifqi.footballapp.features.DetailLeague

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.model.Categories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoriesAdapter(
    private val categories: List<Categories>,
    private val listener: (Categories) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_categories
                , parent, false
            )
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categories = categories[position]
        holder.bindItem(categories, listener)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(categories: Categories, listener: (Categories) -> Unit) {
            itemView.tv_categories.text = categories.category
            categories.imgCategory?.let {
                Picasso.get().load(it).fit().into(itemView.img_categories)
            }
            itemView.setOnClickListener {
                listener(categories)
            }
        }
    }
}