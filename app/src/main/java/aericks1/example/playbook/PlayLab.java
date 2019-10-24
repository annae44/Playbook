package aericks1.example.playbook;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayLab {
    private static PlayLab sPlayLab;
    private List<Play> mPlays;

    public static PlayLab get(Context context) {
        if (sPlayLab == null) {
            sPlayLab = new PlayLab(context);
        }
        return sPlayLab;
    }

    // fully loaded model layer with 1000 plays
    public PlayLab(Context context) {
        mPlays = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Play play = new Play();
            play.setTitle("Play #" + i);
            mPlays.add(play);
        }
    }

    public List<Play> getPlays() {
        return mPlays;
    }

    public Play getPlay(UUID id) {
        for (Play play : mPlays) {
            if (play.getId().equals(id)) {
                return play;
            }
        }
        return null;
    }
}
