package com.example.bemohabatesh.util.time.shamsi

import ir.huri.jcal.JalaliCalendar
import java.time.LocalTime


class ShamsiCalendar {

    //Jalali Calendar With Week

    // parameter
    private var year: Int = 0;
    private var month: Int = 0;
    private var week: Int = 0;
    private var day: Int = 0;
    private var hour: Int = 0;
    private var minute: Int = 0;
    private var second: Int = 0;

    constructor() { // تنظیم کردن تاریخ امروز و زمان فعلی برای شی

        val date = JalaliCalendar()
        val time = LocalTime.now()

        this.year = date.year
        this.month = date.month
        this.week = weekOfYear(JalaliCalendar(this.year, this.month, this.day))
        this.day = date.day
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
            val t = gregorianCalendar.timeInMillis
            val s = startOfYearJalaliGregorian.timeInMillis
            val diffInMillis = gregorianCalendar.timeInMillis - startOfYearJalaliGregorian.timeInMillis


            // تعداد روزها از اول سال
            val daysSinceStartOfYear = (diffInMillis / (1000 * 60 * 60 * 24))

            // محاسبه شماره هفته
            return ((daysSinceStartOfYear / 7) + 1).toInt()
        }

    }

    fun getYear(): Int {return year}
    fun getMonth(): Int {return month}
    fun getWeek(): Int {return week}
    fun getDay(): Int {return day}
    fun getHour(): Int {return hour}
    fun getMinute(): Int {return minute}
    fun getSecond(): Int {return second}

}