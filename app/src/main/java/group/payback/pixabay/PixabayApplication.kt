package group.payback.pixabay

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import group.payback.pixabay.di.app.DaggerComponentApplication

import timber.log.Timber


class PixabayApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
        timber()
    }

    private fun timber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerComponentApplication.builder().application(this).build()
    }
}