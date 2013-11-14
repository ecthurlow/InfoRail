package com.archtypestudios.inforail.themes;

import com.archtypestudios.inforail.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class InfoRailActivity extends Activity {
	
	protected TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.activity_home);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);

        title = (TextView) findViewById(R.id.title);
        title.setText(R.string.app_name);
    }
}
