package com.example.learnenglish.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.chatbot.Message
import com.example.learnenglish.chatbot.MessageAdapter
import com.example.learnenglish.databinding.ActivityMessagingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MessagingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMessagingBinding




    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView

    private lateinit var messageAdapter: ChatMessageAdapter
    private lateinit var messageList:ArrayList<ChatMessage>


    private lateinit var mDbRef: DatabaseReference

    private lateinit var backButton: ImageButton

    var receiverRoom:String? = null
    var senderRoom:String? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagingBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val receiverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance().reference
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid





        chatRecyclerView = findViewById(R.id.recyclerView)
        messageBox = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendBtn)
        messageList = ArrayList()
        messageAdapter = ChatMessageAdapter(this,messageList)



        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter



        //logic for adding data to recyclerview
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(ChatMessage::class.java)
                        messageList.add(message!!)
                    }

                    messageAdapter.notifyDataSetChanged()

                }
                override fun onCancelled(error: DatabaseError) {

                }

            })

        val name = intent.getStringExtra("name")
        // add message to database
        sendButton.setOnClickListener {

            val message = messageBox.text.toString()
            val messageObject = ChatMessage(message,senderUid,name)
            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")
        }




    }
}