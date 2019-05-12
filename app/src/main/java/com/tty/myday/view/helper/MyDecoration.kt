package com.tty.myday.view.helper

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import android.graphics.drawable.Drawable
import android.view.View


/**
 * author : chen
 * date   : 2018/8/22  11:03
 * desc   :
 */
class MyDecoration(private val mContext: Context, orientation: Int,offSet:Int = 0) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable?
    private var mOrientation: Int = 0
    private var offSet:Int = 0

    init {
        val ta = mContext.obtainStyledAttributes(ATRRS)
        this.mDivider = ta.getDrawable(0)
        ta.recycle()
        setOrientation(orientation)
        this.offSet = offSet
    }

    //设置屏幕的方向
    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLine(c, parent, state)
        } else {
            drawHorizontalLine(c, parent, state)
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    private fun drawHorizontalLine(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        //修改 删除最后一条项的线
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            //获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider.setBounds(left + offSet, top, right - offSet, bottom)
            mDivider.draw(c)
            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
        }
    }

    //画竖线
    private fun drawVerticalLine(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount -1) {
            val child = parent.getChildAt(i)

            //获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider!!.intrinsicWidth
            mDivider.setBounds(left, top+offSet, right, bottom-offSet)
            mDivider.draw(c)
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == HORIZONTAL_LIST) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDivider!!.intrinsicWidth, 0)
        }
    }

    companion object {
        val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST = LinearLayoutManager.VERTICAL

        //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
        val ATRRS = intArrayOf(android.R.attr.listDivider)
    }
}