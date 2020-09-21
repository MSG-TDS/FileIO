package com.example.fileio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.nio.file.Files

class MainActivity : AppCompatActivity() {

    var appPath = ""
    var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appPath = filesDir.absolutePath
        fileName = "testText.text"

        btnRead.setOnClickListener {
            readFile()
        }

        btnWrite.setOnClickListener {
            writeFile()
        }
    }

    fun writeFile(){
        val dir = File(appPath)

        if(!dir.exists())
        {
            dir.mkdirs()
        }

        val writer = FileWriter("$appPath/$fileName")
        val buffered = BufferedWriter(writer)

        val txt = txtInput.text.toString()

        buffered.write(txt)
        txtInput.text.clear()

        buffered.close()
    }

    fun readFile (){
        val file = File("${this.appPath}/${this.fileName}")

        if(file.exists())
        {
            val reader = FileReader(file)
            val buffered = BufferedReader(reader)

            var temp : String? = ""
            val result = StringBuffer()

            while(true)
            {
                temp = buffered.readLine()
                if(temp == null) break
                else result.append(temp)
            }

            buffered.close()

            txtInput.setText(result.toString())
        }
    }
}