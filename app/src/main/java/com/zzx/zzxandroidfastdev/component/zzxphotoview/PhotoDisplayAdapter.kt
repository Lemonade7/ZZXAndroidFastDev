package com.zhuzongxing.zzxdevelopmentkit.componentlibrary.zzxphotoviewer

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.zhuzongxing.zzxdevelopmentkit.R
import com.zhuzongxing.zzxdevelopmentkit.componentlibrary.zzxphotoviewer.photoview.PhotoView

/**
 * 图片横向滑动展示ViewPager
 * @author ZZX
 * @date 2019/3/7
 */
class PhotoDisplayAdapter(private var photoViewerBean: PhotoViewerBean) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val photoView = PhotoView(container.context)
        photoView.setImageResource(R.drawable.test_img)
//        Glide.with(container.context).load(photoViewerBean.imageUrls[position]).into(photoView)
        container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        return photoView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`

    }

    override fun getCount(): Int {
        return photoViewerBean.imageUrls.size
    }

}