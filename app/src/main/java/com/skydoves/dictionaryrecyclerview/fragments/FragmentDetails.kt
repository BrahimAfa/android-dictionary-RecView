package com.skydoves.dictionaryrecyclerview.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.skydoves.dictionaryrecyclerview.R
import com.skydoves.dictionaryrecyclerview.model.Words


private const val ARG_WORD = "word"

class FragmentDetails : Fragment() {
    private var word:Words? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            word = it.getParcelable(ARG_WORD)
        }
        Log.e("frag detai", "onCreate: ", )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fragment_details, container, false)
        view.findViewById<TextView>(R.id.txtFragWord).text = "${word?.name}\n\n${word?.description}";
       // view.findViewById<TextView>(R.id.txtFragDescription).text = word?.description
        return view
    }
    companion object {

        @JvmStatic
        fun newInstance(word:Words) =
                FragmentDetails().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_WORD, word)
                    }
                }
    }
}