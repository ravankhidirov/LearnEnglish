package com.example.learnenglish.books.book_sections

class Section {
    var sectionNumber: Int? = null
    var sectionContent: String? = null



    constructor() {}

    constructor(m: Int?, s: String?) {
        this.sectionNumber = m
        this.sectionContent = s
    }
}