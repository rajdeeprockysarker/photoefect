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

public class takeeffect extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	Handler hndlr;
	ProgressDialog progressDialog;
	Bitmap new_bitmap[] = new Bitmap[20];
	Bitmap sourceBitmap;
	Button back_button;
	ImageButton save, share, set_desktop;
 
	ImageButton  button_change_to_vague,
			button_change_to_oid, button_change_to_neon,
			button_change_to_pixelate, button_change_to_invert,
			button_change_to_tv, button_change_to_block, button_change_to_old,
			button_change_to_sharpen, button_change_to_light,
			button_change_to_source,button_change_to_gray,button_change_to_relief;

	String path;
	ImageView image ;
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
		button_change_to_gray = (ImageButton) findViewById(R.id.button_change_to_gray);
		button_change_to_relief = (ImageButton) findViewById(R.id.button_change_to_relief);
		button_change_to_vague = (ImageButton) findViewById(R.id.button_change_to_vague);
		button_change_to_oid = (ImageButton) findViewById(R.id.button_change_to_oid);
		button_change_to_neon = (ImageButton) findViewById(R.id.button_change_to_neon);
		button_change_to_pixelate = (ImageButton) findViewById(R.id.button_change_to_pixelate);
		button_change_to_invert = (ImageButton) findViewById(R.id.button_change_to_invert);
		button_change_to_tv = (ImageButton) findViewById(R.id.button_change_to_tv);
		button_change_to_block = (ImageButton) findViewById(R.id.button_change_to_block);
		button_change_to_old = (ImageButton) findViewById(R.id.button_change_to_old);
		button_change_to_sharpen = (ImageButton) findViewById(R.id.button_change_to_sharpen);
		button_change_to_light = (ImageButton) findViewById(R.id.button_change_to_light);
		button_change_to_source = (ImageButton) findViewById(R.id.button_change_to_source);
		
		
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
				Toast.makeText(takeeffect.this, "save", Toast.LENGTH_LONG)
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
							.getInstance(takeeffect.this);
					wallpaperManager.setBitmap(Share_save_desktop);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_change_to_gray:			
			parse(0);			
			break;
		case R.id.button_change_to_relief:
			parse(1);
			break;
		case R.id.button_change_to_vague:
			parse(2);
			break;
		case R.id.button_change_to_oid:
			parse(3);
			break;
		case R.id.button_change_to_neon:
			parse(4);
			break;
		case R.id.button_change_to_pixelate:
			parse(5);
			break;
		case R.id.button_change_to_invert:
			parse(6);
			break;
		case R.id.button_change_to_tv:
			parse(7);
			break;
		case R.id.button_change_to_block:
			parse(8);
			break;
		case R.id.button_change_to_old:
			parse(9);
			break;
		case R.id.button_change_to_sharpen:
			parse(10);
			break;
		case R.id.button_change_to_light:
			parse(11);
			break;
		case R.id.button_change_to_source:
			parse(12);
			break;
		}
	}
	public void parse(final int no_of_button){
	
		if(new_bitmap[no_of_button]==null){
			
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
							if(no_of_button==0)
							new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
									BitmapFilter.GRAY_STYLE);
							else if (no_of_button==1) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.RELIEF_STYLE);
							}
							else if (no_of_button==2) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.VAGUE_STYLE);
							}
							else if (no_of_button==3) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.OIL_STYLE); 
							}
							else if (no_of_button==4) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.NEON_STYLE); 
							}
							else if (no_of_button==5) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.PIXELATE_STYLE); 
							}
							else if (no_of_button==6) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.INVERT_STYLE); 
							}
							else if (no_of_button==7) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.TV_STYLE); 
							}
							else if (no_of_button==8) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.BLOCK_STYLE); 
							}
							else if (no_of_button==9) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.OLD_STYLE); 
							}
							else if (no_of_button==10) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.SHARPEN_STYLE); 
							}
							else if (no_of_button==11) {
								new_bitmap[no_of_button] = BitmapFilter.changeStyle(sourceBitmap,
										BitmapFilter.LIGHT_STYLE); 
							}
							else if (no_of_button==12) {
								new_bitmap[no_of_button] = sourceBitmap; 
							}
							
						
							
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
								image.setImageBitmap(new_bitmap[no_of_button]);
								Share_save_desktop = new_bitmap[no_of_button];
							} catch (Exception e) {
  
							}

							// set as destination

						} catch (Exception e) {

						}

					}
				}
			};
			
			
		}
		else{
			image.setImageBitmap(new_bitmap[no_of_button]);
			Share_save_desktop = new_bitmap[no_of_button];
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
	
	
}