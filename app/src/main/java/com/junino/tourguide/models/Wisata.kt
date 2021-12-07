package com.junino.tourguide.models

import com.junino.tourguide.enums.TagEnum
import java.io.Serializable

data class Wisata(
    val imageWisata : String? = "non",
    var nama : String? = null,
    var deskripsi : String? = null,
    var jenis : String? = null,
    var alamat : String? = null,
    var latitude : Double? = null,
    var longitude : Double? = null,
    var tags : MutableList <TagEnum>? = null
) : Serializable