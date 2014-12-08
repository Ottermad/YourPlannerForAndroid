package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FifthActivity extends Activity {
	
	public DatabaseHelper db = new DatabaseHelper(this);

    private EditText mSubject;
    private EditText mDate;
    private EditText mDescription;
    private Button mHomeButton;
    private Button mBackButton;
    private CalendarView mCalender;
    private String curDate;

    private String TAG = "Fifth Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		
		mSubject = (EditText) findViewById(R.id.editText51);

        mDescription = (EditText) findViewById(R.id.editText53);
        mHomeButton = (Button) findViewById(R.id.button51);
        mBackButton = (Button) findViewById(R.id.button52);


        if (android.os.Build.VERSION.SDK_INT >= 11) {

            mCalender = (CalendarView) findViewById(R.id.calendarView);

            mCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    // add one because month starts at 0
                    month = month + 1;
                    // output to log cat **not sure how to format year to two places here**
                    curDate = dayOfMonth + "/" + month + "/" + year;
                    Log.d("NEW_DATE", curDate);
                }
            });

        } else {
            mDate = (EditText) findViewById(R.id.editText53);
        }

        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectValue = mSubject.getText().toString();

                String descriptionValue = mDescription.getText().toString();

                if (android.os.Build.VERSION.SDK_INT < 11) {
                    curDate = mDate.getText().toString();
                }

                if (TextUtils.isEmpty(subjectValue) || TextUtils.isEmpty(curDate) || TextUtils.isEmpty(descriptionValue)) {
                    // TODO: Create a AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(FifthActivity.this);
                    builder.setMessage("Please enter a value for all fields")
                            .setTitle("Opps!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    db.addHomework(subjectValue, curDate, descriptionValue);
                    Intent intent = new Intent(FifthActivity.this, HomeActivity.class);
                    startActivity(intent);

                }

            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectValue = mSubject.getText().toString();

                String descriptionValue = mDescription.getText().toString();

                if (android.os.Build.VERSION.SDK_INT < 11) {
                    curDate = mDate.getText().toString();
                }

                if (TextUtils.isEmpty(subjectValue) || TextUtils.isEmpty(curDate) || TextUtils.isEmpty(descriptionValue)) {
                    // TODO: Create a AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(FifthActivity.this);
                    builder.setMessage("Please enter a value for all fields")
                            .setTitle("Opps!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    db.addHomework(subjectValue, curDate, descriptionValue);
                    Intent intent = new Intent(FifthActivity.this, HomeworkActivity.class);
                    startActivity(intent);

                }
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fifth, menu);
		return true;
	}

}
