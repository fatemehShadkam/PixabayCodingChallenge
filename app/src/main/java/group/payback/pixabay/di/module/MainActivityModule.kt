package group.payback.pixabay.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import group.payback.pixabay.ui.fragments.PhotoDetailFragment
import group.payback.pixabay.ui.fragments.PhotosFragment

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributePhotosFragment(): PhotosFragment

    @ContributesAndroidInjector
    abstract fun contributePhotoDetailFragment(): PhotoDetailFragment

}