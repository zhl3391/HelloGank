package com.zhl.hellogank.common.base;


/**
 * Created by zhouhl on 2016/11/1.
 * BaseContract
 */

public interface BaseContract {

    interface View<T extends Presenter> {

        void setPresenter(T presenter);

        void showProgress();

        void hideProgress();

        void showToast(int resId);

        void showToast(String msg);

        void finish();
    }

    interface Presenter {

        void subscribe();

        void unsubscribe();
    }
}
