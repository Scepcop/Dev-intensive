package ru.skillbranch.devintensive.models

import kotlinx.android.synthetic.main.activity_profile.*

//нужен для того чтобы данные из репозитирия доставлять в юай интерфейс

data class Profile (
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {

    val rank: String = "Junior Android"
    val nickName: String = "John Doe" // TODO IMPLEMENT ME

    fun toMap():Map<String,Any> = mapOf(
        "nickname" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}