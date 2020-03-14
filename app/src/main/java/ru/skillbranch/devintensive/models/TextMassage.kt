package ru.skillbranch.devintensive.models

import java.util.*

class TextMassage(
    id: String,
    from: User?,
    chat: Chat,
    isIncomig: Boolean = false,
    date: Date = Date(),
    var text: String
) : BaseMessage(id, from, chat, isIncomig, date){

    override fun formatMessage(): String {
        text = "только что "
        return text
    }

}