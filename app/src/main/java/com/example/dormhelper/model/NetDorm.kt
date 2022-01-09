package com.example.dormhelper.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetDorm(
    @Json(name = "ADRES") val address: String,
    @Json(name = "HUISNR") val houseNumber: String?,
    @Json(name = "Bisnr.") val bisNmr: String?,
    @Json(name = "Busnr.") val busNmr: String?,
    @Json(name = "GEMEENTE") val city: String,
    @Json(name = "aantal kamers") val roomCount: Int,
    @Json(name = "KAD.AFD") val KadAfd: String,
    @Json(name = "KAD.SECTIE") val KadSection: String,
    @Json(name = "KAD.NR") val KadNr: String) : Parcelable {
}