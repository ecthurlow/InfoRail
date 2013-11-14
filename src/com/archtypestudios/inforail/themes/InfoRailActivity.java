package com.archtypestudios.inforail.themes;

import com.archtypestudios.inforail.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class InfoRailActivity extends Activity {
	
	protected TextView title;
	protected TextView subTitle;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean titled = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_home);
        if(titled){
        	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
        	
        	title = (TextView) findViewById(R.id.title);
        	subTitle = (TextView) findViewById(R.id.subtitle);
        	
        }
    }
}
