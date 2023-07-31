package com.example.learnenglish.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.databinding.ActivityChatPeopleBinding
import com.example.learnenglish.registration.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatPeopleActivity : AppCompatActivity() {
    private lateinit var userList:ArrayList<User>
    private lateinit var binding:ActivityChatPeopleBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatPeopleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference

        userList = ArrayList()
        val contactLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        val contactsAdapter = UserAdapter(this,userList)
        binding.searchPeopleRecyclerView.layoutManager = contactLayoutManager
        binding.searchPeopleRecyclerView.adapter = contactsAdapter

        mDbRef.child("user").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }
                }
                contactsAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })


        binding.searchToAddButton.setOnClickListener {
            mDbRef.child("user").addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()
                    for (postSnapshot in snapshot.children){
                        val currentUser = postSnapshot.getValue(User::class.java)
                        if (currentUser?.name == binding.searchToAdd.text.toString()){
                            userList.add(currentUser!!)
                        }
                    }
                    contactsAdapter.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

    }
}