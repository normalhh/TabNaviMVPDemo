package com.tumblr.tabnavimvp.presenter;

import android.app.Activity;

/**
 * Created by jason on 2017/11/30.
 * TODO Description: 处理业务逻辑接口的基类
 */

public interface BasePresenter<T> {
    /**
     * 载入View接口
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 清除View接口
     */
    void detachView();

    /**
     * 从当前Activity跳转到对应Activity
     *
     * @param current
     * @param target
     */
    void jumpToActivity(Activity current, Class target);
}
