package aericks1.example.playbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "playBase.db";

    public PlayBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PlayDbSchema.PlayTable.NAME +
                "( _id integer primary key autoincrement, " +
                PlayDbSchema.PlayTable.Cols.UUID + ", " +
                PlayDbSchema.PlayTable.Cols.TITLE + ", " +
                PlayDbSchema.PlayTable.Cols.DESCRIPTION + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
