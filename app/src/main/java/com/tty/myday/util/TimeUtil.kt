package com.tty.myday.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil{
    val now:Date
    get() {
        return Date()
    }

    fun toDateString(date:Date):String{
        val formatter:SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE)
        return formatter.format(date)
    }

    fun toDisplayDateString(date:Date):String{
        val formatter = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINESE)
        return formatter.format(date)
    }

    fun toFriendDateString(date:Date):String{
        val days = TimeSpan.fromDate(date,Date()).days.toInt()
        return if(days == 0){
            "今天"
        } else if (days == 1){
            "明天"
        } else if (days == 2){
            "后天"
//        } else if (days in 3..7){
//            "${days}天后 到期"
        } else{
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            val year1 = calendar.get(Calendar.YEAR)
            calendar.time = date
            val year2 = calendar.get(Calendar.YEAR)

            return if (year1 == year2){
                val formatter = SimpleDateFormat("M月d日", Locale.CHINESE)
                formatter.format(date)
            }
            else{
                TimeUtil.toDisplayDateString(date)
            }

        }
    }

    fun toDateTimeString(date:Date):String{
        val formatter:SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINESE)
        return formatter.format(date)
    }

    private fun toDateVar(dateString:String,format:String):Date{
        val formatter:SimpleDateFormat = SimpleDateFormat(format,Locale.CHINESE)
        return formatter.parse(dateString)
    }

    fun toDate(dateString: String):Date {
        return toDateVar(dateString,"yyyy-MM-dd")
    }

    fun toDateTime(dateString: String):Date{
        return toDateVar(dateString,"yyyy-MM-dd HH:mm:ss")
    }

    fun cutToDate(date:Date){
        date.time = date.time - date.time % 1000*3600*24
    }

}