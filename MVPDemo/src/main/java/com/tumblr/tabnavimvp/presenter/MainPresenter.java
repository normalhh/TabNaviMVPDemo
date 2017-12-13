package com.tumblr.tabnavimvp.presenter;

import com.tumblr.tabnavimvp.view.MainViewInterface;

/**
 * Created by jason on 2017/11/30.
 * TODO Description:
 */

public interface MainPresenter extends BasePresenter<MainViewInterface> {
    // 刷新Dashboard
    void refresh(int position);
}
