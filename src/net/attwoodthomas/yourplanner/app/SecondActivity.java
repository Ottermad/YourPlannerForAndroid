package net.attwoodthomas.yourplanner.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {
	
	// Member Variables
    public static String period1;
    public static String period2;
    public static String period3;
    public static String period4;
    public static String period5;
    public static String period6;
    private String TAG = "SecondActivity";
    private Button mGoBackButton;
    private TextView mPeriod1TextView;
    private TextView mPeriod2TextView;
    private TextView mPeriod3TextView;
    private TextView mPeriod4TextView;
    private TextView mPeriod5TextView;
    private TextView mPeriod6TextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		// Set widgets
        mPeriod1TextView = (TextView) findViewById(R.id.textView3);
        mPeriod2TextView = (TextView) findViewById(R.id.textView5);
        mPeriod3TextView = (TextView) findViewById(R.id.textView7);
        mPeriod4TextView = (TextView) findViewById(R.id.textView9);
        mPeriod5TextView = (TextView) findViewById(R.id.textView11);
        mPeriod6TextView = (TextView) findViewById(R.id.textView13);
        mGoBackButton = (Button) findViewById(R.id.button21);

        mGoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
