package com.tty.myday.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tty.myday.R
import com.tty.myday.listener.OnRItemClickListener
import com.tty.myday.model.ScheduleItemCollection
import kotlinx.android.synthetic.main.sample_schedule_item_view.view.*

class ScheduleItemAdapter(var data:ScheduleItemCollection): RecyclerView.Adapter<ScheduleItemAdapter.ViewHolder>()
{

    var mListener:OnRItemClickListener?=null
    lateinit var context: Context
    var isFolded =  false

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        context = p0.context
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.sample_schedule_item_view,p0,false),mListener)
    }

    override fun getItemCount(): Int {
        //return 22;
        return data.data.size + 1
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if (p1 < data.data.size){
            p0.tbxTitle.text = data.data[p1].title
            p0.itemView.visibility = View.VISIBLE
        } else {
            if (isFolded){
                p0.itemView.visibility = View.GONE
            }else{
                p0.itemView.visibility = View.INVISIBLE
            }
        }
    }

    fun setOnRItemClickListener(listener: OnRItemClickListener){
        this.mListener = listener
    }

    class ViewHolder(v: View,var listener:OnRItemClickListener?):RecyclerView.ViewHolder(v),View.OnClickListener
    {
        override fun onClick(v: View?) {
            listener?.onItemClick(v,layoutPosition)
        }

        init {
            v.setOnClickListener(this)
        }

        var tbxTitle = v.tbx_title_item
    }
}