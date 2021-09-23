package com.felipepolo.pokedex.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar




fun View.ShowIf(condition: Boolean){
    if (condition){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

fun LottieAnimationView.setupSocialIf(uri: String, clickListener: View.OnClickListener ) {
    if (uri.isNotEmpty()){
        this.visibility = View.VISIBLE
        this.setOnClickListener(clickListener)
    }else{
        (this.parent as ViewGroup).removeView(this)
    }
}

fun View.ShowSnack( msj: String){
    Snackbar.make(this, msj, Snackbar.LENGTH_LONG)
        .setAction("Ok") { }
        .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
        .show()
}

fun ImageView.setImageFromUrl(url:String){
    Glide.with(this.context).load(url).fitCenter().into(this)
}