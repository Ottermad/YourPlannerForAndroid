package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FifthActivity extends Activity {
	
	public DatabaseHelper db = new DatabaseHelper(this);

    private EditText mSubject;
    private EditText mDate;
    private EditText mDescription;
    private Button mAddButton;

    private String TAG = "Fifth Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		
		mSubject = (EditText) findViewById(R.id.editText51);
        mDate = (EditText) findViewById(R.id.editText2);
        mDescription = (EditText) findViewById(R.id.editText53);
        mAddButton = (Button) findViewById(R.id.button51);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addHomework(mSubject.getText().toString(), mDate.getText().toString(), mDescription.getText().toString());
                Intent intent = new Intent(FifthActivity.this, HomeworkActivity.class);
                startActivity(intent);

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
