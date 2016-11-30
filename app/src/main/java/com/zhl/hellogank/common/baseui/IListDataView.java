package com.zhl.hellogank.common.baseui;

/**
 * Created by zhouhl on 2016/11/1.
 * IListDataView
 */

public interface IListDataView {

    void showList(boolean isHasMore);

    void showEmpty();

    void showError();

    void showLoading();
}
