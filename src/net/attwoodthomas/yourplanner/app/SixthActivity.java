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
    TextView subjectView;
    TextView dateView;
    TextView descriptionView;
    Button backButton;
    Button deleteButton;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sixth);
		
        subjectView = (TextView) findViewById(R.id.textView61);
        dateView = (TextView) findViewById(R.id.textView62);
        descriptionView = (TextView) findViewById(R.id.textView63);
        backButton = (Button) findViewById(R.id.button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SixthActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        deleteButton = (Button) findViewById(R.id.button2);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] data = {subjectView.getText().toString(), dateView.getText().toString(), descriptionView.getText().toString()};
                db.deleteHomework(data);
                Intent intent = new Intent(SixthActivity.this, MainActivity.class);
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
	        subjectView.setText(data[0]);
	        dateView.setText(data[1]);
	        descriptionView.setText(data[2]);

	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sixth, menu);
		return true;
	}

}
