package com.example.learnenglish.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.books.definition.DisplayAdapter
import com.example.learnenglish.databinding.ActivityVocabularyBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WordsActivity : AppCompatActivity() {

    lateinit var binding: ActivityVocabularyBinding
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocabularyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val topicName = intent.getStringExtra("topicName")

        mDbRef = FirebaseDatabase.getInstance().reference
        val words = ArrayList<String>()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = DisplayAdapter(words)
        binding.vocRecycle.layoutManager = layoutManager
        binding.vocRecycle.adapter = adapter

        mDbRef.child("vocabulary").child(topicName!!).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                words.clear()
                for (postSnapshot in snapshot.children){
                    words.add(postSnapshot.value.toString())
                }
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })


    }
}