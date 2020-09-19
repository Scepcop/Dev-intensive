package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {

    private val repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()

    init {
        Log.d("M_ProfileViewModel", "INIT VIEWMODEL")
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("M_ProfileViewModel", "view model cleared")

    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun getTheme(): LiveData<Int> = appTheme

    fun saveProfileDate(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    //режим ночи включен или нет
    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }

        repository.saveAppTheme(appTheme.value!!)
    }

    fun isCorrectURL(text: String): Boolean {

        val incorrectWords = listOf(
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join"
        ).joinToString("|")

        return text.isBlank() ||
                text.matches(Regex("""^(https://)?(www\.)?github\.com/(?!($incorrectWords)/?$)[\-A-Za-z0-9]+/?$"""))
    }
}