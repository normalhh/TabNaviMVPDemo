package com.tumblr.tabnavimvp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tumblr.tabnavimvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ViewHolder viewHolder;

    public PlaceholderFragment() {
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        viewHolder = new ViewHolder(rootView);
        viewHolder.sectionLabel.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        viewHolder.refreshLayout.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void onRefresh() {
        // TODO: 设置刷新效果，调用刷新逻辑
        Toasty.info(getContext(), "刷新", Toast.LENGTH_SHORT, true).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewHolder.refreshLayout.setRefreshing(false);
            }
        }, 3000);
    }


    static class ViewHolder {
        @BindView(R.id.section_label)
        TextView sectionLabel;
        @BindView(R.id.refresh_layout)
        SwipeRefreshLayout refreshLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}