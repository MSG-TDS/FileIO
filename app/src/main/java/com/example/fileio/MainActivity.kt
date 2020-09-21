package com.example.fileio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.nio.file.Files

class MainActivity : AppCompatActivity() {

    private var appPath = ""
    private var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appPath = filesDir.absolutePath
        fileName = "testText.text"

        btnRead.setOnClickListener {
            val result =  readFile()
            txtInput.setText(result.toString())
        }

        btnWrite.setOnClickListener {
            val content = txtInput.text.toString()
            writeFile(content)
            txtInput.text.clear()
        }
    }

    private fun writeFile(content : String) {

        val dir = File(appPath)

        // 경로가 없으면 생성
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val file = File("${this.appPath}/${this.fileName}")

        file.bufferedWriter().use{
            it.write(content)
        }
    }

    private fun readFile() : String? {

        var result: String? = ""

        val file = File("${this.appPath}/${this.fileName}")

        if (!file.exists()) return ""

        result = file.bufferedReader().readText()

        return result
    }
}