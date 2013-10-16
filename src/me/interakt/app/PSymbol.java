package me.interakt.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

public class PSymbol {
	// s - static, m - atributo da classe, l - local, p - parametro
	static Context sContext;
	private static int sDefaultColor = Color.BLUE;
	Bitmap mImage;
	String mLabel, mId;
	int mBorderColor;
	
	

	PSymbol(PSymbol s) {
		// TODO mImage = new Bitmap();
		this.mLabel = new String(s.mLabel);
		this.mId = new String(s.mId);
		this.mBorderColor = s.mBorderColor;
		mBorderColor = PSymbol.getDefaultColor();
	}

	PSymbol() {
		// TODO mImage = new Bitmap();
		mLabel = new String();
		mId = new String();
		mBorderColor = PSymbol.getDefaultColor();

	}

	PSymbol(Bitmap pImage, String pLabel, String pId, int pBorderColor) {
		// TODO mImage = new Bitmap();
		mLabel = new String(pLabel);
		mId = new String(pId);
		mBorderColor = pBorderColor;
	}

	static void setContext(Context pContext) {
		sContext = pContext;
	}

	public static int getDefaultColor() {
		return sDefaultColor;
	}

	public static void setDefaultColor(int sDefaultColor) {
		PSymbol.sDefaultColor = sDefaultColor;
	}
}
