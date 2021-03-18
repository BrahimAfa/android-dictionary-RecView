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
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.dictionaryrecyclerview.databinding.ItemSectionBinding
import com.skydoves.dictionaryrecyclerview.interfaces.IHandleClick
import com.skydoves.dictionaryrecyclerview.model.Words

class ParentAdapter (private val words: ArrayList<Words>, private val handleClick: IHandleClick?,):
  RecyclerView.Adapter<ParentAdapter.ParentViewHolder>(){

  fun addWords(words: ArrayList<Words>) {
    words.addAll(words)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
   // val binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section,parent,false);
    return ParentViewHolder(view)
  }

  override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
    val word = words[position]
    holder.view.findViewById<TextView>(R.id.txtWord).text = word.name
    holder.itemView.findViewById<TextView>(R.id.txtWord).setOnClickListener {
      handleClick?.WordClicked(word);
    }
  }
  override fun getItemCount() = words.size

  class ParentViewHolder(val view: View) :
    RecyclerView.ViewHolder(view)
}
