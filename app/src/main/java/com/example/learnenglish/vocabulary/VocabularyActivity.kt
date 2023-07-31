package com.example.learnenglish.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.books.definition.DisplayAdapter
import com.example.learnenglish.databinding.ActivityVocabularyBinding
import com.example.learnenglish.registration.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VocabularyActivity : AppCompatActivity() {
    lateinit var binding:ActivityVocabularyBinding
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocabularyBinding.inflate(layoutInflater)
        setContentView(binding.root)



        mDbRef = FirebaseDatabase.getInstance().reference
        val topics = ArrayList<String>()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = VocabularyAdapter(this,topics)
        binding.vocRecycle.layoutManager = layoutManager
        binding.vocRecycle.adapter = adapter

        mDbRef.child("vocabulary").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                topics.clear()
                for (postSnapshot in snapshot.children){
                    topics.add(postSnapshot.key.toString())
                }
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}