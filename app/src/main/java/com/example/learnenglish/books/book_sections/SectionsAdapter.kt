package com.example.learnenglish.books.book_sections


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.books.reading.ReadingActivity

class SectionsAdapter(val context: Context, private val dataList: List<Section>) : RecyclerView.Adapter<SectionsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.section_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.nameText.text ="Section " +  data.sectionNumber.toString()
        holder.itemView.setOnClickListener {

            val intent = Intent(context, ReadingActivity::class.java)
            intent.putExtra("sectionNumber",data.sectionNumber.toString())
            intent.putExtra("sectionContent",data.sectionContent)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.section)
    }
}