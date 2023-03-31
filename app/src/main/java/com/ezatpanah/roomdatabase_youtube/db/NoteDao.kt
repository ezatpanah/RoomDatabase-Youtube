package com.ezatpanah.roomdatabase_youtube.db

import androidx.room.*
import com.ezatpanah.roomdatabase_youtube.utils.Constants.NOTE_TABLE

// It has to be noted that DAO file is created once.
// We make the object of our database file and call below methods through Interface.

@Dao
interface NoteDao {

    // onConflict is used for replacing old data and continue the transaction with the new data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    // getting all the notes and showing them to the recyclerView in the MainActivity
    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId DESC")
    fun getAllNotes() : MutableList<NoteEntity>

    // selecting one note at a time
    @Query("SELECT * FROM $NOTE_TABLE WHERE noteId LIKE :id")
    fun getNote(id : Int) : NoteEntity

}