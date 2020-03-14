package ru.skillbranch.devintensive.models

class UserView(
    val id: String,
    val fullName: String,
    val nickName: Unit,
    var avatar: String? = null,
    var status: String? = "offline",
    val initials: Unit
){
    fun printMe(){
        println("""
            id: $id:
            fullName: $fullName:
            nickName: $nickName:
            avatar: $avatar:
            status: $status:
            initials: $initials:
        """.trimIndent())
    }
}