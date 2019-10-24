package aericks1.example.playbook;

import androidx.fragment.app.Fragment;

public class PlayListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PlayListFragment();
    }
}
