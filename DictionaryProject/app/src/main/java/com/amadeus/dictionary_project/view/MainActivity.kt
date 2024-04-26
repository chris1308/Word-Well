package com.amadeus.dictionary_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amadeus.dictionary_project.adapter.MeaningAdapter
import com.amadeus.dictionary_project.databinding.ActivityMainBinding
import com.amadeus.dictionary_project.retrofit.RetrofitInstance
import com.amadeus.dictionary_project.retrofit.WordResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get()= _binding!!
    lateinit var adapter: MeaningAdapter
    val ioScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            val textInput = binding.searchInput.text.toString()
            getMeaning(textInput)
        }

        //SETUP RV
        binding.meaningRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MeaningAdapter(emptyList())
        binding.meaningRecyclerview.adapter = adapter
    }

    private fun getMeaning(word:String){
        setProgressBar(true) //loading selagi mencari dari api
        ioScope.launch {
            try {
                val response = RetrofitInstance.dictionaryApi.getMeaning(word)
                if (response.body() == null){
                    throw (Exception())
                }

//            Log.i("Response API",response.body().toString())
                runOnUiThread{
                    setProgressBar(false)
                    response.body()?.first()?.let{
                        setMeaning(it)
                    }
                }
            }catch (e : Exception){
                runOnUiThread {
                    setProgressBar(false)
                    Toast.makeText(applicationContext,"Something went wrong!",Toast.LENGTH_SHORT).show()
                }
            }


        }

    }

    private fun setMeaning(result : WordResult){
        //DISPLAY RESULT TO SCREEN
        binding.wordTextview.text = result.word
        binding.phoneticTextview.text = result.phonetic
        adapter.updateNewData(result.meanings)
    }
    private fun setProgressBar(loading: Boolean){
        if (loading){
            //masih loading, tampilkan progressbar dan bikin search btn invisible
            binding.searchBtn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.searchBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}