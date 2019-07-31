package com.opinionbureau.login;

import com.opinionbureau.mvp.MainView;

public interface LoginPresenter {

    void loginUser(String email,
                   String password,
                   String culture_id,
                   String device_id,
                   String device_type);

    interface View<T> extends MainView<T> {

        void onSuccessLogin(LoginResponse response);

        void onLoginError(String message);

    }
}
