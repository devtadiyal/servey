package com.opinionbureau.mvp;

/**
 * Created by day on 10/04/19.
 */


public class BasePresenter<ViewT> implements IBasePresenter<ViewT> {

    protected ViewT view;
    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }
    @Override
    public void onViewInActive() {
        view = null;
    }

}