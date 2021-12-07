package com.junino.tourguide.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.junino.tourguide.enums.TagEnum
import com.junino.tourguide.models.Wisata

class HomeViewModel : ViewModel() {

    private val firestore = Firebase.firestore

    private val _text = MutableLiveData<String>().apply {
        value = "Home"
    }
    val text: LiveData<String> = _text

    fun showData() {
        var test = MutableLiveData<Wisata>()


    }
    val testData : MutableList<Wisata> = mutableListOf(
        Wisata(
            nama = "Pantai Senggigi",
            deskripsi = "Pantai yang paling banyak dikunjungi di Lombok",
            jenis = "pantai",
            alamat = "senggigi",
            latitude = -8.500835,
            longitude = 116.045494,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Gunung Rinjani",
            deskripsi = "Gunung tertinggi di Lombok",
            jenis = "gunung",
            alamat = "sembalun",
            latitude = -8.411804,
            longitude = 116.458550,
            tags = mutableListOf(
                TagEnum.GUNUNG_BUKIT
            )
        ),
        Wisata(
            nama = "Sate Rembiga",
            deskripsi = "Sate daging",
            jenis = "kuliner",
            alamat = "rembiga",
            latitude = -8.561428018897278,
            longitude = 116.1094525199632,
            tags = mutableListOf(
                TagEnum.KULINER
            )
        ),
        Wisata(
            nama = "Pantai Kuta",
            deskripsi = "Pantai di daerah kuta",
            jenis = "Pantai",
            alamat = "Lombok Tengah",
            latitude = -8.893321,
            longitude = 116.282140,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Nasi Puyung Inaq Esun",
            deskripsi = "Nasi Puyung",
            jenis = "Kuliner",
            alamat = "Lombok Tengah",
            latitude = -8.767600114051538,
            longitude = 116.26535091295484,
            tags = mutableListOf(
                TagEnum.KULINER
            )
        ),
        Wisata(
            nama = "Air Terjun Benang Kelambu",
            deskripsi = "Air Terjun",
            jenis = "Air",
            alamat = "Lombok Tengah",
            latitude = -8.532215592014909,
            longitude = 116.3370065976127,
            tags = mutableListOf(
                TagEnum.AIRTERJUN
            )
        ),
        Wisata(
            nama = "Air Terjun Tiu Kelep",
            deskripsi = "Air Terjun",
            jenis = "Air",
            alamat = "Senaru",
            latitude = -8.301097183364796,
            longitude = 116.40733446693,
            tags = mutableListOf(
                TagEnum.AIRTERJUN
            )
        ),
        Wisata(
            nama = "Bukit Bengkaung",
            deskripsi = "Bukit",
            jenis = "Bukit",
            alamat = "Lombok Barat",
            latitude = -8.500157462065122,
            longitude = 116.09036174172975,
            tags = mutableListOf(
                TagEnum.GUNUNG_BUKIT
            )
        ),
        Wisata(
            nama = "Bukit Merese",
            deskripsi = "Bukit",
            jenis = "Bukit",
            alamat = "Lombok Tengah",
            latitude = -8.913781503995459,
            longitude = 116.31899556877975,
            tags = mutableListOf(
                TagEnum.GUNUNG_BUKIT
            )
        ),
        Wisata(
            nama = "Rumah makan Kania",
            deskripsi = "rumah makan taliwang",
            jenis = "Kuliner",
            alamat = "Mataram",
            latitude = -8.589002955588924,
            longitude = 116.12482489576678,
            tags = mutableListOf(
                TagEnum.KULINER
            )
        ),
        Wisata(
            nama = "Gili Trawangan",
            deskripsi = "Pulau",
            jenis = "Pulau",
            alamat = "Pemenang",
            latitude = -8.3478889045606,
            longitude = 116.03839479630977,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Gili Meno",
            deskripsi = "Pulau",
            jenis = "Pulau",
            alamat = "Pemenang",
            latitude = -8.350903598803754,
            longitude = 116.05753503825702,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Gili Air",
            deskripsi = "Pulau",
            jenis = "Pulau",
            alamat = "Pemenang",
            latitude = -8.358584131667648,
            longitude =  116.08140524328978,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Pusuk",
            deskripsi = "Bukit Pusuk",
            jenis = "Pusuk",
            alamat = "Pemenang",
            latitude = -8.46402092898182,
            longitude =  116.08488676494272,
            tags = mutableListOf(
                TagEnum.GUNUNG_BUKIT
            )
        ),
        Wisata(
            nama = "Suranadi",
            deskripsi = "Suranadi",
            jenis = "Kuliner",
            alamat = "Lombok Barat",
            latitude = -8.569586702341107,
            longitude =  116.23207771375499,
            tags = mutableListOf(
                TagEnum.KULINER
            )
        ),
        Wisata(
            nama = "Udayana",
            deskripsi = "Udayana",
            jenis = "Kuliner",
            alamat = "Mataram",
            latitude = -8.574291553004846,
            longitude =  116.10230968227228,
            tags = mutableListOf(
                TagEnum.KULINER
            )
        ),
        Wisata(
            nama = "Pantai Ampenan",
            deskripsi = "Pantai",
            jenis = "Pantai",
            alamat = "Ampenan",
            latitude = -8.569460542048057,
            longitude =  116.07237797503208,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Pantai Semeti",
            deskripsi = "Pantai",
            jenis = "Pantai",
            alamat = "Lombok Tengah",
            latitude = -8.891334495093261,
            longitude =  116.15879416877964,
            tags = mutableListOf(
                TagEnum.PANTAI
            )
        ),
        Wisata(
            nama = "Bukit Pergasingan",
            deskripsi = "Bukit",
            jenis = "Bukit",
            alamat = "Sembalun",
            latitude = -8.34493590840686,
            longitude =  116.53332814732624,
            tags = mutableListOf(
                TagEnum.GUNUNG_BUKIT
            )
        ),
        Wisata(
            nama = "Bukit Nanggi",
            deskripsi = "Bukit",
            jenis = "Bukit",
            alamat = "Sembalun",
            latitude = -8.390735578821683,
            longitude =  116.57324828894403,
            tags = mutableListOf(
                TagEnum.GUNUNG_BUKIT
            )
        )
    )

    val serverData : MutableList<Wisata> = mutableListOf()
}