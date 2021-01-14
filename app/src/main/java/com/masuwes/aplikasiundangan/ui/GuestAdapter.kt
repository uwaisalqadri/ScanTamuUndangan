package com.masuwes.aplikasiundangan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.masuwes.aplikasiundangan.data.Guest
import com.masuwes.aplikasiundangan.databinding.GuestListItemBinding

class GuestAdapter(options: FirebaseRecyclerOptions<Guest>)
    : FirebaseRecyclerAdapter<Guest, GuestAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GuestListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Guest) {
        holder.bind(model)
    }

    inner class ViewHolder(val binding: GuestListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: Guest) {
            binding.apply {
                guestName.text = guest.name
                guestBarcode.text = guest.barcodeResult
                guestCouponsCount.text = guest.couponsCount.toString()
            }
        }
    }
}