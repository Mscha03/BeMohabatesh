package com.example.bemohabatesh.util.time.shamsi

import android.content.Context
import com.example.bemohabatesh.R

class ShamsiDetail {
    companion object {

        enum class MonthsNames {
            FARVARDIN,
            ORDIBEHESHT,
            KHORDAD,
            TIR,
            MORDAD,
            SHAHRIVAR,
            MEHR,
            ABAN,
            AZAR,
            DAY,
            BAHMAN,
            ESFAND
        }

        enum class DaysOfWeekNames {
            SHANBE,
            YEKSHANBE,
            DOSHANBE,
            SESHANBE,
            CHAHARSHANBE,
            PANJSHANBE,
            JOMEE
        }

        fun shamsiMonthName(context: Context, monthNumber: Int): String? {
            val monthName = context.resources.getStringArray(R.array.shamsiMonth)
            return if (monthNumber in 1..12) monthName[monthNumber - 1] else null
        }

        fun shamsiWeekDayName(context: Context, dayNumber: Int): String?{
            val dayName = context.resources.getStringArray(R.array.shamsiWeekDay)
            return if (dayNumber in 1..7) dayName[dayNumber%7] else null
        }


    }
}