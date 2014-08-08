package net.attwoodthomas.yourplanner.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeworkActivity extends Activity {
	
	// Widgets
    private Button mCheckHomeworkButton;
    private Button mAddHomeworkButton;
    private Button mCheckDoneHomeworkButton;
    private Button mBackButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homework);
		
		mCheckHomeworkButton = (Button) findViewById(R.id.button);
        mAddHomeworkButton = (Button) findViewById(R.id.button2);
        mCheckDoneHomeworkButton = (Button) findViewById(R.id.button3);
        mBackButton = (Button) findViewById(R.id.button4);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeworkActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // mCheckHomeworkButton
        mCheckHomeworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeworkActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });

        // mAddHomeworkButton
        mAddHomeworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeworkActivity.this, FifthActivity.class);
                startActivity(intent);
            }
        });

        // mCompletedHomeworkButton
        mCheckDoneHomeworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeworkActivity.this, SeventhActivity.class);
                startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.homework, menu);
		return true;
	}

}
