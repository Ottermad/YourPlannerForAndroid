package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.app.AlertDialog;
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

                String dayValue = ThirdActivity.this.mDay;
                String weekValue = ThirdActivity.this.mWeek;
                String period1Value = ThirdActivity.this.mPeriod1.getText().toString();
                String period2Value = ThirdActivity.this.mPeriod2.getText().toString();
                String period3Value = ThirdActivity.this.mPeriod3.getText().toString();
                String period4Value = ThirdActivity.this.mPeriod4.getText().toString();
                String period5Value = ThirdActivity.this.mPeriod5.getText().toString();
                String period6Value = ThirdActivity.this.mPeriod6.getText().toString();

                if (period1Value.isEmpty() || period2Value.isEmpty() || period3Value.isEmpty() || period4Value.isEmpty() || period5Value.isEmpty() || period6Value.isEmpty()) {
                    // TODO: Create a AlertDialog
                    AlertDialog.Builder builder= new AlertDialog.Builder(ThirdActivity.this);
                    builder.setMessage("Please enter a value for all periods")
                            .setTitle("Opps!")
                            .setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {

                    String day = "";

                    if (mCounter < 4) {
                        mWeek = "A";
                    } else if (mCounter > 3) {
                        mWeek = "B";
                    }

                    if (mCounter == 0 || mCounter == 5) {
                        mDay = "Monday";
                        day = "Tuesday";
                    } else if (mCounter == 1 || mCounter == 6) {
                        mDay = "Tuesday";
                        day = "Wednesday";
                    } else if (mCounter == 2 || mCounter == 7) {
                        mDay = "Wednesday";
                        day = "Thursday";
                    } else if (mCounter == 3 || mCounter == 8) {
                        mDay = "Thursday";
                        day = "Friday";
                    } else if (mCounter == 4 || mCounter == 9) {
                        mDay = "Friday";

                    }

                    if (mCounter == 4) {
                        day = "Monday";

                    }

                    ThirdActivity.this.db.updateLessons(weekValue, dayValue, period1Value, period2Value, period3Value, period4Value, period5Value, period6Value);

                    if (mCounter == 9) {
                        Intent intent = new Intent(ThirdActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        mCounter = mCounter + 1;
                    }

                    mTitle.setHint("Week " + mWeek + " " + day);
                    mPeriod1.setHint("Period 1");
                    mPeriod2.setHint("Period 2");
                    mPeriod3.setHint("Period 3");
                    mPeriod4.setHint("Period 4");
                    mPeriod5.setHint("Period 5");
                    mPeriod6.setHint("Period 6");
                }

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
