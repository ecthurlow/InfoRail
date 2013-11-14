package com.archtypestudios.inforail.themes;

import com.archtypestudios.inforail.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoRailActivity extends Activity {
	
	protected TextView title;
	protected ImageView icon;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
 
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
        
        setContentView(R.layout.activity_home);
 
        title = (TextView) findViewById(R.id.title);
        icon  = (ImageView) findViewById(R.id.icon);
        icon.setImageResource(R.drawable.ic_launcher);
    }
}
