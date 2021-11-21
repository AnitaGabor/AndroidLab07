package com.example.quizapp


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.model.Question

class DataAdapter(private val list: MutableList<Question>?, private val listener: OnItemClickListener) : RecyclerView.Adapter<DataAdapter.DataViewHolder>()
{

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    var counter_create: Int = 0
    var counter_bind: Int = 0


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val question: TextView = itemView.findViewById(R.id.question_text)
        val textView1: TextView = itemView.findViewById(R.id.correctAnswer)
        private val detailButton: Button = itemView.findViewById(R.id.detailButton)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        init{
            deleteButton.setOnClickListener(this)
            detailButton.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            Log.d("Position", "AdapterPosition: $currentPosition")
            if(this.detailButton.isPressed)
            {
                listener.onItemClick(currentPosition)
            }
            if(this.deleteButton.isPressed)
                list?.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, list!!.size)


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.question_layout, parent, false)
        ++counter_create
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list?.get(position)
        holder.question.text = currentItem?.text
        holder.textView1.text = currentItem?.correctAnswer
        ++counter_bind

    }
    override fun getItemCount() = list!!.size

}