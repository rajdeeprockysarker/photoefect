package com.tuli.raj.photoefect;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;


public class BitmapFilter {
	
	public static final int GRAY_STYLE = 1;  
	public static final int RELIEF_STYLE = 2; 
	public static final int VAGUE_STYLE = 3; 
	public static final int OIL_STYLE = 4;  
	public static final int NEON_STYLE = 5; 
	public static final int PIXELATE_STYLE = 6;  
	public static final int TV_STYLE = 7; 
	public static final int INVERT_STYLE = 8; 
	public static final int BLOCK_STYLE = 9; 
	public static final int OLD_STYLE = 10; 
	public static final int SHARPEN_STYLE = 11; 
	public static final int LIGHT_STYLE = 12; 
	public static final int LOMO_STYLE = 13;
	
	public static Bitmap changeStyle(Bitmap bitmap, int styleNo) {
		if (styleNo == GRAY_STYLE) {
			//return changeToGray(bitmap);
			return GrayFilter.changeToGray(bitmap);
		}
		else if (styleNo == RELIEF_STYLE) {
			return ReliefFilter.changeToRelief(bitmap);
		}
		else if (styleNo == VAGUE_STYLE) {
			return VagueFilter.changeToVague(bitmap);
		}
		else if (styleNo == OIL_STYLE) {
			return OilFilter.changeToOil(bitmap);
		}
		else if (styleNo == NEON_STYLE) {
			return NeonFilter.changeToNeon(bitmap);
		}
		else if (styleNo == PIXELATE_STYLE) {
			return PixelateFilter.changeToPixelate(bitmap);
		}
			
		else if (styleNo == TV_STYLE) {
			return TvFilter.changeToTV(bitmap);
		}
		else if (styleNo == INVERT_STYLE) {
			return InvertFilter.chageToInvert(bitmap);
		}
		else if (styleNo == BLOCK_STYLE) {
			return BlockFilter.changeToBrick(bitmap);
		}
		else if (styleNo == OLD_STYLE) {
			return OldFilter.changeToOld(bitmap);
		}
		else if (styleNo == SHARPEN_STYLE) {
			return SharpenFilter.changeToSharpen(bitmap);
		}
		else if (styleNo == LIGHT_STYLE) {
			return LightFilter.changeToLight(bitmap);
		}
		else if (styleNo == LOMO_STYLE) {
			return LomoFilter.changeToLomo(bitmap);
		}
			return bitmap;
	}
	
	

}

















