package com.zhl.hellogank.business.contract;

import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.business.model.bean.HomeType;
import com.zhl.hellogank.common.base.ListDataContract;

import java.util.List;


/**
 * Created by zhouhl on 2016/11/8.
 * ArticleContract
 */

public interface ArticleContract {

    interface View extends ListDataContract.View<Presenter> {
    }

    interface Presenter extends ListDataContract.Presenter<Article> {

        HomeType getHomeType();
    }

}
