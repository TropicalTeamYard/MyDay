package com.tty.myday.util

import java.util.*

class TimeSpan
{
    var time:Long = 0;
    val days:Long
    get() {return time/ TICK_DAY}


    constructor(time:Long){
        this.time = time
    }



    companion object
    {
        fun fromDate(d1:Date,d2: Date):TimeSpan{
            return TimeSpan((d1.time - d1.time % TICK_DAY) -(d2.time - d2.time % TICK_DAY))
        }

        fun from(d1:Date,d2: Date):TimeSpan
        {
            return TimeSpan(d1.time - d2.time)
        }
        private const val TICK_DAY:Long=1000*3600*24
    }
}