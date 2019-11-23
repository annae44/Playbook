package aericks1.example.playbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayLab {
    // SEE PAGE 277 FOR DEMOLITION
    private static PlayLab sPlayLab;
    //private List<Play> mPlays;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PlayLab get(Context context) {
        if (sPlayLab == null) {
            sPlayLab = new PlayLab(context);
        }
        return sPlayLab;
    }


    public PlayLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PlayBaseHelper(mContext).getWritableDatabase();
        //mPlays = new ArrayList<>();
        //for (int i = 1; i <= 25; i++) {
        //    Play play = new Play();
        //    play.setTitle("Play #" + i);
        //    mPlays.add(play);
        //}
    }

    public List<Play> getPlays() {
        List<Play> plays = new ArrayList<>();
        PlayCursorWrapper cursor = queryPlays(null ,null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                plays.add(cursor.getPlay());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return plays;
    }

    public Play getPlay(UUID id) {

        PlayCursorWrapper cursor = queryPlays(PlayDbSchema.PlayTable.Cols.UUID + " = ?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPlay();
        } finally {
            cursor.close();
        }
    }

    public void updatePlay(Play play){
        String uuidString = play.getId().toString();
        ContentValues values = getContentValues(play);

        mDatabase.update(PlayDbSchema.PlayTable.NAME, values,
                PlayDbSchema.PlayTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private PlayCursorWrapper queryPlays(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(PlayDbSchema.PlayTable.NAME,
                               null,
                                        whereClause,
                                        whereArgs,
                                null,
                                 null,
                                null);
        return new PlayCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Play play) {
        ContentValues values = new ContentValues();
        values.put(PlayDbSchema.PlayTable.Cols.UUID, play.getId().toString());
        values.put(PlayDbSchema.PlayTable.Cols.TITLE, play.getTitle());
        values.put(PlayDbSchema.PlayTable.Cols.DESCRIPTION, play.getDescription());
        return values;
    }

    public void addPlay(Play p) {
        ContentValues values = getContentValues(p);
        mDatabase.insert(PlayDbSchema.PlayTable.NAME, null, values);
    }

    public File getPhotoFile (Play play) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, play.getPhotoFilename());
    }
}
