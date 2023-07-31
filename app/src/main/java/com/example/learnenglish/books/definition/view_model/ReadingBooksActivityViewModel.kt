package com.example.learnenglish.books.definition.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.learnenglish.books.definition.data.Result
import com.example.learnenglish.books.definition.network.RetrofitClient

class ReadingBooksActivityViewModel : ViewModel() {

    var definition = MutableLiveData<List<Result>>()

    fun getDefinitions(context: Context,prompt:String){
        val call: Call<List<Result>?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getDefinition(prompt)
        call?.enqueue(object: Callback<List<Result>?> {
            override fun onResponse(call: Call<List<Result>?>, response: Response<List<Result>?>) {
                val definition:List<Result>? = response.body() as List<Result>
                this@ReadingBooksActivityViewModel.definition.postValue(definition)
            }

            override fun onFailure(call: Call<List<Result>?>, t: Throwable) {
                Toast.makeText(context, "An error has occured", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun observeDefinition(): LiveData<List<Result>>
    {
        return definition
    }
}