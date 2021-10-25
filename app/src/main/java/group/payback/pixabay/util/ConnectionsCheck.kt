package group.payback.pixabay.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import group.payback.pixabay.di.app.AnnotationApplication
import javax.inject.Inject

class ConnectionsCheck @Inject constructor(@AnnotationApplication val context: Context) :
    ConnectionCheckImp {
    override fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}