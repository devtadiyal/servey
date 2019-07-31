package com.opinionbureau.mvp;

/**
 * Created by day on 10/04/19.
 */


public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInActive();
}