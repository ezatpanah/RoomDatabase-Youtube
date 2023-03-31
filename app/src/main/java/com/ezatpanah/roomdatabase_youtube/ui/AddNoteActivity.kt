package com.ezatpanah.roomdatabase_youtube.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.ezatpanah.roomdatabase_youtube.R
import com.ezatpanah.roomdatabase_youtube.databinding.ActivityAddNoteBinding
import com.ezatpanah.roomdatabase_youtube.db.NoteDatabase
import com.ezatpanah.roomdatabase_youtube.db.NoteEntity
import com.ezatpanah.roomdatabase_youtube.utils.Constants.NOTE_DATABASE
import com.google.android.material.snackbar.Snackbar

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding

    // make an object of database to access the methods
    private val noteDB : NoteDatabase by lazy {
        Room.databaseBuilder(this,NoteDatabase::class.java,NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()

                /* if title and description of a note are not empty, then data can be inserted and
                note can be saved in the database */
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity= NoteEntity(0,title,desc)
                    noteDB.doa().insertNote(noteEntity)
                    finish()
                }
                else{
                    Snackbar.make(it,"Title and Description cannot be Empty",Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }
}