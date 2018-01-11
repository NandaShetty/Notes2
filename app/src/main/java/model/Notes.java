package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nanda devi shetty b on 30-11-2017.
 */

public class Notes implements Parcelable {
    private   int _ID;
    public String name;
    public String password;
    public String category;
    public int alarm;
    private String dateCreated;
    public String date;

    public Notes() {
    }

    public Notes(String name,int alarm, String pwd,String category, int id,String date,String dateCreated) {
        this._ID = id;
        this.name = name;
        this.password = pwd;
        this.category=category;
        this.date = date;
        this.alarm=alarm;
        this.dateCreated=dateCreated;
    }

    protected Notes(Parcel in) {
        _ID = in.readInt();
        name = in.readString();
        password = in.readString();
        category = in.readString();
        date = in.readString();
        dateCreated=in.readString();
        alarm=in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_ID);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(category);
        dest.writeString(dateCreated);
        dest.writeString(date);
        dest.writeInt(alarm);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int get_ID() {

        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getCategory() {
        return category;
    }

    public String getAlarmDate() {
        return date;
    }

    public void setAlarmDate(String date ) {
        this.date=date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public int getAlarm() {
        return alarm;
    }
    public void setAlarm(int alarm){
        this.alarm=alarm;
    }
}




