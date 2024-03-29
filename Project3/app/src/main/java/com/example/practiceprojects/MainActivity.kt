package com.example.practiceprojects

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceprojects.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    //    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter

    //    var ediText: EditText? = null
//    var btnPressMe: Button? = null
//    var text: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()


//        ediText = findViewById(R.id.ediText)
//        btnPressMe = findViewById(R.id.btnPressMe)
//        text = findViewById(R.id.textView)

        dataBinding.apply {
            btnPressMe?.setOnClickListener {
                val str: String = ediText?.text.toString()
                storeSharePrefData(str)
                displayPrefData()
            }
        }


        //1- create instances

        dataBinding.rvVaccines.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        //2- creating the list
        var vaccineList: ArrayList<VaccineModel> = ArrayList()
        val v1 = VaccineModel(R.drawable.ic_launcher_foreground, "First Vaccine")
        val v2 = VaccineModel(R.drawable.ic_launcher_foreground, "Second Vacine")
        val v3 = VaccineModel(R.drawable.ic_launcher_foreground, "Third Vacine")
        val v4 = VaccineModel(R.drawable.ic_launcher_foreground, "Fourth Vacine")
        vaccineList.add(v1)
        vaccineList.add(v2)
        vaccineList.add(v3)
        vaccineList.add(v4)


        //3- adapter
        adapter = MyAdapter(vaccineList)
        dataBinding.rvVaccines.adapter = adapter

    }

    private fun displayPrefData() {
        val sharePref: SharedPreferences = baseContext.getSharedPreferences(
            "mySharePref",
            Context.MODE_PRIVATE
        )
        var str = sharePref.getString("ediTextData", "No data")

        dataBinding.textView?.text = str.toString()
    }

    private fun storeSharePrefData(str: String) {
        val sharePref = baseContext.getSharedPreferences("mySharePref", Context.MODE_PRIVATE)

        val editor = sharePref.edit()

        editor.putString("ediTextData", str)
        editor.apply()
    }
}