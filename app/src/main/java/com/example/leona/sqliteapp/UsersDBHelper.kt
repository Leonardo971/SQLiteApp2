package com.example.leona.sqliteapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsersDBHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)
{

    companion object {
        val DATABASE_VERSION=1
        val DATABASE_NAME="Usuarios.db"

 //crear una table en bdd
        private val SQL_CREATE_ENTRIES="CREATE TABLE Usuario("+"idUsuario text PRIMARY KEY,"+"nomUsuario text,"+"edadUsuario text)"

        private val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS Usuario"
    }

    //metodos obligatorios Oncreate,onUpgrade,onDpwngrade
    override fun onCreate(db: SQLiteDatabase)
    {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        onUpgrade(db,oldVersion, newVersion)
    }

    //Rutina generiaca para mandar ejecutar un inserte , update,delete en la base de datos

    fun Ejecuta(sentencia:String): Int
    {
        try{
            val db=writableDatabase
            db.execSQL(sentencia)
            db.close()
            return 1

            }
        catch(ex:Exception)
        {
            return 0
        }
    }

    //Rutina para mandar ejecutar una consulta en base de datos

    fun Consulta(select:String):Cursor?
    {
        try {
            val db=readableDatabase
            var cur:Cursor=db.rawQuery(select,null)
            return cur
        }
        catch (ex:Exception){
            val cur:Cursor?=null

            return cur

        }
    }
}