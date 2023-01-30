package com.example.notion.db

import com.example.notion.models.MyNotion

interface MyDBInterface {
    fun addNotes(myNotion: MyNotion)
    fun getAllNotes():List<MyNotion>
}