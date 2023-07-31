package com.example.learnenglish.chat
class ChatMessage {
    var message: String? = null
    var senderId: String? = null
    var senderName:String? = null


    constructor() {}

    constructor(m: String?, s: String?,sN:String?) {
        this.message = m
        this.senderId = s
        this.senderName = sN
    }
}