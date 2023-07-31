package com.example.learnenglish.books.book_sections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.databinding.ActivityBookDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var mDbRef: DatabaseReference
    lateinit var binding:ActivityBookDetailsBinding
    private lateinit var sections:ArrayList<Section>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mDbRef = FirebaseDatabase.getInstance().reference
        var name = intent.getStringExtra("bookName")
        var title = intent.getStringExtra("bookTitle")

        binding.bookName.text = title


        // reading all sections that the book has in the database
        sections = ArrayList()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = SectionsAdapter(this,sections)
        binding.sectionsRecyclerView.layoutManager = layoutManager
        binding.sectionsRecyclerView.adapter = adapter
        mDbRef.child("book").child(name!!).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                sections.clear()
                for (postSnapshot in snapshot.children){
                    val currentSection = postSnapshot.getValue(Section::class.java)
                    sections.add(currentSection!!)
                    Log.v("Data",currentSection.sectionContent!!)
                }
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}