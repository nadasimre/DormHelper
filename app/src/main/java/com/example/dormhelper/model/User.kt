package com.example.dormhelper.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "username")
    var userName: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "email")
    var email: String
    ): Parcelable {
}