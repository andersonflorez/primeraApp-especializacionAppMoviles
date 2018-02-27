package cad;

import android.provider.BaseColumns;

/**
 * Created by Aprendiz on 20/02/2018.
 */

public class dbconstants {
    public static final String DB = "basedatos.db";
    public static final int DB_VERSION = 1;
    public static class User implements BaseColumns{
        public static final String TABLE = "tbl_user";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DOCUMENT = "document";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";

    }
}
