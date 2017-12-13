package com.tumblr.tabnavimvp.presenter;

import android.app.Activity;

import com.tumblr.tabnavimvp.view.LoadingViewInterface;

/**
 * Created by jason on 2017/11/30.
 * TODO Description: 处理LoadingActivity业务逻辑的接口
 */

public interface LoadingPresenter extends BasePresenter<LoadingViewInterface> {
    void requestPermissions(Activity context, String rationale, int requestCode, String[] permissions);
}
