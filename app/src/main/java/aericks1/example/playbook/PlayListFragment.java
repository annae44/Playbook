package aericks1.example.playbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayListFragment extends Fragment {
    private RecyclerView mPlayRecyclerView;
    private PlayAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);
        mPlayRecyclerView = (RecyclerView) view.findViewById(R.id.play_recycler_view);
        mPlayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_play_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_play:
                Play play = new Play();
                PlayLab.get(getActivity()).addPlay(play);
                Intent intent = PlayPagerActivity.newIntent(getActivity(), play.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        PlayLab playLab = PlayLab.get(getActivity());
        List<Play> plays = playLab.getPlays();
        if (mAdapter == null) {
            mAdapter = new PlayAdapter(plays);
            mPlayRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setPlays(plays);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class PlayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Play mPlay;

        public PlayHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_play, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.play_title);
        }

        public void bind(Play play) {
            mPlay = play;
            mTitleTextView.setText(mPlay.getTitle());
        }

        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mPlay.getId());
            Intent intent = PlayPagerActivity.newIntent(getActivity(), mPlay.getId());
            startActivity(intent);
        }
    }

    private class PlayAdapter extends RecyclerView.Adapter<PlayHolder> {
        private List<Play> mPlays;

        public PlayAdapter(List<Play> plays) {
            mPlays = plays;
        }

        @Override
        public PlayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PlayHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayHolder holder, int position) {
            Play play = mPlays.get(position);
            holder.bind(play);
        }

        @Override
        public int getItemCount() {
            return mPlays.size();
        }

        public void setPlays(List<Play> plays) {
            mPlays = plays;
        }
    }
}