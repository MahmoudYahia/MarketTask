package co.myahia.markettask.features.home;

import android.os.Bundle;

import co.myahia.markettask.R;
import co.myahia.markettask.util.ActivityUtils;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), MainFragment.getInstance(), R.id.content_frame);
    }


}
