package kg.geektech.youtubeapi.ui.playlists


import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.`object`.Constant
import kg.geektech.youtubeapi.base.BaseActivity
import kg.geektech.youtubeapi.databinding.ActivityPlaylistsBinding
import kg.geektech.youtubeapi.model.Item
import kg.geektech.youtubeapi.ui.detail_playlist.DetailPlaylistActivity

class PlaylistsActivity : BaseActivity<PlaylistsViewModel, ActivityPlaylistsBinding>() {

    private val adapter: PlaylistsAdapter by lazy {
        PlaylistsAdapter()
    }
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun checkInternet() {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkChangeFilter = NetworkRequest.Builder().build()
        cm.registerNetworkCallback(networkChangeFilter,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    runOnUiThread {
                        binding.internet.root.visibility = View.GONE
                        binding.rvPlaylists.visibility = View.VISIBLE
                        initViewModel()

                    }
                }

                override fun onLost(network: Network) {
                    runOnUiThread {
                        binding.rvPlaylists.visibility = View.GONE
                        binding.internet.root.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    override fun initView() {
        adapter.setOnClick(object: PlaylistsAdapter.OnItemClick{
            override fun onClick(item: Item) {
                val intent = Intent(this@PlaylistsActivity, DetailPlaylistActivity::class.java)
                intent.putExtra(Constant.PLAYLIST_ID,item.id)
                startActivity(intent)
            }
        })
        binding.rvPlaylists.adapter = adapter
        binding.rvPlaylists.layoutManager = LinearLayoutManager(this)
    }

    override fun initViewModel() {
        viewModel.playlists().observe(this) {
            it.items?.let { it1 -> adapter.setList(it1) }
        }
        Log.d("shama", "initViewModel: 5")
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }


}