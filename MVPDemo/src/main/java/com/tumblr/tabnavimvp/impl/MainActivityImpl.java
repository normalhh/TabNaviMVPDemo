package com.tumblr.tabnavimvp.impl;

import android.app.Activity;
import android.content.Intent;

import com.tumblr.tabnavimvp.presenter.MainPresenter;
import com.tumblr.tabnavimvp.view.MainViewInterface;


/**
 * Created by jason on 2017/11/30.
 * TODO Description:
 */

public class MainActivityImpl implements MainPresenter {
    private MainViewInterface mainViewInterface;

    @Override
    public void attachView(MainViewInterface view) {
        this.mainViewInterface = view;
    }

    @Override
    public void detachView() {
        this.mainViewInterface = null;
    }

    @Override
    public void jumpToActivity(Activity current, Class target) {
        Intent intent = new Intent(current, target);
        current.startActivity(intent);
    }

    // 刷新页面
    @Override
    public void refresh(int position) {
//        mainViewInterface.showToast("刷新第" + (position + 1) + "页面!");
    }

}
