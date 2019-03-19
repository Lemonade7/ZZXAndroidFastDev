package com.zzx.zzxandroidfastdev.component.zzxphotoview;

import java.io.Serializable;
import java.util.List;

/**
 * 图片浏览器实体类
 *
 * @author ZZX
 * @date 2019/3/7
 */
public class PhotoViewerBean implements Serializable {
    /**
     * 网络加载图片序列
     */
    private List<String> mImageUrls;
    /**
     * 存储地址
     */
    private String mStorageAddress;

    public List<String> getImageUrls() {
        return mImageUrls;
    }

    public void setImageUrls(List<String> mImageUrls) {
        this.mImageUrls = mImageUrls;
    }

    public String getStorageAddress() {
        return mStorageAddress;
    }

    public void setStorageAddress(String mStorageAddress) {
        this.mStorageAddress = mStorageAddress;
    }
}
