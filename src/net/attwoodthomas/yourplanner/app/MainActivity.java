package net.attwoodthomas.yourplanner.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
	
    // Member Variables
    public static String mWeek;
    public static String mDay;

    // Widget Variables
    private RadioGroup weekGroup;
    private RadioGroup dayGroup;
    private Button checkButton;
    private Button updateButton;
    private Button mBackButton;

    private String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Widget Variables
        weekGroup = (RadioGroup) findViewById(R.id.radioGroup);
        dayGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        checkButton = (Button) findViewById(R.id.button);
        updateButton = (Button) findViewById(R.id.button2);
        mBackButton = (Button) findViewById(R.id.button3);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        // OnClickListener for checkButton
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Set Week Variable
                System.out.println(weekGroup.getCheckedRadioButtonId());
                if (weekGroup.getCheckedRadioButtonId() == 2131230742) {
                    mWeek = "A";
                }

                else if (weekGroup.getCheckedRadioButtonId() == 2131230743) {
                    mWeek = "B";

                }

                // Set Day Variable
                System.out.println(dayGroup.getCheckedRadioButtonId());
                if (dayGroup.getCheckedRadioButtonId() == 2131230745) {
                    mDay = "Monday";
                }
                else if (dayGroup.getCheckedRadioButtonId() == 2131230746) {
                    mDay = "Tuesday";
                }
                else if (dayGroup.getCheckedRadioButtonId() == 2131230747) {
                    mDay = "Wednesday";
                }
                else if (dayGroup.getCheckedRadioButtonId() == 2131230748) {
                    mDay = "Thursday";
                }
                else if (dayGroup.getCheckedRadioButtonId() == 2131230749) {
                    mDay = "Friday";
                }

                Intent checkIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(checkIntent);

            }
        });

        // OnClickListener for UpdateButton

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateIntent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(updateIntent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
