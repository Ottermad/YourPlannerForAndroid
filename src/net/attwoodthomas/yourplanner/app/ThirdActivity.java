package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends Activity {
    public DatabaseHelper db = new DatabaseHelper(this);
    private int mCounter = 0;
    private String mDay;
    private EditText mPeriod1;
    private EditText mPeriod2;
    private EditText mPeriod3;
    private EditText mPeriod4;
    private EditText mPeriod5;
    private EditText mPeriod6;
    private TextView mTitle;
    private Button mUpdateButton;
    private String mWeek;
    String TAG = "Third Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		mPeriod1 = (EditText) findViewById(R.id.editText);
        mPeriod2 = (EditText) findViewById(R.id.editText2);
        mPeriod3 = (EditText) findViewById(R.id.editText3);
        mPeriod4 = (EditText) findViewById(R.id.editText4);
        mPeriod5 = (EditText) findViewById(R.id.editText5);
        mPeriod6 = (EditText) findViewById(R.id.editText6);
        mTitle = (TextView) findViewById(R.id.textView32);
        mUpdateButton = (Button) findViewById(R.id.button31);
        

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String day = "";

                if (mCounter < 4) {
                    mWeek = "A";
                }
                else if (mCounter > 3) {
                    mWeek = "B";
                }

                if (mCounter == 0 || mCounter == 5) {
                    mDay = "Monday";
                    day = "Tuesday";
                }

                else if (mCounter == 1 || mCounter == 6) {
                    mDay = "Tuesday";
                    day = "Wednesday";
                }

                else if (mCounter == 2 || mCounter == 7) {
                    mDay = "Wednesday";
                    day = "Thursday";
                }

                else if (mCounter == 3 || mCounter == 8) {
                    mDay = "Thursday";
                    day = "Friday";
                }

                else if (mCounter == 4 || mCounter == 9) {
                    mDay = "Friday";
                    
                }
                
                if (mCounter == 4) {
                	day = "Monday";
                
                }

                ThirdActivity.this.db.updateLessons(ThirdActivity.this.mWeek, ThirdActivity.this.mDay, ThirdActivity.this.mPeriod1.getText().toString(), ThirdActivity.this.mPeriod2.getText().toString(), ThirdActivity.this.mPeriod3.getText().toString(), ThirdActivity.this.mPeriod4.getText().toString(), ThirdActivity.this.mPeriod5.getText().toString(), ThirdActivity.this.mPeriod6.getText().toString());

                if (mCounter == 9) {
                    Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    mCounter = mCounter + 1;
                }

                mTitle.setText("Week " + mWeek + " " + day);
                mPeriod1.setText("Period 1");
                mPeriod2.setText("Period 2");
                mPeriod3.setText("Period 3");
                mPeriod4.setText("Period 4");
                mPeriod5.setText("Period 5");
                mPeriod6.setText("Period 6");
            }
        });
	}
	
    protected void onDestroy()
    {
        Log.w(TAG, "App destroyed");
        super.onDestroy();
    }


    protected void onStop()
    {
        Log.w(TAG, "App stopped");
        super.onStop();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

}
