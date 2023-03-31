package com.ezatpanah.roomdatabase_youtube.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ezatpanah.roomdatabase_youtube.adapter.NoteAdapter
import com.ezatpanah.roomdatabase_youtube.databinding.ActivityMainBinding
import com.ezatpanah.roomdatabase_youtube.db.NoteDatabase
import com.ezatpanah.roomdatabase_youtube.utils.Constants.NOTE_DATABASE

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // creating object of our database
    private val noteDB : NoteDatabase by lazy {
        Room.databaseBuilder(this,NoteDatabase::class.java,NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this,AddNoteActivity::class.java))
        }

    }

    // In future, Use LiveData in place of this function
    override fun onResume() {
        super.onResume()
        checkItem()
    }

    private fun checkItem(){
        binding.apply {
            // if the database is not empty then show the list of data
            if(noteDB.doa().getAllNotes().isNotEmpty()){
                rvNoteList.visibility= View.VISIBLE
                tvEmptyText.visibility=View.GONE
                noteAdapter.differ.submitList(noteDB.doa().getAllNotes())
                setupRecyclerView()
            }else{
                rvNoteList.visibility=View.GONE
                tvEmptyText.visibility=View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvNoteList.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=noteAdapter
        }

    }
}