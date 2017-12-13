package com.tumblr.tabnavimvp.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;

import com.tumblr.tabnavimvp.activity.MainActivity;
import com.tumblr.tabnavimvp.presenter.LoadingPresenter;
import com.tumblr.tabnavimvp.view.LoadingViewInterface;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by jason on 2017/11/30.
 * TODO Description: 处理LoadingActivity的业务逻辑类，实现对应的Presenter接口
 */

public class LoadingActivityImpl implements LoadingPresenter {
    private LoadingViewInterface loadingViewInterface;

    @Override
    public void attachView(LoadingViewInterface view) {
        this.loadingViewInterface = view;
    }

    @Override
    public void detachView() {
        this.loadingViewInterface = null;
    }

    /**
     * 跳转界面的业务逻辑
     *
     * @param current
     * @param target
     */
    @Override
    public void jumpToActivity(final Activity current, final Class target) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadingViewInterface.showToast("正在跳转MainActivity");
                SystemClock.sleep(1000);
                Intent intent = new Intent(current, target);
                current.startActivity(intent);
                current.finish();
            }
        }).start();
    }

    /**
     * 请求权限的业务逻辑
     *
     * @param activity
     * @param rationale
     * @param requestCode
     * @param permissions
     */
    @Override
    public void requestPermissions(Activity activity, String rationale, int requestCode, String[] permissions) {
        // 先判断是否有权限，有权限跳转到主页面，没有权限提示需要获取权限并申请权限
        if (EasyPermissions.hasPermissions(activity, permissions)) {
            jumpToActivity(activity, MainActivity.class);
        } else {
            loadingViewInterface.showToast("缺少必要的权限");
            EasyPermissions.requestPermissions(activity, rationale, requestCode, permissions);
        }
    }
}
