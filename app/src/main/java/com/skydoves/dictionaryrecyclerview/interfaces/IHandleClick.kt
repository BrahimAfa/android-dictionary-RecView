package com.skydoves.dictionaryrecyclerview.interfaces

import android.os.Parcelable
import com.skydoves.dictionaryrecyclerview.model.Words

interface IHandleClick{
    fun WordClicked(words: Words);
}

