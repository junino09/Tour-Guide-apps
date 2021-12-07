package com.junino.tourguide.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.junino.tourguide.R
import com.junino.tourguide.models.Wisata
import com.junino.tourguide.ui.DetailActivity

class WisataRowAdapter(
    val context : Context,
    private val onListItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<WisataRowAdapter.WisataRowViewHolder>() {

    private val wisataList = ArrayList<Wisata>()

    @SuppressLint("NotifyDataSetChanged")
    fun setDataWisata(data : ArrayList<Wisata>){
        if (data.isEmpty()) return
        wisataList.clear()
        wisataList.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataRowViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_wisata, parent, false)
        return WisataRowViewHolder(view, onListItemClicked)
    }

    override fun onBindViewHolder(holder: WisataRowViewHolder, position: Int) {
       val wisata = wisataList[position]
       holder.image.setImageResource(R.mipmap.ic_launcher)
        holder.nama.text = wisata.nama
        holder.deskripsi.text = wisata.deskripsi
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }

    class WisataRowViewHolder(
        itemView: View,
        private val onListItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var image:ImageView = itemView.findViewById(R.id.imageViewWisata)
        var nama:TextView = itemView.findViewById(R.id.textViewNama)
        var deskripsi:TextView = itemView.findViewById(R.id.textViewDeskripsi)
        var cardView:CardView = itemView.findViewById(R.id.cardView)

        init {
            //itemView.setOnClickListener(this)
            cardView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            onListItemClicked(pos)
        }

    }
}