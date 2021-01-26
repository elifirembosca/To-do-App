package com.example.todouygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_detay.*
import java.util.*

class DetayActivity : AppCompatActivity() {

    private lateinit var yapilacak:Isler
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        toolbarDetayActivity.title="Yapılacak İş Detay"
        setSupportActionBar(toolbarDetayActivity)

        vt = VeritabaniYardimcisi(this@DetayActivity)

        yapilacak = intent.getSerializableExtra("nesne") as Isler

        editTextYapilacakIs.setText(yapilacak.yapilacak_is)


        buttonGuncelle.setOnClickListener {
            val yapilacak_is = editTextYapilacakIs.text.toString()

            guncelle(yapilacak.yapilacak_id,yapilacak_is)
        }

    }

    fun guncelle(yapilacak_id:Int,yapilacak_is:String){
        Log.e("İş Güncelle","$yapilacak_is")

        Islerdao().isGuncelle(vt,yapilacak_id,yapilacak_is)

        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
    }
}