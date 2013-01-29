package com.tuli.raj.photoefect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class mainactivity extends Activity { 
	Button btn_cammera, btn_sdcard;
	int CAMERA_PIC_REQUEST = 0;
	ImageView imgView;	 

	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		btn_cammera = (Button) findViewById(R.id.cammer);
		btn_sdcard = (Button) findViewById(R.id.sdcard);
 

		/*
		 * btn_cammera = (Button) findViewById(R.id.btn_cammera); btn_sdcard =
		 * (Button) findViewById(R.id.btn_sdcard);
		 * 
		 * 
		 * btn_cammera.setCompoundDrawables(null,
		 * getResources().getDrawable(R.drawable.ic_launcher), null, null);
		 */
		btn_cammera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent camera_intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(camera_intent, 0);

				/*
				 * Intent inti =new
				 * Intent(mainactivity.this,RagnarokFilterActivity.class);
				 * inti.putExtra("value", 0); startActivity(inti);
				 */

			}
		});

		btn_sdcard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 1);

				/*
				 * Intent inti =new
				 * Intent(mainactivity.this,RagnarokFilterActivity.class);
				 * inti.putExtra("value", 1); startActivity(inti);
				 */
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Log.v("first", "first");
		Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

				if (android.os.Environment.getExternalStorageState().equals(
						android.os.Environment.MEDIA_MOUNTED)) {
					try {
						File sdCard = Environment.getExternalStorageDirectory();
						File dir = new File(sdCard.getAbsolutePath()
								+ "/PhotoEdit"); 
						dir.mkdirs();
						File file = new File(dir, "temp.png"); 
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						thumbnail
								.compress(Bitmap.CompressFormat.PNG, 100, baos);
						FileOutputStream f = null;
						f = new FileOutputStream(file);

						if (f != null) {
							f.write(baos.toByteArray());
							f.flush();
							f.close();
						}
						String path = Environment.getExternalStorageDirectory()+ "/PhotoEdit/temp.png";
						Intent inti = new Intent(mainactivity.this,
								takeeffect.class);
						inti.putExtra("image_path",
								path);
						startActivity(inti);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}
		break;
		case 1:
			if (resultCode == RESULT_OK) {
				try {
					Log.v("second", "second");
					Uri selectedImage = data.getData();
					/*
					 * InputStream imageStream =
					 * getContentResolver().openInputStream(selectedImage);
					 * Bitmap yourSelectedImage =
					 * BitmapFactory.decodeStream(imageStream);
					 * imgView.setImageBitmap(yourSelectedImage);
					 * 
					 * 
					 * Intent inti =new
					 * Intent(mainactivity.this,testwholeopen.class);
					 * inti.putExtra("image", yourSelectedImage);
					 * startActivity(inti);
					 */

					String[] projection = { MediaStore.Images.Media.DATA };
					Cursor cursor = managedQuery(selectedImage, projection,
							null, null, null);
					int column_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

					cursor.moveToFirst();

					Bitmap b = BitmapFactory.decodeFile(cursor
							.getString(column_index), null);

					Log.v("selected path", cursor.getString(column_index));

					Intent inti = new Intent(mainactivity.this,
							takeeffect.class);
					inti.putExtra("image_path", cursor.getString(column_index));
					startActivity(inti);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("error", e.getMessage());
				}
			}

		}

	}
}
