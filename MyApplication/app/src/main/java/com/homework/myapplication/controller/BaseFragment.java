package com.homework.myapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

public abstract class BaseFragment extends Fragment {
    public View view;

    public Activity mContext;


    protected boolean isViewInitiated;

    protected boolean isVisibleToUser;

    protected boolean isDataInitiated;

    public WeakReference<View> mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            View view=inflater.inflate(getLayoutId(), null);
            mRootView = new WeakReference<View>(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        initView();
        mContext = getActivity();

        this.initData();
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated=true;
        //加载数据
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData();
        }
    }

    public void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     *
     *
     * @param forceUpdate
     */
    public void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            lazyLoad();
            isDataInitiated = true;
        }
    }

    /**
     * lazyLoad
     */
    protected abstract void lazyLoad();

    /**
     * getLayoutId
     *
     * @return
     */
    protected abstract int getLayoutId();


    protected abstract void initView();


    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     *
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    /**
     *
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     *
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}