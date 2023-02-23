package com.kshitij.ecommerceapp.ui.dashboard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    var id : Int,
    var title : String,
    var price : Float,
    var description : String,
    var category : String,
    var image : String
) : Parcelable