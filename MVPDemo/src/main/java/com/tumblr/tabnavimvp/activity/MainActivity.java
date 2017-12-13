package com.tumblr.tabnavimvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tumblr.tabnavimvp.R;
import com.tumblr.tabnavimvp.adapter.SectionsPagerAdapter;
import com.tumblr.tabnavimvp.impl.MainActivityImpl;
import com.tumblr.tabnavimvp.view.MainViewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainViewInterface, ViewPager.OnPageChangeListener, BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    ViewPager container;

    private MainActivityImpl mainActivityImpl;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Timber.e("AttachBaseContext!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.e("MainActivity!");
        ButterKnife.bind(this);
        mainActivityImpl = new MainActivityImpl();
        mainActivityImpl.attachView(this);
        initView();
    }

    /**
     * 初始化View控件
     */
    private void initView() {
        // 顶部Toolbar
        setSupportActionBar(toolbar);

        // 底部导航栏
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED_NO_TITLE);
        bottomNavigationBar
                .setActiveColor(R.color.navigation_icon_active)
                .setInActiveColor(R.color.navigation_icon_inactive)
                .setBarBackgroundColor(R.color.navigation_background);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, getString(R.string.title_dashboard)))
                .addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp, getString(R.string.title_explorer)))
                .addItem(new BottomNavigationItem(R.drawable.ic_add_circle_black_24dp, getString(R.string.title_post)))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_black_24dp, getString(R.string.title_like)))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_black_24dp, getString(R.string.title_profile)))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        // 初始化ViewPager
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);
        container.addOnPageChangeListener(this);
    }

    /**
     * Toolbar的菜单项
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }
    // Toolbar菜单项结束

    /**
     * 底部导航栏选中事件监听
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        // 选中对应页面，并显示内容
        container.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        // 选中时再次点击则刷新当前页面内容
        mainActivityImpl.refresh(position);
    }
    // 底部导航栏选中事件监听结束

    /**
     * ViewPager的滑动切换监听，当滑动到对应位置，则选中对应的导航栏按钮
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置对应导航栏按钮选中项
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //ViewPager切换监听结束

    @Override
    public void showToast(String message) {
        Toasty.info(this, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityImpl.detachView();
    }

}
