package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
	
	// Widgets
    private Button mTimetableButton;
    private Button mHomeworkButton;
    private Button mMeritsButton;
    private Button mRateUsButton;
    private Context context;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		 	mHomeworkButton = (Button) findViewById(R.id.button2);
	        mTimetableButton = (Button) findViewById(R.id.button);
	        mMeritsButton = (Button) findViewById(R.id.button3);
	        mRateUsButton = (Button) findViewById(R.id.button5);

	        mHomeworkButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Intent intent = new Intent(HomeActivity.this, HomeworkActivity.class);
	                startActivity(intent);
	            }
	        });

	        mTimetableButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
	                startActivity(intent);
	            }
	        });

	        mMeritsButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Intent intent = new Intent(HomeActivity.this, MeritsActivity.class);
	                startActivity(intent);
	            }
	        });
	        
	        mRateUsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
					Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
					try {
					  startActivity(goToMarket);
					} catch (ActivityNotFoundException e) {
					  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
					}*/
					
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
