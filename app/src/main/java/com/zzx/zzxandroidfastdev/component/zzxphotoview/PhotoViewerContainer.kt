package com.zhuzongxing.zzxdevelopmentkit.componentlibrary.zzxphotoviewer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhuzongxing.zzxdevelopmentkit.R
import kotlinx.android.synthetic.main.container_photo_viewer.*

/**
 * PhotoViewer主Activity
 * @author ZZX
 * @date 2019/3/7
 */
class PhotoViewerContainer : AppCompatActivity() {
    private var mPhotoViewerBean: PhotoViewerBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_photo_viewer)
        initData(savedInstanceState)
        initView()
    }

    private fun initData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {

        }
        val bundle = intent.extras
        if (bundle != null) {
            mPhotoViewerBean = bundle.getSerializable("photoViewerBean") as PhotoViewerBean?
        }
    }

    private fun initView() {
        if (mPhotoViewerBean != null) {
            val photoDisplayAdapter = PhotoDisplayAdapter(mPhotoViewerBean!!)
            vp_photo_display.adapter = photoDisplayAdapter
//            pv.setImageResource(R.drawable.test_img)
        }
    }

    companion object {
        /**
         * 进入照片浏览界面，不带转场动画
         * @param context 上下文
         * @param photoViewerBean 图片展示需要的实体类，已经封装进去，用PhotoViewerBean实体类作为数据载体
         */
        fun launchPhotoViewer(context: Context, photoViewerBean: PhotoViewerBean) {
            if (photoViewerBean.imageUrls.size == 0 || photoViewerBean.imageUrls == null) {
                return
            }
            val intent = Intent(context, PhotoViewerContainer::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val bundle = Bundle()
            bundle.putSerializable("photoViewerBean", photoViewerBean)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}