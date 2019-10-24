package aericks1.example.playbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    // in class CrimeActivity
    private static final String EXTRA_PLAY_ID = "Extra play id";

    public static Intent newIntent(Context packageContext, UUID playId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_PLAY_ID, playId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID playID = (UUID) getIntent().getSerializableExtra(EXTRA_PLAY_ID);
        return PlayFragment.newInstance(playID);
    }
}
