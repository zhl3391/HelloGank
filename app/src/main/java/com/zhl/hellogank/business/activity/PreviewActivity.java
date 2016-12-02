package com.zhl.hellogank.business.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zhl.hellogank.R;
import com.zhl.hellogank.common.base.BaseActivity;

import aptintent.lib.AptIntent;
import aptintent.lib.ExtraField;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhl.hellogank.business.IntentCreator.EXTRA_HEIGHT;
import static com.zhl.hellogank.business.IntentCreator.EXTRA_IMG_URL;
import static com.zhl.hellogank.business.IntentCreator.EXTRA_WIDTH;

/**
 * Created by zhouhl on 2016/11/30.
 * 预览大图
 */

public class PreviewActivity extends BaseActivity {

    @ExtraField(EXTRA_IMG_URL)
    String mImgUrl;
    @ExtraField(EXTRA_HEIGHT)
    int mHeight;
    @ExtraField(EXTRA_WIDTH)
    int mWidth;
    @BindView(R.id.img_pic)
    ImageView mImgPic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);

        AptIntent.bind(this);

        Glide.with(this).load(mImgUrl)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImgPic);

        mImgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAfterTransition(mActivity);
            }
        });
    }
}
