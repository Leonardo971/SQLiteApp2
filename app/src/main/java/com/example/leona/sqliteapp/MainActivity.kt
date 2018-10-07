package com.example.leona.sqliteapp

import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var usersDBHelper: UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersDBHelper=UsersDBHelper(this)
    }


    fun addUser(v:View)
    {
        var userid= this.editText_userid.text.toString()
        var name= this.editText_name.text.toString()
        var age= this.editText_age.text.toString()


        var sentencia = "Insert Into Usuario(idUsuario,nomUsuario,edadUsuario) values('$userid','$name','$age')"
        var result=usersDBHelper.Ejecuta(sentencia)
        //clear all editext

        if (result==1)
        {
            this.editText_age.setText("")
            this.editText_name.setText("")
            this.editText_userid.setText("")
            this.textView.text="Added user:"+result
            this.lll_entries.removeAllViews()
        }

        else
            Toast.makeText(this,"Error:No se inserto el usuario",Toast.LENGTH_LONG).show()
           // Toast.makeText(this,result.toString(),Toast.LENGTH_SHORT).show()

    }

    fun deleteUser(v:View)
    {
        var  userid=this.editText_userid.text.toString()
        val sentencia:String="Delete from Usuario where idUsuario='$userid'"
        val result=usersDBHelper.Ejecuta(sentencia)

        if (result==1)
        {
            this.textView.text="Deleted  user:"+result
            this.lll_entries.removeAllViews()
        }

        else
            Toast.makeText(this,"Error:No se Elimino el usuario",Toast.LENGTH_LONG).show()
    }

    fun showAllUser(v:View)
    {
        val select ="Select * from Usuario"
        val cur:Cursor?=usersDBHelper.Consulta(select)
        this.lll_entries.removeAllViews()

        var name:String
        var  age:String

        if (cur!!.moveToFirst())
        {
            while (cur.isAfterLast==false)
            {

                name= cur.getString(cur.getColumnIndex("nomUsuario"))
                age= cur.getString(cur.getColumnIndex("edadUsuario"))
                var  tv_user= TextView(this)

                tv_user.textSize=20F
                tv_user.text=name.toString()+"-"+age.toString()
                this.lll_entries.addView(tv_user)
                cur.moveToNext()
            }
        }
    }
}
