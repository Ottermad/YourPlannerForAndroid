package net.attwoodthomas.yourplanner.app;

import java.util.ArrayList;
import java.util.HashMap;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SeventhActivity extends ListActivity {
	
	public static ArrayList<HashMap<String, String>> mHomeworkDue = new ArrayList<HashMap<String, String>>();
    private String TAG = "Foruth Activity";
    public static Intent intent;
    public static int pos = 0;
    private Button mBackButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seventh);
		
		mBackButton = (Button) findViewById(R.id.button71);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeventhActivity.this, HomeworkActivity.class);
                startActivity(intent);
            }
        });

        mHomeworkDue = new ArrayList<HashMap<String, String>>();
        new DatabaseHelper(this).getCompletedHomework();

        String[] keys = {"Subject", "DueDate"};
        int[] ids = {android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(this, mHomeworkDue, android.R.layout.simple_list_item_2, keys, ids);
        setListAdapter(adapter);
		
		
	}
	
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        intent = new Intent(SeventhActivity.this, EigthActivity.class);
        pos =position;
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        mHomeworkDue = new ArrayList<HashMap<String, String>>();
        new DatabaseHelper(this).getCompletedHomework();
    }

    protected void onDestroy()
    {
        Log.w(TAG, "App destroyed");
        super.onDestroy();
    }


    protected void onStop()
    {
        Log.w(this.TAG, "App stopped");
        super.onStop();
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seventh, menu);
		return true;
	}

}
