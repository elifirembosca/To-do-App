package com.example.todouygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_kayit.*
import java.util.*

class KayitActivity : AppCompatActivity() {

    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)

        toolbarKayitActivity.title="Yapılacak İş Kayıt"
        setSupportActionBar(toolbarKayitActivity)

        vt= VeritabaniYardimcisi(context = this@KayitActivity)

        buttonKaydet.setOnClickListener {

            val yapilacak_is = editTextYapilacakIs.text.toString()

            kayit(yapilacak_is)
        }

    }

    fun kayit(yapilacak_is:String){
        Log.e("Yapilacak Kayıt","$yapilacak_is")

        Islerdao().isEkle(vt,yapilacak_is)

        startActivity(Intent(this@KayitActivity,MainActivity::class.java))
    }

}