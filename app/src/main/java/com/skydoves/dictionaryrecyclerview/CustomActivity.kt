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

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.dictionaryrecyclerview.databinding.ActivityCustomBinding
import com.skydoves.dictionaryrecyclerview.model.Words
import com.skydoves.dictionaryrecyclerview.utils.readFile
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

class CustomActivity : AppCompatActivity() {

  private val adapter = ParentAdapter()
  private val ctx = this;
  private lateinit var  spinner : ProgressBar;
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityCustomBinding.inflate(layoutInflater)
    setContentView(binding.root)
    spinner = findViewById(R.id.progressBar1);

    binding.recyclerView.adapter = adapter
    spinner.visibility = View.VISIBLE;
    val def = GlobalScope.launch(Dispatchers.IO) {
      delay(2000); // simulate a long running operation
      val words = readFile(ctx,"dictionary.txt")
      handleWords(words);
    }


  }
  fun handleWords(words:ArrayList<Words>){
    GlobalScope.launch(Dispatchers.Main) {
      spinner.visibility = View.GONE;
      adapter.addWords(words);
    }
  }

}
