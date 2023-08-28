package com.example.thepickleapp.presentation.common_views.utils

import com.example.thepickleapp.domain.utils.capitalizeEveryWord

object TextUtils {


    fun getInitialsAndNumbers(string: String): String {
        val listOfWords = string
            .replace("(", "")
            .replace(")", "")
            .split(" ", "-")
        var newString = ""
        for (word in listOfWords) {
            newString += word[0].toString().capitalizeEveryWord()
        }
        return newString
    }

    fun getEpisodeDataInitials(string: String): String {
        return string.replace("0", "").uppercase()
    }

    fun getListOfEpisodeDataString(string: String): List<String> {
        return getEpisodeDataInitials(string)
            .replace("E", "splitEpisode ")
            .replace("S", "Season ")
            .split("split")
    }
}