package com.zhl.hellogank.business.view;

import com.zhl.hellogank.R;
import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.databinding.ItemArticleBinding;

/**
 * Created by zhouhl on 2016/11/8.
 * ArticleViewHolder
 */

public class ArticleViewHolder extends DataBindingVH<Article, ItemArticleBinding> {

    @Override
    public void updateView(Article data, int position) {
        mBinding.setData(data);
        mBinding.executePendingBindings();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_article;
    }

}
