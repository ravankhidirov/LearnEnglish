package com.example.learnenglish.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.registration.User

class UserAdapter(val context: Context, val userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val contactName = itemView.findViewById<TextView>(R.id.contactName)
        val contactEmail = itemView.findViewById<TextView>(R.id.contactEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = userList[position]
        holder.contactName.text = data.name
        holder.contactEmail.text = data.email

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MessagingActivity::class.java)
            intent.putExtra("email",data.email)
            intent.putExtra("name",data.name)
            intent.putExtra("uid",data.uid)
            context.startActivity(intent)
        }
    }
}