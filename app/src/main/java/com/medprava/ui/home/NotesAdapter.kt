package com.medprava.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medprava.databinding.ItemNotesBinding
import com.medprava.domain.model.Notes

class NotesAdapter(
    private val notesList: List<Notes>,
    private val onItemClick: (Notes) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(private val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) {
            binding.notesTitle.text = notes.title
            binding.notesSubject.text = notes.subject
            binding.notesDescription.text = notes.description
            binding.notesDownloads.text = "${notes.downloads} downloads"
            binding.root.setOnClickListener { onItemClick(notes) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount() = notesList.size
}
