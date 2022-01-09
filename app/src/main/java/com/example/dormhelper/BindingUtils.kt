package com.example.dormhelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dormhelper.model.DormPreset
import com.example.dormhelper.model.NetDorm
import com.example.dormhelper.screens.network.dormsfromnetwork.DormAdapter
import com.example.dormhelper.screens.network.dormsfromnetwork.DormApiStatus

@BindingAdapter("nameOfPreset")
fun TextView.setNameOfPreset(item: DormPreset) {
    text = item.nameOfPreset
}

@BindingAdapter("dormImage")
fun ImageView.setDormImage(item: DormPreset) {
    setImageResource(when (item.roomCount) {
        0 -> R.drawable.incomplete
        1 -> R.drawable.room
        2 -> R.drawable.house
        3 -> R.drawable.house
        4 -> R.drawable.house
        5 -> R.drawable.house
        6 -> R.drawable.house
        7 -> R.drawable.house
        8 -> R.drawable.house
        9 -> R.drawable.house
        10 -> R.drawable.house
        else -> R.drawable.mansion
    })
}

@BindingAdapter("dormApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: DormApiStatus?) {
    when (status) {
        DormApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_anim)
        }

        DormApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.network_error)
        }

        DormApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<NetDorm>?) {
    val adapter = recyclerView.adapter as DormAdapter
    adapter.submitList(data)
}

@BindingAdapter("cityName")
fun TextView.setNameOfCity(item: NetDorm) {
    text = item.city
}

@BindingAdapter("streetName")
fun TextView.setStreetName(item: NetDorm) {
    text = item.address
}

@BindingAdapter("streetNum")
fun TextView.setNumOfStreet(item: NetDorm) {
    text = item.houseNumber
}

@BindingAdapter("rooms")
fun TextView.setRooms(item: NetDorm) {
    text = item.roomCount.toString()
}