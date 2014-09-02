package net.attwoodthomas.yourplanner.app;

import net.attwoodthomas.yourplanner.app.database.helper.DatabaseHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends Activity {
	
	// Widgets
    private Button mTimetableButton;
    private Button mHomeworkButton;
    private Button mMeritsButton;
    private TextView mName;
    private Context context;
    private ImageButton mImage;
    private TextView mHomeworkDone;
    private TextView mHomeworkDue;
    private TextView mTotalMerits;
    private static final int TAKE_PHOTO_REQUEST = 0;
    private static final int PICK_PHOTO_REQUEST = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;
    protected Uri mMediaUri;
    public DatabaseHelper db = new DatabaseHelper(this);
    public String[] camera_choices = {"Take Photo"};
    private String TAG = "HomeActivity";



    protected DialogInterface.OnClickListener mDialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which) {
                case 0:
                    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    System.out.println(mMediaUri);
                    if (mMediaUri == null) {
                        Toast.makeText(HomeActivity.this, "There was a problem accessing your devices external storage.", Toast.LENGTH_LONG).show();

                    } else {
                        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                        startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST);
                    }
                case 1:
                    break;
            }


        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            db.insertPhoto(mMediaUri.toString());

        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(HomeActivity.this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
    }

    private Uri getOutputMediaFileUri(int mediaType) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        if (isExternalStorageAvailable()) {
            // get the external storage directory
            String appName = HomeActivity.this.getString(R.string.app_name);
            File mediaStorageDir= new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appName);

            // create a subdirectory
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.e(TAG, "Failed to create directory");
                    return null;
                }
            }

            // create filename
            // create the file
            File mediaFile;
            Date now = new Date();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK).format(now);

            String path = mediaStorageDir.getPath() + File.separator;
            if (mediaType == MEDIA_TYPE_IMAGE) {
                mediaFile = new File(path + "IMG_" + timestamp + ".jpg");
            } else {
                return null;
            }

            // Return the Uri
            return Uri.fromFile(mediaFile);


        } else {
            return null;
        }

    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

            if (findViewById(R.id.textView2) != null) {
                mName = (TextView) findViewById(R.id.textView2);


                System.out.println(db.checkName());
                if (!db.checkName()) {
                    Intent intent = new Intent(HomeActivity.this, NameActivity.class);
                    startActivity(intent);
                }
                mName.setText(db.getName());
            }

            mHomeworkDue = (TextView) findViewById(R.id.textView4);
            mHomeworkDone = (TextView) findViewById(R.id.textView5);
            mTotalMerits = (TextView) findViewById(R.id.textView6);
            mImage = (ImageButton) findViewById(R.id.imageView);


		 	mHomeworkButton = (Button) findViewById(R.id.button2);
	        mTimetableButton = (Button) findViewById(R.id.button);
	        mMeritsButton = (Button) findViewById(R.id.button3);

            int height =  mImage.getHeight();
            mImage.setMinimumWidth(height);

            try {
                Uri imageUri = Uri.parse(db.getPhotoPath());

                mImage.setImageURI(imageUri);

            } catch (Exception e) {
                Log.e(TAG, e.toString());
                mImage.setImageResource(R.drawable.plus);
            }

            if (findViewById(R.id.textView2) != null) {
                mName = (TextView) findViewById(R.id.textView2);
                mName.setText(db.getName());
            }
            String[] merits = db.getMerits();
            int homework = db.getNumberOfHomework();
            int completed_homework = db.getNumberOfCompletedHomework();
            mTotalMerits.setText(merits[0] + " Merits");
            mHomeworkDue.setText(Integer.toString(homework) + " Due");
            mHomeworkDone.setText(Integer.toString(completed_homework) + " Completed");

            mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

                    builder.setItems(camera_choices, mDialogListener);
                    AlertDialog dialog = builder.create();
                    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                    dialog.show();



                }
            });


	        mHomeworkButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Intent intent = new Intent(HomeActivity.this, HomeworkActivity.class);
	                startActivity(intent);
	            }
	        });

	        mTimetableButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
	                startActivity(intent);
	            }
	        });

	        mMeritsButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Intent intent = new Intent(HomeActivity.this, MeritsActivity.class);
	                startActivity(intent);
	            }
	        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

    protected void onResume() {
        super.onResume();
        try {
            Uri imageUri = Uri.parse(db.getPhotoPath());

            mImage.setImageURI(imageUri);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            mImage.setImageResource(R.drawable.plus);
        }
    }

}
