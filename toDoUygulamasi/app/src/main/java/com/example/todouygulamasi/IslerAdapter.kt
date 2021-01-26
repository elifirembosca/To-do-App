package com.example.todouygulamasi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class IslerAdapter(var mContext: Context, var islerliste: ArrayList<Isler>, var vt: VeritabaniYardimcisi): RecyclerView.Adapter<IslerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim){

        var satir_card:CardView
        var satir_yazi:TextView
        var satir_resim:ImageView

        init {
            satir_card = tasarim.findViewById(R.id.satir_card)
            satir_yazi = tasarim.findViewById(R.id.satir_yazi)
            satir_resim = tasarim.findViewById(R.id.satir_resim)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var yapilacak =islerliste.get(position)

        holder.satir_yazi.text="${yapilacak.yapilacak_is}"

        holder.satir_resim.setOnClickListener{
            Toast.makeText(mContext, "${yapilacak.yapilacak_is} silindi!", Toast.LENGTH_SHORT).show()

            Islerdao().isSil(vt, yapilacak.yapilacak_id)

            islerliste=Islerdao().tumIsler(vt)
            notifyDataSetChanged()
        }
        holder.satir_card.setOnClickListener {
            val intent = Intent(mContext, DetayActivity::class.java)
            intent.putExtra("nesne", yapilacak)
            mContext.startActivity(intent)
        }

        val androidColors = mContext.getResources().getIntArray(R.array.androidcolors)
        val randomAndroidColor:Int = androidColors[Random().nextInt(androidColors.size)]
        holder.satir_card.setCardBackgroundColor(randomAndroidColor)

    }

    override fun getItemCount(): Int {
        return islerliste.size
    }


}