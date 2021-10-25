package group.payback.pixabay.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import group.payback.pixabay.ui.activity.MainActivity


@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}