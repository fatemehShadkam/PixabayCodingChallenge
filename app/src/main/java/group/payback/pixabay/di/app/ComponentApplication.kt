package group.payback.pixabay.di.app

import android.app.Activity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import group.payback.pixabay.PixabayApplication
import group.payback.pixabay.di.module.ApplicationModule
import group.payback.pixabay.di.module.BuilderModule
import group.payback.pixabay.di.module.DataModule
import group.payback.pixabay.di.module.ViewModelPhotoModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        BuilderModule::class, ViewModelPhotoModule::class,
        DataModule::class
    ]
)
interface ComponentApplication : AndroidInjector<PixabayApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PixabayApplication): Builder

        fun build(): ComponentApplication

    }

    val activityInjector: DispatchingAndroidInjector<Activity>
}