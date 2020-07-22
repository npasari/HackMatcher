//Nayan Pasari
//111868106
package com.example.hackmatcher;
import android.provider.BaseColumns;

public class HackContract {

    private HackContract() {
    }

    public static final class HackEntry implements BaseColumns {
        public static final String TABLE_NAME = "hackmatcher";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCHOOL = "school";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_MAJOR = "major";
        public static final String COLUMN_GRAD = "grad";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_LANG = "languages";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
