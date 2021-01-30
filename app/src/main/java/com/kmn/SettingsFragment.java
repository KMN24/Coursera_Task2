package com.kmn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private RadioButton mGoogle;
    private RadioButton mYandex;
    private RadioButton mBing;
    private RadioGroup mRadioGroup;
    public static final String SHARED_PREF = "SHARED_PREF";
    private static final String GOOGLE_KEY = "GOOGLE_KEY";
    private static final String YANDEX_KEY = "YANDEX_KEY";
    private static final String BING_KEY = "BING_KEY";
    Browser searchingEngine = new Browser();

    public SettingsFragment() {
        super(R.layout.fr_settings);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fr_settings, container, false);
        mGoogle = view.findViewById(R.id.rbGoogle);
        mYandex = view.findViewById(R.id.rbYandex);
        mBing = view.findViewById(R.id.rbBing);
        mRadioGroup = view.findViewById(R.id.radio_group);
        mGoogle.setChecked(updateData(GOOGLE_KEY));
        mYandex.setChecked(updateData(YANDEX_KEY));
        mBing.setChecked(updateData(BING_KEY));

        mGoogle.setChecked(updateData(GOOGLE_KEY));
        mYandex.setChecked(updateData(YANDEX_KEY));
        mBing.setChecked(updateData(BING_KEY));

        mGoogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    whichSearchinEngine("google");
                }
                saveIntoSharedPrefs(GOOGLE_KEY, isChecked);
            }
        });

        mYandex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    whichSearchinEngine("yandex");
                }
                saveIntoSharedPrefs(YANDEX_KEY, isChecked);
            }
        });
        mBing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    whichSearchinEngine("bing");
                }
                saveIntoSharedPrefs(BING_KEY, isChecked);
            }
        });

        return view;
    }

    private void saveIntoSharedPrefs(String key, boolean value){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    private boolean updateData(String key){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    private void whichSearchinEngine(String serchingEng){
        searchingEngine.SEARCH_ENGINE = serchingEng;
    }

}
