package com.example.thepickleapp.domain.utils

import com.example.thepickleapp.data.dao.LocationDao
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


fun LocationDao.getFormatedCreationDate(): String {
    return try {
        val localDate = LocalDate.parse(this.created?.substring(0, 10))
        val dateFormatter =
            DateTimeFormatter.ofPattern("LLLL yyyy").withLocale(Locale.ENGLISH)
        localDate.format(dateFormatter).capitalizeEveryWord()
    } catch (e: Exception) {
        "Unknown"
    }
}