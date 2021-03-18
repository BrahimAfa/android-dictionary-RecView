package com.skydoves.dictionaryrecyclerview.model

import android.os.Parcel
import android.os.Parcelable

data class Words(val name: String?, val description: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Words> {
        override fun createFromParcel(parcel: Parcel): Words {
            return Words(parcel)
        }

        override fun newArray(size: Int): Array<Words?> {
            return arrayOfNulls(size)
        }
    }
}