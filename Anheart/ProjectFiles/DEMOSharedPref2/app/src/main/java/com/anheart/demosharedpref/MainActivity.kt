package com.anheart.demosharedpref

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var sName: String? = ""
    private var sRollNo: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("SP_info", Context.MODE_PRIVATE)

        btn_submit.setOnClickListener {
            val name = et_name.text.toString()
            val rollNo = et_rollNo.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("NAME",name)
            editor.putString("ROLLNO",rollNo)

            editor.apply()

            tv_info.visibility = View.VISIBLE
            tv_info.text = "I will remember you \n $name"

            Toast.makeText(this@MainActivity, "Saved",Toast.LENGTH_SHORT).show()
        }

        sName = sharedPreferences.getString("NAME","")
        sRollNo = sharedPreferences.getString("ROLLNO","")

        if(sName!!.isNotEmpty() && sRollNo!!.isNotEmpty()){

            tv_info.visibility = View.VISIBLE
            tv_info.text = "Welcome back \n $sRollNo --> $sName"

            et_name.visibility = View.GONE
            et_rollNo.visibility = View.GONE
            btn_submit.visibility = View.GONE
        }

    }
}