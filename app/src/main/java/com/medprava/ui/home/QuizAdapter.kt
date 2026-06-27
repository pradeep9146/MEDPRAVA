package com.medprava.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medprava.databinding.ItemQuizBinding
import com.medprava.domain.model.Quiz

class QuizAdapter(
    private val quizzes: List<Quiz>,
    private val onItemClick: (Quiz) -> Unit
) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(private val binding: ItemQuizBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: Quiz) {
            binding.quizTitle.text = quiz.title
            binding.quizDescription.text = quiz.description
            binding.quizSubject.text = quiz.subject
            binding.quizDuration.text = "${quiz.duration} min"
            binding.root.setOnClickListener { onItemClick(quiz) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(quizzes[position])
    }

    override fun getItemCount() = quizzes.size
}
