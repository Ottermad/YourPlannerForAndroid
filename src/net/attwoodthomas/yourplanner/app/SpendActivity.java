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
    private Button mSubmit;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spend);
		
		mAmount = (EditText) findViewById(R.id.editText);
        mSubmit = (Button) findViewById(R.id.button);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                Intent intent = new Intent(SpendActivity.this, MeritsActivity.class);
                startActivity(intent);

            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spend, menu);
		return true;
	}

}