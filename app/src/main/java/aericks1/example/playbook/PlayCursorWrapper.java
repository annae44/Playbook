package aericks1.example.playbook;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class PlayCursorWrapper extends CursorWrapper {
    public PlayCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Play getPlay() {
        String uuidString = getString(getColumnIndex(PlayDbSchema.PlayTable.Cols.UUID));
        String title = getString(getColumnIndex(PlayDbSchema.PlayTable.Cols.TITLE));
        String description = getString(getColumnIndex(PlayDbSchema.PlayTable.Cols.DESCRIPTION));

        Play play = new Play(UUID.fromString(uuidString));
        play.setTitle(title);
        play.setDescription(description);

        return play;
    }
}
