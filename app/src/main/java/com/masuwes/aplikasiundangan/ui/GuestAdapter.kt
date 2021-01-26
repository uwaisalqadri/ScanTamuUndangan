package com.masuwes.aplikasiundangan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.aplikasiundangan.data.model.GuestItem
import com.masuwes.aplikasiundangan.databinding.GuestListItemBinding

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<GuestItem>() {
        override fun areItemsTheSame(oldItem: GuestItem, newItem: GuestItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GuestItem, newItem: GuestItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GuestListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = differ.currentList.size

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