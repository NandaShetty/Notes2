package database;

import android.provider.BaseColumns;

/**
 * Created by Nanda devi shetty b on 30-11-2017.
 */

public class LoginContract {

    private LoginContract() {
    }


    public static class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String _ID = "_id";
        public static final String COLUMN_TITLE = "name";
        public static final String COLUMN_CONTENT = "password";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_REMINDER = "reminder";
        public static final String COLUMN_IS_ALARM = "isAlarm";
    }
}

