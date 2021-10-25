package group.payback.pixabay.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import group.payback.pixabay.PixabayApplication
import group.payback.pixabay.data.execution.NetworkJobExecutor
import group.payback.pixabay.data.execution.PostExecutionThread
import group.payback.pixabay.data.execution.RequestExecutor
import group.payback.pixabay.di.app.AnnotationApplication
import group.payback.pixabay.util.ConnectionCheckImp
import group.payback.pixabay.ui.uiThread.UiThreadExecutor
import group.payback.pixabay.util.ConnectionsCheck
import javax.inject.Singleton

@Module
object ApplicationModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideRequestExecutor(): RequestExecutor {
        return NetworkJobExecutor()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun postExecutionThread(): PostExecutionThread = UiThreadExecutor()


    @Provides
    @JvmStatic
    @AnnotationApplication
    fun provideContext(app: PixabayApplication): Context = app.applicationContext

    @Provides
    @JvmStatic
    @Singleton
    fun provideConnectivityManager(connectivityManagerImp: ConnectionsCheck)
            : ConnectionCheckImp = connectivityManagerImp
}
