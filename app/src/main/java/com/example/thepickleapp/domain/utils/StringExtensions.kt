package com.example.thepickleapp.domain.utils

fun String.capitalizeEveryWord(): String {
    val originalString = this
    var resultString = ""
    val words = originalString.split(" ")
    for (word in words) {
        val newWord = word.replaceFirstChar {
            it.uppercase()
        }
        if (resultString.isNotEmpty()) {
            resultString += " "
        }
        resultString += newWord
    }
    return resultString
}