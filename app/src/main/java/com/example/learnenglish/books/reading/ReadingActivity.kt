package com.example.learnenglish.books.reading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learnenglish.books.definition.view_model.ReadingBooksActivityViewModel
import com.example.learnenglish.books.text_processing.TextExtractor
import com.example.learnenglish.databinding.ActivityReadingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReadingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReadingBinding
    private lateinit var viewModel:ReadingBooksActivityViewModel
    private lateinit var bottomSheetFragment: BottomSheetFragment
    private val bottomSheetDialogs: MutableList<BottomSheetDialogFragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionContent: String? = intent.getStringExtra("sectionContent")
        val sectionNumber: String = "Section " + intent.getStringExtra("sectionNumber")
        binding.sectionContent.text = sectionContent
        binding.sectionNumber.text = sectionNumber


        val text = sectionContent.toString()




        // extracting words from content
        val words = TextExtractor().extractWordsFromText(text)
        binding.sectionContent.movementMethod = LinkMovementMethod.getInstance()
        val spannableString = SpannableString(text)


        viewModel = ViewModelProvider(this)[ReadingBooksActivityViewModel::class.java]





        // putting onclicklisteners on words

        for (word in words) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    viewModel.getDefinitions(this@ReadingActivity,word)
                    viewModel.observeDefinition().observe(this@ReadingActivity, Observer { definition->
                        bottomSheetFragment = BottomSheetFragment(this@ReadingActivity,definition)
                        bottomSheetDialogs.add(bottomSheetFragment)
                        bottomSheetFragment.show(supportFragmentManager,"BottomSheetDialog")
                        if (bottomSheetDialogs.size == 2)
                        {
                            val bottomSheetFragmentToRemove = bottomSheetDialogs.getOrNull(0)
                            bottomSheetFragmentToRemove?.dismiss()
                            bottomSheetDialogs.remove(bottomSheetFragmentToRemove)
                        }
                    })
                }
            }
            val startIndex = text.indexOf(word)
            val endIndex = startIndex + word.length
            spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.sectionContent.text = spannableString
    }

}