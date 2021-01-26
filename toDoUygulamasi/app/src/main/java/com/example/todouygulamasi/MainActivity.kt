package com.example.todouygulamasi

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.satir_tasarim.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var islerListe: ArrayList<Isler>
    private lateinit var adapter: IslerAdapter
    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        veritabaniKopyala()

        toolbarMainActivity.title = "Yapılacaklar"
        setSupportActionBar(toolbarMainActivity)

        rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        vt = VeritabaniYardimcisi(this@MainActivity)
        tumIsleriAl()

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, KayitActivity::class.java))

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_arama_menu, menu)
        val item =menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Gönderilen Arama Sonucu", query)
        aramaYap(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Harf Girdikçe Sonuç", newText)
        aramaYap(newText)
        return true
    }


    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)

        try{

            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun tumIsleriAl(){

        islerListe=Islerdao().tumIsler(vt)

        adapter = IslerAdapter(this@MainActivity, islerListe, vt)
        rv.adapter = adapter
    }

    fun aramaYap(aramaKelimesi: String){

        islerListe=Islerdao().isAra(vt, aramaKelimesi)

        adapter = IslerAdapter(this@MainActivity, islerListe, vt)
        rv.adapter = adapter
    }


}