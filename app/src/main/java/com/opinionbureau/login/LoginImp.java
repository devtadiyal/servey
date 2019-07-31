package com.opinionbureau.login;

import com.opinionbureau.mvp.BasePresenter;
import com.opinionbureau.networking.ApiClient;
import com.opinionbureau.networking.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginImp extends BasePresenter<LoginPresenter.View> implements LoginPresenter {

    public LoginImp(View mview) {
        view = mview;
    }

    @Override
    public void onViewInActive() {
        super.onViewInActive();
    }

    @Override
    public void onViewActive(View view) {
        super.onViewActive(view);
    }

    @Override
    public void loginUser(String email,
                          String password,
                          String culture_id,
                          String device_id,
                          String device_type) {
        try {
            view.showLoadingLayout();
            if (view == null) {
                return;
            }
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setCulture_id(culture_id);
            loginRequest.setDevice_id(device_id);
            loginRequest.setDevice_type(device_type);
            loginRequest.setEmail(email);
            loginRequest.setPassword(password);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.login(loginRequest)
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call,
                                               Response<LoginResponse> response) {
                            try
                            {
                                if (view != null) {
                                    view.hideLoadingLayout();
                                }
                                LoginResponse contact = response.body();
                                if (contact.getStatus()==200) {
                                    view.onSuccessLogin(contact);
                                }
                                else if (contact.getStatus()==300) {
                                    view.onSuccessLogin(contact);
                                }
                                else if (contact.getStatus()==309) {
                                    view.onSuccessLogin(contact);
                                }
                                else if (contact.getStatus()==350) {
                                    view.onSuccessLogin(contact);
                                }
                                else if (contact.getStatus()==400) {
                                    view.onSuccessLogin(contact);
                                }
                                else {
                                    view.showError(contact.getMessage());
                                }
                            }
                            catch(Exception e)
                            {
                                view.showError(e.getMessage());
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            if (view != null) {
                                view.hideLoadingLayout();
                            }
                            view.showError(t.getMessage());
                        }
                    });
        } catch (Exception e) {
            view.hideLoadingLayout();
            System.out.println("MESSAGE 1"+e.toString());
            e.printStackTrace();
        }
    }
}