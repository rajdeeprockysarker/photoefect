package com.tuli.raj.photoefect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class testwholeopen extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	Handler hndlr;
	ProgressDialog progressDialog;
	Bitmap new_bitmap[] = new Bitmap[20];
	Bitmap sourceBitmap;
	Button back_button;
	ImageButton save, share, set_desktop;

	ImageView button_change_to_relief, button_change_to_vague,
			button_change_to_oid, button_change_to_neon,
			button_change_to_pixelate, button_change_to_invert,
			button_change_to_tv, button_change_to_block, button_change_to_old,
			button_change_to_sharpen, button_change_to_light,
			button_change_to_source, button_change_to_gray, image;

	String path;

	Bitmap Share_save_desktop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		path = getIntent().getExtras().getString("image_path");
		Log.v("dfgdfdfdfdfgdsffg", path);

		sourceBitmap = BitmapFactory.decodeFile(path);
		Share_save_desktop = sourceBitmap;

		set_desktop = (ImageButton) findViewById(R.id.set_background);
		save = (ImageButton) findViewById(R.id.save);
		share = (ImageButton) findViewById(R.id.share);

		back_button = (Button) findViewById(R.id.back_button);
		button_change_to_gray = (ImageView) findViewById(R.id.button_change_to_gray);
		button_change_to_relief = (ImageView) findViewById(R.id.button_change_to_relief);
		button_change_to_vague = (ImageView) findViewById(R.id.button_change_to_vague);
		button_change_to_oid = (ImageView) findViewById(R.id.button_change_to_oid);
		button_change_to_neon = (ImageView) findViewById(R.id.button_change_to_neon);
		button_change_to_pixelate = (ImageView) findViewById(R.id.button_change_to_pixelate);
		button_change_to_invert = (ImageView) findViewById(R.id.button_change_to_invert);
		button_change_to_tv = (ImageView) findViewById(R.id.button_change_to_tv);
		button_change_to_block = (ImageView) findViewById(R.id.button_change_to_block);
		button_change_to_old = (ImageView) findViewById(R.id.button_change_to_old);
		button_change_to_sharpen = (ImageView) findViewById(R.id.button_change_to_sharpen);
		button_change_to_light = (ImageView) findViewById(R.id.button_change_to_light);
		button_change_to_source = (ImageView) findViewById(R.id.button_change_to_source);
		
		
		image = (ImageView) findViewById(R.id.image);
		image.setImageBitmap(sourceBitmap);

		button_change_to_gray.setOnClickListener(this);
		button_change_to_relief.setOnClickListener(this);
		button_change_to_vague.setOnClickListener(this);
		button_change_to_oid.setOnClickListener(this);
		button_change_to_neon.setOnClickListener(this);
		button_change_to_pixelate.setOnClickListener(this);
		button_change_to_invert.setOnClickListener(this);
		button_change_to_tv.setOnClickListener(this);
		button_change_to_block.setOnClickListener(this);
		button_change_to_old.setOnClickListener(this);
		button_change_to_sharpen.setOnClickListener(this);
		button_change_to_light.setOnClickListener(this);
		button_change_to_source.setOnClickListener(this);

		back_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				save();
				Toast.makeText(testwholeopen.this, "save", Toast.LENGTH_LONG)
						.show();
			}
		});
		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				if (android.os.Environment.getExternalStorageState().equals(
						android.os.Environment.MEDIA_MOUNTED)) {
					try {
						File sdCard = Environment.getExternalStorageDirectory();
						File dir = new File(sdCard.getAbsolutePath() + "/PhotoEdit");
						dir.mkdirs();
						File file = new File(dir, "temp.png");
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						Share_save_desktop.compress(Bitmap.CompressFormat.PNG, 100,
								baos);
						FileOutputStream f = null;
						f = new FileOutputStream(file);

						if (f != null) {
							f.write(baos.toByteArray());
							f.flush();
							f.close();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				
				Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("image/*");
				share
						.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/PhotoEdit/temp.png"));

				startActivityForResult(Intent.createChooser(share,
						"Share Image"), 0);
			}
		});

		set_desktop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					WallpaperManager wallpaperManager = WallpaperManager
							.getInstance(testwholeopen.this);
					wallpaperManager.setBitmap(Share_save_desktop);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
 
		// sourceBitmap =
		// BitmapFactory.decodeResource(this.getResources(),R.drawable.aamir);

		try { 
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("Processing...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.show();

			new Thread() {
   
				@Override
				public void run() {
					Looper.prepare();
					try {

						new_bitmap[0] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.GRAY_STYLE); 

						new_bitmap[1] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.OIL_STYLE);

						new_bitmap[2] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.RELIEF_STYLE);

						new_bitmap[3] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.VAGUE_STYLE);
 
						new_bitmap[4] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.NEON_STYLE);

						new_bitmap[5] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.PIXELATE_STYLE);

						new_bitmap[6] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.INVERT_STYLE);

						new_bitmap[7] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.TV_STYLE);

						new_bitmap[8] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.BLOCK_STYLE);

						new_bitmap[9] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.OLD_STYLE);

						new_bitmap[10] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.SHARPEN_STYLE);

						new_bitmap[11] = BitmapFilter.changeStyle(sourceBitmap,
								BitmapFilter.LIGHT_STYLE);
						
						
						progressDialog.dismiss();
						hndlr.sendEmptyMessage(0);

						// currentThread().destroy();

					} catch (Exception e) {
						Log.v("111", "Error");
						progressDialog.dismiss();
					}
					Looper.loop();

					Log.v("1", "1");

				};
			}.start();
		} catch (Exception e) { // TODO: handle exception
			Log.v("Exception", "Exception");
		}

		hndlr = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				if (msg.what == 0) {
					try {
						try {
							Log.v("complete", "complete");

							button_change_to_gray.setImageBitmap(new_bitmap[0]);
							button_change_to_oid.setImageBitmap(new_bitmap[1]);
							button_change_to_relief
									.setImageBitmap(new_bitmap[2]);
							button_change_to_vague
									.setImageBitmap(new_bitmap[3]);
							button_change_to_neon.setImageBitmap(new_bitmap[4]);
							button_change_to_pixelate
									.setImageBitmap(new_bitmap[5]);
							button_change_to_invert
									.setImageBitmap(new_bitmap[6]);
							button_change_to_tv.setImageBitmap(new_bitmap[7]);
							button_change_to_block
									.setImageBitmap(new_bitmap[8]);
							button_change_to_old.setImageBitmap(new_bitmap[9]);
							button_change_to_sharpen
									.setImageBitmap(new_bitmap[10]);
							button_change_to_light
									.setImageBitmap(new_bitmap[11]);
							
							

						} catch (Exception e) {

						}

						// set as destination

					} catch (Exception e) {

					}

				}
			}
		};

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.button_change_to_gray:
			image.setImageBitmap(new_bitmap[0]);
			Share_save_desktop = new_bitmap[0];
			break;
		case R.id.button_change_to_oid:
			image.setImageBitmap(new_bitmap[1]);
			Share_save_desktop = new_bitmap[1];
			break;
		case R.id.button_change_to_relief:
			image.setImageBitmap(new_bitmap[2]);
			Share_save_desktop = new_bitmap[2];
			break;
		case R.id.button_change_to_vague:
			image.setImageBitmap(new_bitmap[3]);
			Share_save_desktop = new_bitmap[3];
			break;
		case R.id.button_change_to_neon:
			image.setImageBitmap(new_bitmap[4]);
			Share_save_desktop = new_bitmap[4];
			break;
		case R.id.button_change_to_pixelate:
			image.setImageBitmap(new_bitmap[5]);
			Share_save_desktop = new_bitmap[5];
			break;
		case R.id.button_change_to_invert:
			image.setImageBitmap(new_bitmap[6]);
			Share_save_desktop = new_bitmap[6];
			break;
		case R.id.button_change_to_tv:
			image.setImageBitmap(new_bitmap[7]);
			Share_save_desktop = new_bitmap[7];
			break;
		case R.id.button_change_to_block:
			image.setImageBitmap(new_bitmap[8]);
			Share_save_desktop = new_bitmap[8];
			break;
		case R.id.button_change_to_old:
			image.setImageBitmap(new_bitmap[9]);
			Share_save_desktop = new_bitmap[9];
			break;
		case R.id.button_change_to_sharpen:
			image.setImageBitmap(new_bitmap[10]);
			Share_save_desktop = new_bitmap[10];
			break;
		case R.id.button_change_to_light:
			image.setImageBitmap(new_bitmap[11]);
			Share_save_desktop = new_bitmap[11];
			break;

		}

	}

	public void save() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			try {
				File sdCard = Environment.getExternalStorageDirectory();
				File dir = new File(sdCard.getAbsolutePath() + "/PhotoEdit");
				dir.mkdirs();
				File file = new File(dir, new SimpleDateFormat(
						"yyyyMMdd_HHmmss").format(Calendar.getInstance()
						.getTime())
						+ ".png");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Share_save_desktop.compress(Bitmap.CompressFormat.PNG, 100,
						baos);
				FileOutputStream f = null;
				f = new FileOutputStream(file);

				if (f != null) {
					f.write(baos.toByteArray());
					f.flush();
					f.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {

				Toast.makeText(testwholeopen.this, "ffghfghfgh",
						Toast.LENGTH_LONG).show();

			}

		}
	}

}