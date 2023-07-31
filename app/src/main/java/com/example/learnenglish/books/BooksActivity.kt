package com.example.learnenglish.books

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnenglish.books.book_sections.BookDetailsActivity
import com.example.learnenglish.databinding.ActivityBooksBinding

class BooksActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBooksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.readLittlePrince.setOnClickListener {
            val intent = Intent(this, BookDetailsActivity::class.java)
            intent.putExtra("bookName","littlePrince")
            intent.putExtra("bookTitle","The Little Prince")
            startActivity(intent)
        }


    }
}