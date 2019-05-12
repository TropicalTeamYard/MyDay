package com.tty.myday.model

class ScheduleItemCollection():Iterable<ScheduleItem> {

    override fun iterator(): Iterator<ScheduleItem> {
        return data.iterator()
    }

    var data :ArrayList<ScheduleItem> = ArrayList()
    var isLoaded = false
}