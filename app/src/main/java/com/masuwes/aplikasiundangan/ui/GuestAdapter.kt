package com.masuwes.aplikasiundangan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.aplikasiundangan.data.model.GuestItem
import com.masuwes.aplikasiundangan.databinding.GuestListItemBinding

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.ViewHolder>() {

    private val listGuestItem = arrayListOf<GuestItem>()

    fun setList(list: List<GuestItem>?) {
        if (list == null) return
        listGuestItem.addAll(list)
        listGuestItem.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GuestListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listGuestItem[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listGuestItem.size

    inner class ViewHolder(val binding: GuestListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestItem) {
            binding.apply {
                guestName.text = guest.guest_name
                guestBarcode.text = guest.id.toString()
                guestCouponsCount.text = guest.status
            }
        }
    }
}