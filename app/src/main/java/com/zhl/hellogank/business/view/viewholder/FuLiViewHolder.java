package com.zhl.hellogank.business.view.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhl.commonadapter.BaseViewHolder;
import com.zhl.hellogank.R;
import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.common.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhouhl on 2016/11/10.
 * FuLiViewHolder
 */

public class FuLiViewHolder extends BaseViewHolder<Article> {
    @BindView(R.id.img_fuli)
    ImageView mImgFuli;

    @Override
    public void findView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void updateView(Article data, final int position) {

        ViewGroup.LayoutParams params = mImgFuli.getLayoutParams();
        params.width = data.imgWidth;
        params.height = data.imgHeight;
        mImgFuli.setLayoutParams(params);
        Glide.with(mContext).load(data.url)
                .override(data.imgWidth, data.imgHeight)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImgFuli);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_fuli;
    }
}
