package kg.geektech.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.`object`.Constant
import kg.geektech.youtubeapi.BuildConfig.API_KEY
import kg.geektech.youtubeapi.base.BaseViewModel
import kg.geektech.youtubeapi.model.YoutubePlaylist
import kg.geektech.youtubeapi.remote.RetrofitClient
import kg.geektech.youtubeapi.remote.ServiceApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class PlaylistsViewModel : BaseViewModel() {

    private val apiService: ServiceApi by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<YoutubePlaylist> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<YoutubePlaylist> {
        val data = MutableLiveData<YoutubePlaylist>()

        apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY,Constant.MAX_RESULT)
            .enqueue(object : Callback<YoutubePlaylist> {
                override fun onResponse(call: Call<YoutubePlaylist>, response: Response<YoutubePlaylist>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                    // 200 - 299
                }

                override fun onFailure(call: Call<YoutubePlaylist>, t: Throwable) {
                    // 404 - not found, 401 - токен истек 400-499
                    print(t.stackTrace)
                }
            })

        return data
    }
}