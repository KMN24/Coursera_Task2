package com.kmn;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment exitFragment = new MainFragment();
        FragmentTransaction  ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, exitFragment);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings :
                showMessage(R.string.settings);
                settingsOpenFragment();
                break;
            case R.id.search :
                showMessage(R.string.search);
                searchOpenFragment();
                break;
            case R.id.exit :
                showMessage(R.string.exit);
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showMessage(@StringRes int stringDisplay) {
        Toast.makeText(this, stringDisplay, Toast.LENGTH_LONG).show();
    }

    private void settingsOpenFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentTransaction  ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, settingsFragment);
        ft.addToBackStack("tag");
        ft.commit();

    }
    private void searchOpenFragment(){
        SearchingFragment searchFragment = new SearchingFragment();
        FragmentTransaction  ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, searchFragment);
        ft.addToBackStack("tag");
        ft.commit();
    }

}
