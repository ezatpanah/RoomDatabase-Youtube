package com.ezatpanah.roomdatabase_youtube.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezatpanah.roomdatabase_youtube.utils.Constants.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId :Int,
    @ColumnInfo(name = "note_title")
    val noteTitle:String,
    @ColumnInfo(name = "note_desc")
    val noteDesc : String
)

// It has to be noted that Entity file is created once.
/* Each room entity must define a Primary key that uniquely identifies each row in the database.
   Basically if a table has 3 columns : userId, userName, userAddress, then this table is defined as
   EntityClass(Modal class) in RoomDb */