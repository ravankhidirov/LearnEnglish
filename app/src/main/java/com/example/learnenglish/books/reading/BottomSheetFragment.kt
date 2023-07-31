package com.example.learnenglish.books.reading

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.books.definition.DisplayAdapter
import com.example.learnenglish.books.definition.data.Result
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment (val myContext: Context,val definition:List<Result>?):BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_fragment,container,false)
    }




    // this fragment will be shown when a user clicks on a word to get definition


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var definitions = ArrayList<String>()
        var synonyms = ArrayList<String>()
        for (i in definition?.get(0)?.meanings?.get(0)?.definitions!!){
            definitions.add(i.definition)
        }
        for (j in definition?.get(0)?.meanings?.get(0)?.synonyms!!){
            synonyms.add(j)
        }
        val definitionRecyle = view.findViewById<RecyclerView>(R.id.definitionRecyle)
        val layoutManager = LinearLayoutManager(myContext, LinearLayoutManager.VERTICAL, false)
        val adapter = DisplayAdapter(definitions)
        definitionRecyle.layoutManager = layoutManager
        definitionRecyle.adapter = adapter

        val synonymsRecycle = view.findViewById<RecyclerView>(R.id.synonymsRecycle)
        val layoutManager1 = LinearLayoutManager(myContext, LinearLayoutManager.VERTICAL, false)
        val adapter1 = DisplayAdapter(synonyms)
        synonymsRecycle.layoutManager = layoutManager1
        synonymsRecycle.adapter = adapter1


        view.findViewById<TextView>(R.id.word).text = definition[0].word
        view.findViewById<TextView>(R.id.pronunciation).text = definition[0].phonetic
        view.findViewById<TextView>(R.id.partOfSpeech).text = definition[0].meanings[0]?.partOfSpeech

    }

}