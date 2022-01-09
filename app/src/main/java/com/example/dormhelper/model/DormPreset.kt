package com.example.dormhelper.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "dorm_table")
data class DormPreset (

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var nameOfPreset: String,

    var nameOfUser: String,

    @ColumnInfo(name = "size")
    var size: Int,

    @ColumnInfo(name = "roomCount")
    var roomCount: Int,

    @ColumnInfo(name = "has_a_bathroom")
    var hasBathroom: Boolean,

    @ColumnInfo(name = "has_a_sink")
    var hasSink: Boolean,

    @ColumnInfo(name = "has_a_kitchen")
    var hasKitchen: Boolean,

    @ColumnInfo(name = "is_furnitured")
    var isFurnished: Boolean,
): Parcelable {
}