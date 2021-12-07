package com.junino.tourguide.models

import com.junino.tourguide.enums.TagEnum
import java.io.Serializable

data class User(
    val username : String? = "",
    val email : String? = "",
    val password : String? = "",
    val favorites : MutableList<String> = mutableListOf()
) : Serializable