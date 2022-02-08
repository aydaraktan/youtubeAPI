package kg.geektech.youtubeapi.ui.detail_playlist

import android.view.LayoutInflater
import kg.geektech.`object`.Constant
import kg.geektech.kotlin1lesson5.core.extensions.showToast
import kg.geektech.youtubeapi.base.BaseActivity
import kg.geektech.youtubeapi.base.BaseViewModel
import kg.geektech.youtubeapi.databinding.ActivityDeatailPlaylistBinding

class DetailPlaylistActivity : BaseActivity<BaseViewModel, ActivityDeatailPlaylistBinding>() {
    override val viewModel: BaseViewModel = BaseViewModel()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDeatailPlaylistBinding {
        return ActivityDeatailPlaylistBinding.inflate(layoutInflater)
    }

    override fun initView() {
        intent.getStringExtra(Constant.PLAYLIST_ID)?.let { showToast(it) }
    }
}