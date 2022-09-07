package com.example.recyclerwithnavigationcomponent.model

data class ListPhone(
    val status : Boolean,
    val data : MutableList<Phone>
)

data class Phone(
    val brand_id :Int,
    val brand_name :String,
    val brand_slug : String,
    val device_count : Int,
    val detail :String
)