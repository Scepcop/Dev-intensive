package ru.skillbranch.devintensive.models

import android.service.voice.AlwaysOnHotwordDetector
import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncomig: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFatory {
        var lastId = -1
        fun makeMassege(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: String = "text",
            payload: Any?
        ): BaseMessage {
            lastId++
            return when (type) {
                "image" -> ImageMessage(
                    "$lastId",
                    from,
                    chat,
                    date = date,
                    image = payload as String
                )
                else -> TextMassage(
                    "$lastId",
                    from,
                    chat,
                    date = date,
                    text = payload as String)
            }
        }
    }
}
