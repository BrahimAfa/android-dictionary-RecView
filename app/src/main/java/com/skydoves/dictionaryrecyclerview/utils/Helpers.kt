package com.skydoves.dictionaryrecyclerview.utils

import android.content.Context
import com.skydoves.dictionaryrecyclerview.model.Words
import java.io.IOException

@Throws(IOException::class)

fun readFile(context: Context, fileName: String): ArrayList<Words> {
        val dict = arrayListOf<Words>()
        context.assets.open(fileName).use {file->
            file.reader(Charsets.UTF_8).forEachLine {
                if(it.isBlank() or  it.isEmpty()) return@forEachLine;
                var splitedLine = it.split(':')
                dict.add(Words(splitedLine[0].trim(),splitedLine[1].trim()));
            }
        }
    return dict;
}



