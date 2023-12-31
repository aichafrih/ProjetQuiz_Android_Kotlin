package com.isettozeur.projet
import java.io.Serializable
data class ResultModel(
    var timeRemain:Int,
    var type:String,
    val difficulty:String,
    val score:Double
):Serializable
