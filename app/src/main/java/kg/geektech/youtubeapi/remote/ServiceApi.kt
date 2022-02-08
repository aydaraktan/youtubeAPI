package kg.geektech.youtubeapi.remote

import kg.geektech.youtubeapi.model.YoutubePlaylist
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface ServiceApi {
    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): Call<YoutubePlaylist>
}
