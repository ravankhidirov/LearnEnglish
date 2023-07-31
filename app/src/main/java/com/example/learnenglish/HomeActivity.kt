package com.example.learnenglish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnenglish.books.BooksActivity
import com.example.learnenglish.chat.ChatPeopleActivity
import com.example.learnenglish.chatbot.ChatActivity
import com.example.learnenglish.databinding.ActivityHomeBinding
import com.example.learnenglish.vocabulary.VocabularyActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.booksCardview.setOnClickListener {
            val intent = Intent(this,BooksActivity::class.java)
            startActivity(intent)
        }

        binding.chatbotCardview.setOnClickListener {
            val intent = Intent(this,
                ChatActivity::class.java)
            startActivity(intent)
        }

        binding.chatCardview.setOnClickListener {
            val intent = Intent(this,ChatPeopleActivity::class.java)
            startActivity(intent)
        }
        binding.vocabularyCardview.setOnClickListener {
            val intent = Intent(this,VocabularyActivity::class.java)
            startActivity(intent)
        }
    }
}