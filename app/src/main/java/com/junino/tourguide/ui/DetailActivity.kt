package com.junino.tourguide.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.junino.tourguide.R
import com.junino.tourguide.databinding.ActivityDetailBinding
import com.junino.tourguide.models.Wisata
import com.junino.tourguide.utils.saveUser
import com.junino.tourguide.utils.user
import android.widget.Toast

import android.content.ActivityNotFoundException

import android.content.Intent
import android.net.Uri


class DetailActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val modelWisata = intent.getSerializableExtra("model") as Wisata
        val id = modelWisata.nama?.replace(" ", "")
        binding.textViewNamaWisata.text = modelWisata.nama
        binding.textViewJenisWisata.text = modelWisata.jenis
        binding.textViewAlamat.text = modelWisata.alamat
        binding.textViewPenjelasanWisata.text = modelWisata.deskripsi
        binding.buttonGoogle.setOnClickListener{
            val uri =
                "https://www.google.com/maps/dir/?api=1&destination=" + modelWisata.latitude + "," + modelWisata.longitude
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//            intent.setPackage("com.google.android.apps.maps")
//            try {
//                startActivity(intent)
//            } catch (ex: ActivityNotFoundException) {
//                try {
//                    val unrestrictedIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//                    startActivity(unrestrictedIntent)
//                } catch (innerEx: ActivityNotFoundException) {
//                    Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG)
//                        .show()
//                }
//            }
            startActivity(intent)
        }
        binding.buttonFavorit.text = if (user?.favorites?.contains(id) == true) "Batal Favorit" else "Tambah ke Favorit"
        binding.buttonFavorit.setOnClickListener{
            if (user?.favorites?.contains(id) == true) {
                user?.favorites?.remove(id)
            } else {
                user?.favorites?.add(id!!)
            }

            binding.buttonFavorit.text = if (user?.favorites?.contains(id) == true) "Batal Favorit" else "Tambah ke Favorit"
            saveUser(user!!)
        }
        setContentView(binding.root)

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_detail)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}