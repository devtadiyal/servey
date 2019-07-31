package com.opinionbureau.login;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginResponse implements Parcelable {

    protected LoginResponse(Parcel in) {
        status = in.readInt();
        message = in.readString();
        profileCompleteStatus = in.readInt();
        data = in.readParcelable(LoginData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(message);
        dest.writeInt(profileCompleteStatus);
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getProfileCompleteStatus() {
        return profileCompleteStatus;
    }

    public void setProfileCompleteStatus(int profileCompleteStatus) {
        this.profileCompleteStatus = profileCompleteStatus;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    private int status;
    private String message;
    private int profileCompleteStatus;
    private LoginData data;
}
