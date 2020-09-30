package com.asct94.reigndemo

import android.app.Application
import com.facebook.stetho.Stetho

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.setup(this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

    }

}
