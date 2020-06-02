package ru.skillbranch.devintensive.extensions

fun String.truncate(size: Int = 16): String {
    val resStr = this.trim()
    if (resStr.length <= size)
        return resStr
    return resStr.substring(0, size).trimEnd() + "..."
}

fun String.stripHtml() = this.replace(Regex("<[^>]*>"), "").replace(Regex("&amp;|&lt;|&gt;|&quot;|&apos;|&#\\d+;"), "").replace(Regex(" +"), " ")
