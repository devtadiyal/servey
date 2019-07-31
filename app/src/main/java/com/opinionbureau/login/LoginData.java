package com.opinionbureau.login;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginData implements Parcelable {

    public LoginData(String panelist_id, String first_name,
                     String username, String password,
                     String culture_id, String language_id,
                     String subscription_status, String admin_approval_status,
                     String image, String phone_no, String dob, String gender) {
        this.panelist_id = panelist_id;
        this.first_name = first_name;
        this.username = username;
        this.password = password;
        this.culture_id = culture_id;
        this.language_id = language_id;
        this.subscription_status = subscription_status;
        this.admin_approval_status = admin_approval_status;
        this.image = image;
        this.phone_no = phone_no;
        this.dob = dob;
        this.gender = gender;
    }

    protected LoginData(Parcel in) {
        panelist_id = in.readString();
        first_name = in.readString();
        username = in.readString();
        password = in.readString();
        culture_id = in.readString();
        language_id = in.readString();
        subscription_status = in.readString();
        admin_approval_status = in.readString();
        image = in.readString();
        phone_no = in.readString();
        dob = in.readString();
        gender = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(panelist_id);
        dest.writeString(first_name);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(culture_id);
        dest.writeString(language_id);
        dest.writeString(subscription_status);
        dest.writeString(admin_approval_status);
        dest.writeString(image);
        dest.writeString(phone_no);
        dest.writeString(dob);
        dest.writeString(gender);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
        @Override
        public LoginData createFromParcel(Parcel in) {
            return new LoginData(in);
        }

        @Override
        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };

    public String getPanelist_id() {
        return panelist_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCulture_id() {
        return culture_id;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public String getSubscription_status() {
        return subscription_status;
    }

    public String getAdmin_approval_status() {
        return admin_approval_status;
    }

    public String getImage() {
        return image;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    private String panelist_id;
    private String first_name;
    private String username;
    private String password;
    private String culture_id;
    private String language_id;
    private String subscription_status;
    private String admin_approval_status;
    private String image;
    private String phone_no;
    private String dob;
    private String gender;

}
