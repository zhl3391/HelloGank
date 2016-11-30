package com.zhl.hellogank.common.base;

import com.zhl.hellogank.common.baseui.IListDataView;

import java.util.List;

/**
 * Created by zhouhl on 2016/11/1.
 * ListDataContract
 */

public interface ListDataContract {

    interface View<T extends Presenter> extends BaseContract.View<T>, IListDataView {

    }

    interface Presenter<D> extends BaseContract.Presenter {

        void refresh();

        void loadMore();

        List<D> getDataList();
    }
}
