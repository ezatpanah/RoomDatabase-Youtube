package com.ezatpanah.roomdatabase_youtube.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun doa():NoteDao
}

// Article for abstract class : https://www.geeksforgeeks.org/kotlin-abstract-class/
// If we add/delete a new field in Entity class, then we must change our versionNumber
