package com.example.todouygulamasi

import androidx.core.content.contentValuesOf

class Islerdao {

    fun tumIsler(vt:VeritabaniYardimcisi):ArrayList<Isler> {
        val db = vt.writableDatabase
        val islerListe=ArrayList<Isler>()
        val c = db.rawQuery("SELECT * FROM yapilacaklar",null)

        while (c.moveToNext()){
            val yapilacak =Isler(c.getInt(c.getColumnIndex("yapilacak_id")),
                c.getString(c.getColumnIndex("yapilacak_is")))
            islerListe.add(yapilacak)
        }
        return islerListe
    }

    fun isAra(vt:VeritabaniYardimcisi,aramaKelimesi:String) : ArrayList<Isler> {
        val db = vt.writableDatabase
        val islerListe = ArrayList<Isler>()
        val c = db.rawQuery("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%$aramaKelimesi%'", null)

        while (c.moveToNext()) {
            val yapilacak = Isler(c.getInt(c.getColumnIndex("yapilacak_id")),
                c.getString(c.getColumnIndex("yapilacak_is")))
            islerListe.add(yapilacak)
        }
        return islerListe
    }


    fun isSil(vt:VeritabaniYardimcisi,yapilacak_id:Int){
        val db = vt.writableDatabase
        db.delete("yapilacaklar","yapilacak_id=?", arrayOf(yapilacak_id.toString()))
        db.close()
    }

    fun isEkle(vt:VeritabaniYardimcisi,yapilacak_is:String){

        val db = vt.writableDatabase

        val values = contentValuesOf()
        values.put("yapilacak_is",yapilacak_is)

        db.insertOrThrow("yapilacaklar",null,values)
        db.close()
    }

    fun isGuncelle(vt:VeritabaniYardimcisi,yapilacak_id:Int,yapilacak_is: String){
        val db = vt.writableDatabase

        val values = contentValuesOf()
        values.put("yapilacak_is",yapilacak_is)

        db.update("yapilacaklar",values,"yapilacak_id=?", arrayOf(yapilacak_id.toString()))
        db.close()
    }

}