package aericks1.example.playbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class PlayPagerActivity extends AppCompatActivity {
    private static final String EXTRA_PLAY_ID = "extra_play_id";

    private ViewPager mViewPager;
    private List<Play> mPlays;

    public static Intent newIntent(Context packageContext, UUID playId) {
        Intent intent = new Intent(packageContext, PlayPagerActivity.class);
        intent.putExtra(EXTRA_PLAY_ID, playId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_pager);

        mViewPager = (ViewPager) findViewById(R.id.play_view_pager);

        UUID playId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAY_ID);

        mPlays = PlayLab.get(this).getPlays();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Play play = mPlays.get(position);
                return PlayFragment.newInstance(play.getId());
            }

            @Override
            public int getCount() {
                return mPlays.size();
            }
        });

        for (int i = 0; i < mPlays.size(); i++) {
            if (mPlays.get(i).getId().equals(playId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
