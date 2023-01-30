package com.example.notion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notion.databinding.ItemRvBinding
import com.example.notion.models.MyNotion

class RvAdapter( var list: ArrayList<MyNotion>, val rvClick: RvClick): RecyclerView.Adapter<RvAdapter.Vh>(){
    inner class Vh(private val  itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myNotion: MyNotion, position: Int){
            itemRvBinding.textView.text = myNotion.note
            itemRvBinding.date.text = myNotion.date
            itemRvBinding.checkBox.isChecked = false

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RvClick{
        fun clickFloat(myNotion: MyNotion, position: Int)
    }



}