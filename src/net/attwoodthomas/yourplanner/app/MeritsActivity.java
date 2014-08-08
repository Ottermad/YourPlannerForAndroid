package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeritsActivity extends Activity {
	
	// Widgets
    private TextView totalMerits;
    private TextView usedMerits;
    private Button mBackButton;
    private Button mSpendButton;
    private Button mAddButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_merits);
		
		totalMerits = (TextView) findViewById(R.id.textView);
        usedMerits = (TextView) findViewById(R.id.textView2);
        mBackButton = (Button) findViewById(R.id.button3);
        mSpendButton = (Button) findViewById(R.id.button2);
        mAddButton = (Button) findViewById(R.id.button);

        String[] merits = new DatabaseHelper(this).getMerits();
        totalMerits.setText(merits[0]);
        usedMerits.setText(merits[1]);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeritsActivity.this, HomeworkActivity.class);
                startActivity(intent);

            }
        });

        mSpendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeritsActivity.this, SpendActivity.class);
                startActivity(intent);

            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeritsActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });

	}
	
	public void onResume()
    {
        super.onResume();
        String[] merits = new DatabaseHelper(this).getMerits();
        totalMerits.setText(merits[0]);
        usedMerits.setText(merits[1]);

    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.merits, menu);
		return true;
	}

}
