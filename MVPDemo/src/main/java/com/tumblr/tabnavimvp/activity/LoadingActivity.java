package com.tumblr.tabnavimvp.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.tumblr.tabnavimvp.Constants;
import com.tumblr.tabnavimvp.R;
import com.tumblr.tabnavimvp.impl.LoadingActivityImpl;
import com.tumblr.tabnavimvp.view.LoadingViewInterface;

import es.dmoral.toasty.Toasty;


public class LoadingActivity extends BaseActivity implements LoadingViewInterface {
    private LoadingActivityImpl loadingActivityImpl;
    // 需要的权限
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loadingActivityImpl = new LoadingActivityImpl();
        loadingActivityImpl.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadingActivityImpl.requestPermissions(this, getString(R.string.loading_activity_permission_rationale), Constants.LOADING_ACTIVITY_REQUEST_PERMISSION_CODE, permissions);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showToast(String message) {
        Toasty.info(this, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingActivityImpl.detachView();
    }
}
