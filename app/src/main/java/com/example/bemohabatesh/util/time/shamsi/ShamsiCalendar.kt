package com.example.bemohabatesh.util.time.shamsi

import ir.huri.jcal.JalaliCalendar
import java.time.LocalTime


class ShamsiCalendar {

    //Jalali Calendar With Week

    // parameter
    var year: Int = 0
        private set
    var month: Int = 0
        private set
    var week: Int = 0
        private set
    var day: Int = 0
        private set
    var dayOfWeek: Int = 0
        private set
    var hour: Int = 0
        private set
    var minute: Int = 0
        private set
    var second: Int = 0
        private set

    constructor() { // تنظیم کردن تاریخ امروز و زمان فعلی برای شی

        val date = JalaliCalendar()
        val time = LocalTime.now()

        this.year = date.year
        this.month = date.month
        this.week = weekOfYear(date)
        this.day = date.day
        this.dayOfWeek = date.dayOfWeek
        this.hour = time.hour
        this.minute = time.minute
        this.second = time.second
    }

    constructor(year: Int, month: Int, week: Int, day: Int) { // اضافه کردن تاریخ وارد شده بدون زمان
        this.year = year
        this.month = month
        this.week = week
        this.day = day
    }

    constructor(year: Int, month: Int, week: Int, day: Int, hour: Int, min: Int, sec:Int)
            : this(year, month, week, day){ // اضافه کردن تاریخ و زمان وارد شده
        this.hour = hour
        this.minute = min
        this.second = sec
    }

    companion object {
        fun weekOfYear(jalaliCalendar: JalaliCalendar): Int {

            // تبدیل تاریخ به میلادی
            val gregorianCalendar = jalaliCalendar.toGregorian()

            // اول فروردین به میلادی
            val startOfYearJalali = JalaliCalendar(jalaliCalendar.year, 1, 1)
            val startOfYearJalaliGregorian = startOfYearJalali.toGregorian()

            // محاسبه اختلاف روزها
            val diffInMillis = gregorianCalendar.timeInMillis - startOfYearJalaliGregorian.timeInMillis


            // تعداد روزها از اول سال
            val daysSinceStartOfYear = (diffInMillis / (1000 * 60 * 60 * 24))

            // محاسبه شماره هفته
            return ((daysSinceStartOfYear / 7) + 1).toInt()
        }


    }


}