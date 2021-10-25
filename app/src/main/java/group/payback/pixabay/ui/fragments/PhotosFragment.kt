package group.payback.pixabay.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import group.payback.pixabay.R
import group.payback.pixabay.network.model.PhotoModel
import group.payback.pixabay.ui.adapter.ClickListener
import group.payback.pixabay.ui.adapter.PhotoAdapter
import group.payback.pixabay.ui.base.BaseFragment
import group.payback.pixabay.ui.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photos.*
import timber.log.Timber

class PhotosFragment : BaseFragment<PagedList<PhotoModel.PhotoResponse>, PhotosViewModel>(),
    SearchView.OnQueryTextListener {

    override fun handleState(state: PagedList<PhotoModel.PhotoResponse>) {
        render(state)
    }

    private val clickListener: ClickListener = this::onPhotoClicked

    private fun onPhotoClicked(photo: PhotoModel.PhotoResponse) {

        context?.let { showDialog(it,photo) }

    }


    private fun showDetails( photo: PhotoModel.PhotoResponse ){

        view?.let {
            findNavController(it).navigate(
                PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(
                    photo.largeImageUrl,
                    photo.userName,
                    photo.tags,
                    photo.likeNumber,
                    photo.downloadNumber,
                    photo.commentNumber
                )
            )
        }


    }

    private val photoListAdapter = PhotoAdapter(clickListener)


    override fun getLayout(): Int {
        return R.layout.fragment_photos
    }

    override fun onCreateCompleted() {
        initRecyclerView()
        createViewModel(PhotosViewModel::class.java)
        //set default value for searchView
        viewModel.setFilter(getString(R.string.search_filter_default_value))

    }


    private fun render(pagedPhotoList: PagedList<PhotoModel.PhotoResponse>) {
        photoListAdapter.submitList(pagedPhotoList)
        Timber.d("pagedPhotoList : %s", pagedPhotoList)
    }

    private fun initRecyclerView() {
        recyclerView_fragment_photos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            queryHint = getString(R.string.search_view_hint)
            setQuery(
                if (viewModel.cachedFilter.isEmpty()) getString(R.string.search_filter_default_value) else viewModel.cachedFilter,
                true
            )
            isSubmitButtonEnabled = true
        }.setOnQueryTextListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return view?.findNavController()?.let {
            NavigationUI.onNavDestinationSelected(item, it) || super.onOptionsItemSelected(item)
        } ?: false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { newText ->
            Timber.d("query : %s", newText)
            if (newText.trim().replace(" ", "").length >= 3 || newText.isEmpty()) {
                viewModel.cachedFilter = newText
                viewModel.setFilter(newText)
                viewModel.createLiveData()
                startObserving()

            }
        }
        return true
    }


    // Method to show an alert dialog with yes, no  button
    private fun showDialog(context : Context, photo: PhotoModel.PhotoResponse){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(context)

        // Set a title for alert dialog
        builder.setTitle("Details")

        // Set a message for alert dialog
        builder.setMessage("Do you want to see a large size of photo and more details?")


        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> showDetails(photo)
                DialogInterface.BUTTON_NEGATIVE -> dialog.cancel()
              //  DialogInterface.BUTTON_NEUTRAL -> toast("Neutral/Cancel button clicked.")
            }
        }


        // Set the alert dialog positive/yes button
        builder.setPositiveButton("YES",dialogClickListener)

        // Set the alert dialog negative/no button
        builder.setNegativeButton("NO",dialogClickListener)

//        // Set the alert dialog neutral/cancel button
//        builder.setNeutralButton("CANCEL",dialogClickListener)


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }




}