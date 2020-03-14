package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_instance() {
        val user2 = User("2", "Key", "Jey")
    }

    @Test
    fun test_factory() {
        //  val user = User.makeUser("lslsl slslsl")
        //  val user2 = User.makeUser("oiuoiu oiuoiu")
        val user = User.makeUser("lslsl slslsl")
        val user2 = user.copy(id = "2", lastName = "Cena", isOnine = true)
        println(user2)
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("hey Way")
        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()
        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")

    }

    @Test
    fun test_copy() {
        val user = User.makeUser("Kiy Ly")
        var user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(id = "1", lastVisit = Date().add(-2, TimeUnits.SECOND))
        var user4 = user.copy(lastName = "Ollo", lastVisit = Date().add(2, TimeUnits.HOUR))

        println(
            """
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent()
        )

/*
        if (user.equals(user2)) {
            println("equals ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        } else {
            println("not equals ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        }

        user2 = user

        if (user.equals(user2)) {
            println("equals ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        } else {
            println("not equals ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        }

        user2.lastName = "Doe"
        println("$user \n $user2")*/

    }

    @Test
    fun test_dataq_maping() {
        val user = User.makeUser("Ma Mae")
        val newUser = user.copy(lastVisit = Date().add(-7, TimeUnits.DAY))
        println(user)
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Таня Петрова")
        val textMassage = BaseMessage.makeMassege(user, Chat("0"), payload = "any text kmk", type = "text")
        val imageMessage = BaseMessage.makeMassege(user, Chat("0"), payload = "image kkn knkn ", type = "image")

        println(textMassage.formatMessage() + user)
        println(imageMessage.formatMessage())
    }

}
