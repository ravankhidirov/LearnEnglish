package com.example.learnenglish.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.chatbot.Message
import com.google.firebase.auth.FirebaseAuth


class ChatMessageAdapter(val context: Context, val messageList:ArrayList<ChatMessage>):
    RecyclerView.Adapter<ChatMessageAdapter.MyViewHolder>() {

    private lateinit var mAuth: FirebaseAuth



    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val leftChatView = itemView.findViewById<LinearLayout>(R.id.left_chat_view)
        val rightChatView = itemView.findViewById<LinearLayout>(R.id.right_chat_view)
        val leftTextView = itemView.findViewById<TextView>(R.id.left_chat_text_view)
        val rightTextView = itemView.findViewById<TextView>(R.id.right_chat_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {


        val view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message = messageList[position]
        mAuth = FirebaseAuth.getInstance()
        if (message.senderId == mAuth.currentUser?.uid!!) {
            holder.leftChatView.visibility = View.GONE
            holder.rightChatView.visibility = View.VISIBLE
            holder.rightTextView.text = message.message
        } else {
            holder.rightChatView.visibility = View.GONE
            holder.leftChatView.visibility = View.VISIBLE
            holder.leftTextView.text = message.message
        }
    }


    override fun getItemCount(): Int {
        return messageList.size
    }

}