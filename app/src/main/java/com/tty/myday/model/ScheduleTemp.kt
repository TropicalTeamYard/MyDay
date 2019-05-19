package com.tty.myday.model

import android.util.Log
import com.tty.myday.util.TagConst
import com.tty.myday.util.TimeSpan
import com.tty.myday.util.TimeUtil
import java.sql.Time
import java.util.*

/**
 * 临时存储日程信息
 */
class ScheduleTemp
{
    var createTime:String = ""
    var date:Date?=null
    var startTime:String = ""
    var during:String =""
    var repeat:String =""
    var alarm:String =""
    var isTask:Boolean = true

    val typeDisplay:String
    get() {
        return if (isTask){
            "任务"
        } else{
            "日程"
        }
    }

    val dateDisplay:String
    get(){
        if (isTask){
            if (date == null)
            {
                return "设置截止日期"
            } else{
                return "${TimeUtil.toFriendDateString(date!!)} 到期"
            }
        } else{
            return "Not an Obj"
        }
    }

    fun freshCreateTime()
    {
        createTime = TimeUtil.toDateTimeString(Date())
    }

    fun setDateNormal(offset:Int){
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        TimeUtil.cutToDate(calendar.time) //截断到日期

        Log.d(TagConst.UI,"当前时间${TimeUtil.toDisplayDateString( calendar.time)}")

        calendar.add(Calendar.DATE,offset)
        //TimeUtil.cutToDate(calendar.time)
        date = calendar.time
        //TimeUtil.cutToDate(date!!)

        Log.d(TagConst.UI,"设置截止时间${TimeUtil.toDisplayDateString(date!!)}")
    }

    fun setDateNormal(date:Date){
        this.date = date
        TimeUtil.cutToDate(this.date!!)
    }

    fun init(){
        createTime = TimeUtil.toDateTimeString(Date())
        date = null
        startTime = ""
        during = ""
        repeat = ""
        alarm = ""
        isTask = true
    }



}