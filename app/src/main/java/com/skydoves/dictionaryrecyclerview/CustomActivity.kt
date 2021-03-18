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
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.dictionaryrecyclerview.databinding.ActivityCustomBinding
import com.skydoves.dictionaryrecyclerview.fragments.DictionaryFragment
import com.skydoves.dictionaryrecyclerview.fragments.FragmentDetails
import com.skydoves.dictionaryrecyclerview.interfaces.IHandleClick
import com.skydoves.dictionaryrecyclerview.model.Words
import com.skydoves.dictionaryrecyclerview.utils.readFile
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

class CustomActivity : AppCompatActivity(), IHandleClick {

  private val ctx = this;
  private lateinit var spinner: ProgressBar;
  private lateinit var dictFragment : DictionaryFragment
  var binding : ActivityCustomBinding? = null;
  private var IS_LANDSCAP = false;
  val TAG = "CustomActivity"
  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityCustomBinding.inflate(layoutInflater)

    setContentView(binding?.root)

    IS_LANDSCAP = binding!!.fragmentHolderDetails!=null;

    spinner = findViewById(R.id.progressBar1);
    if (savedInstanceState == null) {
    spinner.visibility = View.VISIBLE;
    GlobalScope.launch(Dispatchers.IO) {
      // delay(2000); // simulate a long running operation
      val words = readFile(ctx, "dictionary1.txt")
      GlobalScope.launch(Dispatchers.Main) {
        spinner.visibility = View.GONE;
        dictFragment = DictionaryFragment.newInstance(words);
        dictFragment.handleClick = this@CustomActivity;
        supportFragmentManager
              // 3
              .beginTransaction()
              // 4
              .add(R.id.fragmentHolder, dictFragment, "dictionaryWords")
              // 5
              .commit()
        }
      }
    } else {
      val fragmentWord = supportFragmentManager.findFragmentById(R.id.fragmentHolder)
      if(fragmentWord is DictionaryFragment){
        dictFragment =  fragmentWord
        dictFragment.handleClick = this;
      }
    }
  }

  override fun WordClicked(word:Words) {
    Log.e(TAG, "WordClicked: CLIIICKEF $IS_LANDSCAP")
    if(IS_LANDSCAP){
      supportFragmentManager
              .beginTransaction()
              .replace(R.id.fragmentHolderDetails, FragmentDetails.newInstance(word), "FragmentDetails")
              .commit()
    } else{
      Log.e(TAG, "WordClicked: "+word.name, )
      supportFragmentManager

              .beginTransaction()
              .addToBackStack("FragmentDetails")
              .replace(R.id.fragmentHolder, FragmentDetails.newInstance(word), "FragmentDetails")
              .commit()
    }
    }

}

