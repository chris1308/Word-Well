package com.amadeus.dictionary_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amadeus.dictionary_project.databinding.ItemRowBinding
import com.amadeus.dictionary_project.retrofit.Meaning

class MeaningAdapter(private var meaningList : List<Meaning>) : RecyclerView.Adapter<MeaningAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning){
            //BIND ALL MEANINGS
            binding.tvPartofspeech.text = meaning.partOfSpeech
            binding.definitionsTextview.text = meaning.definitions.joinToString("\n\n") {
                val idx = meaning.definitions.indexOf(it) + 1
                "$idx. ${it.definition}"
            }

            if (meaning.synonyms.isEmpty()){
                binding.synonymsTextview.visibility = View.INVISIBLE
                binding.synonymsTitleTextview.visibility = View.INVISIBLE
            }else {
                binding.synonymsTextview.visibility = View.VISIBLE
                binding.synonymsTitleTextview.visibility = View.VISIBLE
                binding.synonymsTextview.text = meaning.synonyms.joinToString(", ")
            }

            if (meaning.antonyms.isEmpty()){
                binding.antonymsTextview.visibility = View.INVISIBLE
                binding.antonymsTitleTextview.visibility = View.INVISIBLE
            }else {
                binding.antonymsTextview.visibility = View.VISIBLE
                binding.antonymsTitleTextview.visibility = View.VISIBLE
                binding.antonymsTextview.text = meaning.antonyms.joinToString(", ")
            }
        }
    }

    fun updateNewData(newList : List<Meaning>){
        meaningList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}