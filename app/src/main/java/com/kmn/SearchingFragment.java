package com.kmn;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchingFragment extends Fragment {

    private EditText mSearchingEditText;
    private Button mOpeningBrowserBtn;
    public static final String urlGoogle =  "http://google.com/search?q=";
    public static final String urlYandex = "https://yandex.ru/search/?text=";
    public static final String urlBing = "https://www.bing.com/search?q=";
    SearchingEngine searchingEngine = new SearchingEngine();

    private View.OnClickListener mOnOpeningBrowserBtnClickList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            whichURL();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fr_search, container, false);

        mSearchingEditText = view.findViewById(R.id.etInputText);
        mOpeningBrowserBtn = view.findViewById(R.id.btn_openingBrowser);

        mOpeningBrowserBtn.setOnClickListener(mOnOpeningBrowserBtnClickList);

        return view;

    }

    private void whichURL() {
        switch (searchingEngine.SEARCH_ENGINE){
            case "google" :
                openingBrowser(urlGoogle);
                break;
            case "yandex" :
                openingBrowser(urlYandex);
                break;
            case "bing":
                openingBrowser(urlBing);
                break;
            default: break;
        }
    }
    private void openingBrowser(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( url + mSearchingEditText.getText().toString() ));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try
        {
            startActivity(intent);
        }
        catch (ActivityNotFoundException ex)
        {
            //if Chrome browser not installed
            intent.setPackage(null);
            startActivity(intent);
        }
    }
}
