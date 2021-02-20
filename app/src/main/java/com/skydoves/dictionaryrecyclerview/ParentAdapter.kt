/*
 * Copyright (C) 2019 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.dictionaryrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.dictionaryrecyclerview.databinding.ItemSectionBinding
import com.skydoves.dictionaryrecyclerview.model.Words

class ParentAdapter :
  RecyclerView.Adapter<ParentAdapter.ParentViewHolder>(){

  private val words: ArrayList<Words> = arrayListOf()

  fun addWord(word: Words) {
    words.add(word)
    notifyItemChanged(words.lastIndex)
  }
  fun addWords(words: ArrayList<Words>) {
    this.words.addAll(words)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
    val binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ParentViewHolder(binding).apply {
      with(binding.root) {
        setOnClickListener { toggleLayout() }
      }
    }
  }

  override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
    val word = words[position]
    with(holder.binding.expandableLayout) {
      parentLayoutResource = R.layout.item_section_parent
      secondLayoutResource = R.layout.layout_second2
      duration = 200L
      parentLayout.findViewById<TextView>(R.id.title).text = word.name
      secondLayout.findViewById<TextView>(R.id.myText).text = word.description
    }
  }
  override fun getItemCount() = words.size

  class ParentViewHolder(val binding: ItemSectionBinding) :
    RecyclerView.ViewHolder(binding.root)
}
