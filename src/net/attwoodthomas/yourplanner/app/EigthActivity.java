package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EigthActivity extends Activity {
	
	// Widgets
    TextView subjectView;
    TextView dateView;
    TextView descriptionView;
    Button backButton;
    Button deleteButton;
    private Button mHomeButton;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eigth);
		
		subjectView = (TextView) findViewById(R.id.textView81);
        dateView = (TextView) findViewById(R.id.textView82);
        descriptionView = (TextView) findViewById(R.id.textView83);
        backButton = (Button) findViewById(R.id.button83);
        deleteButton = (Button) findViewById(R.id.button82);
        mHomeButton = (Button) findViewById(R.id.button81);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EigthActivity.this, SeventhActivity.class);
                startActivity(intent);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] data = {subjectView.getText().toString(), dateView.getText().toString(), descriptionView.getText().toString()};
                db.deleteHomeworkForever(data);
                Intent intent = new Intent(EigthActivity.this, SeventhActivity.class);
                startActivity(intent);
            }
        });

        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EigthActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
	}
	
	   protected void onResume() {
	        super.onResume();
	        int pos = SeventhActivity.pos;
	        String subject = SeventhActivity.mHomeworkDue.get(pos).get("Subject");
	        String date = SeventhActivity.mHomeworkDue.get(pos).get("DueDate");
	        String[] data =  new DatabaseHelper(this).returnAllDoneValues(subject, date);
	        subjectView.setText(data[0]);
	        dateView.setText(data[1]);
	        descriptionView.setText(data[2]);

	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eigth, menu);
		return true;
	}

}
