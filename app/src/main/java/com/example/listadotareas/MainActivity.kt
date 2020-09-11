package com.example.listadotareas

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readFile()
    }


    fun addTask( v: View){
        if(task.text.isNotEmpty()){
            val output = PrintStream(openFileOutput("task.txt", Activity.MODE_APPEND))
            output.println("${task.text}")
            Log.v("El Tag", "task: $task , task.text: ${task.text}")
            output.close()
            task.text.clear()
            readFile()
        }else{
            Toast.makeText(this, "Type a task", Toast.LENGTH_LONG).show()
        }
    }

    fun readFile(){

        val f = File(filesDir, "task.txt")
        if(f.exists()){
            val arrayListTask = arrayListOf<String>()
            //Read the file
            val input = Scanner(openFileInput("task.txt"))
            while(input.hasNextLine()){
                val i = input.nextLine()
                arrayListTask.add(i)
            }
            createList(arrayListTask)
        }else{
            f.createNewFile()
            readFile()
        }
    }

    fun createList(arrayListTask : ArrayList<String>){
        // Create the list
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListTask)
        listTask.adapter = adapter
    }



}