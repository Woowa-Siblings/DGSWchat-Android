package kr.hs.dgsw.woowasiblings.dgswchat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kr.hs.dgsw.woowasiblings.dgswchat.data.util.PreferenceManager

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager(applicationContext)
    }
}