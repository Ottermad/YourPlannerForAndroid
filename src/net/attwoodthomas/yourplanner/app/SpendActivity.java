package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SpendActivity extends Activity {
	
	// Widgets
    private EditText mAmount;
    private Button mSubmitHomeButton;
    private Button mSubmitBackButton;
    private Button mHomeButton;
    private Button mBackButton;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spend);
		
		mAmount = (EditText) findViewById(R.id.editText);
        mSubmitHomeButton = (Button) findViewById(R.id.button);
        mSubmitBackButton = (Button) findViewById(R.id.button2);
        mBackButton = (Button) findViewById(R.id.button3);
        mHomeButton = (Button) findViewById(R.id.button4);

        mSubmitHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spendMerits();

                Intent intent = new Intent(SpendActivity.this, HomeActivity.class);
                startActivity(intent);

            }


        });

        mSubmitBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spendMerits();
                Intent intent = new Intent(SpendActivity.this, MeritsActivity.class);
                startActivity(intent);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpendActivity.this, MeritsActivity.class);
                startActivity(intent);
            }
        });

        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpendActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

	}

    private void spendMerits() {
        if (db.spendMerits(mAmount.getText().toString()) == true) {
            Context context = getApplicationContext();
            CharSequence text = "Success :)";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Failed :(";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spend, menu);
		return true;
	}

}
