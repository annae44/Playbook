package aericks1.example.playbook;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class PlayFragment extends Fragment {

    private Play mPlay;
    private EditText mTitleField;
    private EditText mDescriptionField;
    public static final String ARG_PLAY_ID = "Play ID";

    public static PlayFragment newInstance(UUID playID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAY_ID, playID);
        PlayFragment fragment = new PlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID playId = (UUID) getArguments().getSerializable((ARG_PLAY_ID));
        mPlay = PlayLab.get(getActivity()).getPlay(playId);

        //mPlay = new Play();
    }

    @Override
    public void onPause() {
        super.onPause();
        PlayLab.get(getActivity()).updatePlay(mPlay);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_play, container, false);

        mTitleField = (EditText) v.findViewById(R.id.play_title);
        mTitleField.setText(mPlay.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // left empty
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPlay.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // left empty
            }
        });

        mDescriptionField = (EditText) v.findViewById(R.id.description_title);
        mDescriptionField.setText(mPlay.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // left empty
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPlay.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // left empty
            }
        });



        return v;
    }
}
