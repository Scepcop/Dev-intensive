package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.graphics.drawable.toDrawable
import ru.skillbranch.devintensive.R

object Utils {

    private val translitMapping = mapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.replace(Regex(" +"), " ")?.split(" ")

        val firstName = parts?.notEmptyOrNullAt(0)
        val lastName = parts?.notEmptyOrNullAt(1)

        return firstName to lastName
    }

    private fun List<String>.notEmptyOrNullAt(index: Int) = getOrNull(index).let {
        if ("" == it) null
        else it
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        payload.forEach {
            result += when {
                it == ' ' -> divider
                it.isUpperCase() -> translitMapping[it.toLowerCase()]?.capitalize() ?: it.toString()
                else -> translitMapping[it] ?: it.toString()
            }
        }
        return result
    }



    fun toInitials(firstName: String?, lastName: String?): String? = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName[0].toUpperCase()
            .toString()
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName[0].toUpperCase()
            .toString()
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> firstName[0].toUpperCase() + lastName[0].toUpperCase()
            .toString()
        else -> throw IllegalStateException("Incorrect state in 'when' expression")
    }
    @SuppressLint("NewApi")
    fun getDrawableInitials(context: Context, initials: String): Drawable {
        val size = context.resources.getDimensionPixelSize(R.dimen.avatar_round_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        val bounds = Rect()
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val c = Canvas()
        c.setBitmap(bitmap)

        val halfSize = (size / 2).toFloat()

        paint.style = Paint.Style.FILL

        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, typedValue, true)

        paint.color = typedValue.data
        c.drawCircle(halfSize, halfSize, halfSize, paint)

        paint.textSize = context.resources.getDimension(R.dimen.text_initials_size)
        paint.textAlign = Paint.Align.CENTER
        paint.color = context.resources.getColor(android.R.color.white, context.theme)
        paint.getTextBounds(initials, 0, initials.length, bounds)
        c.drawText(initials, halfSize, halfSize - ((paint.descent() + paint.ascent()) / 2), paint)
        return bitmap.toDrawable(context.resources)
    }
}