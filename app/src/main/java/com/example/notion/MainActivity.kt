package com.example.notion

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notion.adapters.RvAdapter
import com.example.notion.databinding.ActivityMainBinding
import com.example.notion.databinding.ItemAddBinding
import com.example.notion.db.MyDB
import com.example.notion.models.MyNotion
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RvAdapter.RvClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var myDB: MyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showGif()

        myDB = MyDB(binding.root.context)
        rvAdapter = RvAdapter(myDB.readNote() as ArrayList<MyNotion>, this)
        binding.rv.adapter = rvAdapter

        binding.floating.setOnClickListener {
            addContactAdd()

        }


        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                if (dy > 10 && binding.floating.isShown) {
                    binding.floating.hide()
                }


                if (dy < -10 && !binding.floating.isShown) {
                    binding.floating.show()
                }


                if (!recyclerView.canScrollVertically(-1)) {
                    binding.floating.show()
                }
            }
        })

    }


    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    private fun addContactAdd() {
        val alertDialogLayoutBinding = ItemAddBinding.inflate(layoutInflater)
        val dialog: AlertDialog = AlertDialog.Builder(binding.root.context, R.style.MyMenuDialogTheme)
            .setView(alertDialogLayoutBinding.root)
            .setPositiveButton("OK", null)
            .setNegativeButton("Cancel", null)
            .setCancelable(true)
            .create()
        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)

            button.setOnClickListener {
                val name = alertDialogLayoutBinding.edtName.text.toString().trim()


                        dialog.cancel()
                        val user = MyNotion(
                            name,
                            SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").format(Date())
                        )
                        myDB.addNote(
                            user, binding.root.context
                        )
                rvAdapter.notifyDataSetChanged()
                rvAdapter.list.add(user)
            }



            }
        dialog.show()
        }



    override fun clickFloat(myNotion: MyNotion, position: Int) {

    }
    fun showGif(){
        val imageView: ImageView = findViewById(R.id.imageView)
        Glide.with(this).load(R.drawable.animation).into(imageView)
    }
}





    

   
