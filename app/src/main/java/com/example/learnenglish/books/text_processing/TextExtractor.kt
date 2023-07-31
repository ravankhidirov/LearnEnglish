package com.example.learnenglish.books.text_processing

class TextExtractor {
    fun extractWordsFromText(text: String): List<String> {
        val wordRegex = Regex("\\b\\w+\\b")
        return wordRegex.findAll(text).map { it.value }.toList()
    }
}