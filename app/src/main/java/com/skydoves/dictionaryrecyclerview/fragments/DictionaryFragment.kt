package com.skydoves.dictionaryrecyclerview.fragments

import android.content.Context
import android.os.Bundle
import android.provider.UserDictionary
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.dictionaryrecyclerview.CustomActivity
import com.skydoves.dictionaryrecyclerview.ParentAdapter
import com.skydoves.dictionaryrecyclerview.R
import com.skydoves.dictionaryrecyclerview.interfaces.IHandleClick
import com.skydoves.dictionaryrecyclerview.model.Words
import com.skydoves.dictionaryrecyclerview.utils.readFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
private const val ARG_PARAM1 = "word"
class DictionaryFragment: Fragment() {

    var handleClick : IHandleClick? = null;
    var words : ArrayList<Words>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            words = it.getParcelableArrayList(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val recView = view.findViewById<RecyclerView>(R.id.list)
        with(recView) {
                layoutManager = LinearLayoutManager(context)
                adapter = words?.let { ParentAdapter(it,handleClick) }
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(words:ArrayList<Words>) =
                DictionaryFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_PARAM1, words)
                    }
                }
    }

}