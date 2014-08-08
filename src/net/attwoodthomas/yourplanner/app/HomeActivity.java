package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
	
	// Widgets
    private Button mTimetableButton;
    private Button mHomeworkButton;
    private Button mMeritsButton;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		 mHomeworkButton = (Button) findViewById(R.id.button2);
	        mTimetableButton = (Button) findViewById(R.id.button);
	        mMeritsButton = (Button) findViewById(R.id.button3);

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
