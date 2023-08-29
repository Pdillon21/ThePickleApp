package com.example.thepickleapp.domain.utils

import org.junit.Assert.*
import org.junit.Test

class StringExtensionsTests {

    @Test
    fun `when a sentence is passed, all words are capitalized`() {
        val textAOriginal = "a sentence to be capitalized"
        val textAExpected = "A Sentence To Be Capitalized"
        val textBOriginal = "word"
        val textBExpected = "Word"
        assertEquals(textAExpected, textAOriginal.capitalizeEveryWord())
        assertEquals(textBExpected, textBOriginal.capitalizeEveryWord())
    }
}