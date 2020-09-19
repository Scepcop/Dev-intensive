package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

val SECOND = 1000L
val MINUTE = 60 * SECOND
val HOUR = 60 * MINUTE
val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff =
        (this.time - date.time)
    var name: String = " "

    if (diff in -75000..75000) {

        when (diff / 1000) {
            in -1..1 -> return "только что"
            in 2..45 -> return "через несколько секунд"
            in -45..-2 -> return "несколько секунд назад"
            in -75..-46 -> return "минуту назад"
            in 46..75 -> return "через минуту"
        }
    }

    if (diff in -2759000..2759000) {//-45,9..45,9 минут
        var intDiffMin: Int = (diff.toInt()) / 1000 / 60
        if (intDiffMin >= -20 || intDiffMin <= 20) {

            when (intDiffMin) {
                in -20..-5 -> name = "минут"
                in -4..-2 -> name = "минуты"
                in -1..1 -> name = "минуту"
                in 2..4 -> name = "минуты"
                in 5..20 -> name = "минут"
            }
        }
        if (intDiffMin in -45..-21 || intDiffMin in 21..45) {
            var toMinutes = intDiffMin / 1000 / 60
            val lastCharMinutes: Char = toMinutes.toString().last()
            val lastNumMinutes: Int = lastCharMinutes.toString().toInt()
            when ((lastCharMinutes.toString().toInt())) {
                1 -> name = "минуту"
                in 2..4 -> name = "минуты"
                else -> name = "минут"
            }
        }
        if (intDiffMin > 0) {
            return "через " + intDiffMin.toString() + " " + name
        } else {
            return (intDiffMin.toString().drop(1) + " " + name + " назад")
        }

    }

    if (diff in -4559000..4559000) {
        if (diff > 0) {
            return "через час"
        } else {
            return "час назад"
        }
    }

    if (diff in -96199999..-4560000 || diff in 4560000..96199999) {
        var toHours: Int = ((diff / 3600).toInt()) / 1000
        when (toHours) {
            in -26..-23 -> return "день назад"
            -22 -> name = "часа"
            -21 -> name = "час"
            in -20..-5 -> name = "часов"
            in -4..-2 -> name = "часа"
            in -1..1 -> name = "час"
            in 2..4 -> name = "часа"
            in 5..20 -> name = "часов"
            21 -> name = "час"
            22 -> name = "часа"
            in 23..26 -> return "через день"
        }
        if (toHours > 0) {
            return "через " + toHours.toString() + " " + name
        } else {
            return toHours.toString().drop(1) + " " + name + " назад"
        }
    }

    var numLen: Int
    var toDays = (((diff / 3600) / 1000) / 24).toInt()
    var lastTwoOfThree: Int
    var lastofTwo: Int
    var moreThenZero: Boolean = true


    if (diff <= -97200000 || diff >= 97200000) {

        if (toDays < 0) {
            numLen = toDays.toString().drop(1).length
            toDays = toDays.toString().drop(1).toInt()
            moreThenZero = false
        } else {
            numLen = toDays.toString().length
        }
        if (toDays > 360) {
            if (moreThenZero) {
                return "более чем через год"
            } else {
                return "более года назад"
            }
        }

        if (toDays.toString().drop(1) == "00") {
            name = "дней"
            if (moreThenZero) {
                return "через " + toDays.toString() + " " + name
            } else {
                return toDays.toString() + " " + name + " назад"
            }
        }

        if (numLen == 1) {
            when (toDays) {
                1 -> name = "день"
                in 2..4 -> name = "дня"
                else -> name = "дней"
            }
            if (moreThenZero) {
                return "через " + toDays.toString() + " " + name
            } else {
                return toDays.toString() + " " + name + " назад"
            }
        }
        if (numLen == 2) {
            lastofTwo = toDays.toString().drop(1).toInt()
            lastTwoOfThree = toDays

            if (lastTwoOfThree in 1..20) {

                when (lastTwoOfThree) {
                    1 -> name = "день"
                    in 2..4 -> name = "дня"
                    in 5..20 -> name = "дней"
                }

                if (moreThenZero) {
                    return "через " + toDays.toString() + " " + name
                } else {
                    return toDays.toString() + " " + name + " назад"
                }
            }
            when (lastofTwo) {
                1 -> name = "день"
                in 2..4 -> name = "дня"
                else -> name = "дней"
            }
            if (moreThenZero) {
                return "через " + toDays.toString() + " " + name
            } else {
                return toDays.toString() + " " + name + " назад"
            }
        }

        if (numLen == 3) {

            lastTwoOfThree = toDays.toString().drop(1).toInt()


            if (lastTwoOfThree in 0..20) {

                when (lastTwoOfThree) //ВОТ ЭТОТ ОТМЕЧЕННЫЙ БЛОК
                {
                    0 -> name = "дней"
                    1 -> name = "день"
                    in 2..4 -> name = "дня"
                    in 5..20 -> name = "дней"
                }
                if (moreThenZero) {
                    return "через " + toDays.toString() + " " + name
                } else {
                    return toDays.toString() + " " + name + " назад"
                }
            }
            lastofTwo = lastTwoOfThree.toString().drop(1).toInt()

            when (lastofTwo) {
                1 -> name = "день"
                in 2..4 -> name = "дня"
                else -> name = "дней"
            }
            if (moreThenZero) {
                return "через " + toDays.toString() + " " + name
            } else {
                return toDays.toString() + " " + name + " назад"
            }

        }

        return toDays.toString() + " " + name
    } else {
        return (diff / 3600).toString()
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(number: Int): String {
        var name: Array<String>
        var nameFin: String

        when (this) {
            SECOND -> name = arrayOf("секунду", "секунды", "секунд")
            MINUTE -> name = arrayOf("минуту", "минуты", "минут")
            HOUR -> name = arrayOf("час", "часа", "часов")
            DAY -> name = arrayOf("день", "дня", "дней")
        }

        if (number in 0..20) {
            when (number) {
                1 -> nameFin = name[0]
                in 2..4 -> nameFin = name[1]
                else -> nameFin = name[2]
            }
            return number.toString() + " " + nameFin
        }

        var numberString: String = number.toString()
        var last2digit_ = numberString.drop(numberString.length - 2)

        if (last2digit_.toInt() in 0..20) {
            when (last2digit_.toInt()) {
                1 -> nameFin = name[0]
                in 2..4 -> nameFin = name[1]
                else -> nameFin = name[2]
            }
            return number.toString() + " " + nameFin
        }
        var last1Digit = last2digit_.drop(1).toInt()

        when (last1Digit) {
            1 -> nameFin = name[0]
            in 2..4 -> nameFin = name[1]
            else -> nameFin = name[2]
        }

        return number.toString() + " " + nameFin
    }
}
