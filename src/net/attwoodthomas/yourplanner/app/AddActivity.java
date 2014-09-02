package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;

public class AddActivity extends Activity {
	
	// Widgets
    private EditText mAmount;
    private EditText mCode;
    private Button mHome;
    private Button mBack;
    public DatabaseHelper db = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addmerits);
		
		mAmount = (EditText) findViewById(R.id.editText);
        mHome = (Button) findViewById(R.id.button);
        mCode = (EditText) findViewById(R.id.editText2);
        mBack = (Button) findViewById(R.id.button2);

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountValue = mAmount.getText().toString();
                String codeValue = mCode.getText().toString();
                if (amountValue.isEmpty() || codeValue.isEmpty()) {
                    // TODO: Create a AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    builder.setMessage("Please enter a value for all fields")
                            .setTitle("Opps!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {

                    if (db.addMerits(codeValue, amountValue) == true) {
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

                    Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountValue = mAmount.getText().toString();
                String codeValue = mCode.getText().toString();
                if (amountValue.isEmpty() || codeValue.isEmpty()) {
                    // TODO: Create a AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    final LayoutInflater inflater = getLayoutInflater();
                    builder.setView(inflater.inflate(R.layout.dialog, null))
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {

                    if (db.addMerits(codeValue, amountValue) == true) {
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

                    Intent intent = new Intent(AddActivity.this, MeritsActivity.class);
                    startActivity(intent);
                }

            }
        });



	}





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

}
