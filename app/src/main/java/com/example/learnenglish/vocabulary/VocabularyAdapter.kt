package com.example.learnenglish.vocabulary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R

class VocabularyAdapter(private val context:Context,private val dataList: List<String>) : RecyclerView.Adapter<VocabularyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_topic, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.nameText.text = data
        holder.learnButton.setOnClickListener {
            val intent = Intent(context,WordsActivity::class.java)
            intent.putExtra("topicName",data)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.vocabularyTopic)
        val learnButton = itemView.findViewById<Button>(R.id.vocabularyLearn)
    }
}