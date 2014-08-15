package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SixthActivity extends Activity {
	
	// Widgets
    private TextView mSubjectView;
    private TextView mDateView;
    private TextView mDescriptionView;
    private Button mHomeButton;
    private Button mBackButton;
    private Button mDeleteButton;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sixth);
		
        mSubjectView = (TextView) findViewById(R.id.textView61);
        mDateView = (TextView) findViewById(R.id.textView62);
        mDescriptionView = (TextView) findViewById(R.id.textView63);
        mHomeButton = (Button) findViewById(R.id.button);
        mDeleteButton = (Button) findViewById(R.id.button2);
        mBackButton = (Button) findViewById(R.id.button3);

        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SixthActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] data = {mSubjectView.getText().toString(), mDateView.getText().toString(), mDescriptionView.getText().toString()};
                db.deleteHomework(data);
                Intent intent = new Intent(SixthActivity.this, HomeworkActivity.class);
                startActivity(intent);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SixthActivity.this, HomeworkActivity.class);
                startActivity(intent);
            }
        });
	}
	
	  protected void onResume() {
	        super.onResume();
	        int pos = FourthActivity.pos;
	        String subject = FourthActivity.mHomeworkDue.get(pos).get("Subject");
	        String date = FourthActivity.mHomeworkDue.get(pos).get("DueDate");
	        String[] data =  new DatabaseHelper(this).returnAllValues(subject, date);
	        mSubjectView.setText(data[0]);
	        mDateView.setText(data[1]);
	        mDescriptionView.setText(data[2]);

	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sixth, menu);
		return true;
	}

}
