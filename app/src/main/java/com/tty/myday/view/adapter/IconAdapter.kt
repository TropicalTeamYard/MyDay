package com.tty.myday.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tty.myday.R
import com.tty.myday.listener.OnRItemClickListener
import com.tty.myday.view.converter.IconConverter
import kotlinx.android.synthetic.main.sample_icon_view.view.*

class IconAdapter(var converter:IconConverter): RecyclerView.Adapter<IconAdapter.ViewHolder>(){

    lateinit var context: Context
    private var mListener: OnRItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        context = p0.context
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.sample_icon_view,p0,false),mListener)
    }

    override fun getItemCount(): Int {
        return converter.count
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.imageView.setImageResource(converter.getIconRes(p1))
    }

//    interface OnItemClickListener: View.OnClickListener{
//        fun onItemClick(v:View?,position:Int)
//    }

    fun setOnRItemClickListener(listener: OnRItemClickListener){
        mListener = listener
    }

    inner class ViewHolder(v: View,var listener: OnRItemClickListener?):RecyclerView.ViewHolder(v),View.OnClickListener{
        override fun onClick(v: View?) {
            listener?.onItemClick(v,layoutPosition)
        }

        var imageView: ImageView
        init {
            imageView = v.img_sample_icon_view
            v.setOnClickListener(this)
        }
    }
}