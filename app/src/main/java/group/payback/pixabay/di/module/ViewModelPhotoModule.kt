package group.payback.pixabay.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import group.payback.pixabay.di.viewmodel.ViewModelKey
import group.payback.pixabay.ui.viewmodel.PhotoDetailViewModel
import group.payback.pixabay.ui.viewmodel.PhotosViewModel

@Module
abstract class ViewModelPhotoModule {

    @Binds
    @IntoMap
    @ViewModelKey(clazz = PhotosViewModel::class)
    abstract fun bindPhotosViewModel(viewModel: PhotosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(clazz = PhotoDetailViewModel::class)
    abstract fun bindPhotoDetailViewModel(viewModel: PhotoDetailViewModel): ViewModel

}