package com.junino.tourguide.utils

import android.content.Intent
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.junino.tourguide.MainActivity
import com.junino.tourguide.enums.TagEnum
import com.junino.tourguide.models.User
import com.junino.tourguide.models.Wisata
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

var isLoggedIn: Boolean = false
var user: User? = null

val fs
    get() = Firebase.firestore
val cUsers
    get() = fs.collection("users")

val cWisata
    get() = fs.collection("wisata")

fun saveUser (user: User) : Task<Void> {
    return cUsers.document(user.username!!).set(user).addOnSuccessListener {
        Log.d("Firestore", "User tersimpan")
    }.addOnFailureListener { result ->
        Log.d("Firestore", "Gagal simpan User")
    }
}

fun saveWisata(wisata: Wisata){
    cWisata
        .document(wisata.nama!!.replace(" ", ""))
        .set(wisata).addOnSuccessListener {
            print("FS Written")
        }
}

//fun loadWisata () : MutableList<Wisata>{
//
//}
