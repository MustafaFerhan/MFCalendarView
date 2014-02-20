package com.mustafaferhan;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * @since Dec 15 2013
 * @author Mustafa Ferhan Akman
 * */
@SuppressLint("DefaultLocale")
public class Util {

	/**
	 * @param date yyyy-MM-dd (ex: 2013-12-26)
	 * @param hour HH:mm:ss (ex: 12:15:00)
	 * */
	public static String makePrettyDate(String date, String hour){
		String strDate = date + " " + hour;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d = null;
		try {
			d = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//		Log.d("","makePrettyDate::" +date);
		//		Log.d("","makePrettyDate::" );

		Calendar.getInstance().setTime(d);
		return String.format(new Locale("TR"),"%02d.%02d.%02d %02d:%02d", 
				d.getDate(), d.getMonth() + 1, d.getYear()+1900, 
				d.getHours(), d.getMinutes());
	}

	/**
	 * @param date yyyy-MM-dd (ex: 2013-12-26)
	 * */
	public static String makePrettyDate(String date){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date d = null;

			d = formatter.parse(date);


			//		Log.d("","makePrettyDate::" +date);
			//		Log.d("","makePrettyDate::" );

			Calendar.getInstance().setTime(d);
			return String.format(new Locale("TR"),"%02d.%02d.%02d", 
					d.getDate(), d.getMonth() + 1, d.getYear()+1900);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}


	/**
	 * Herhangi bir tarih bilgisini Long'a çevirir
	 * 
	 * @param date like 2013-12-17
	 * */
	public static long dateToLong(String date){

		SimpleDateFormat formatter = 
				new SimpleDateFormat("yyyy-MM-dd", new Locale("TR"));

		Date d = null;
		try {
			d = formatter.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		Calendar c = Calendar.getInstance();
		c.setTime(d); 

		return c.getTimeInMillis();
	}


	/**
	 * @return 
	 * */
	public static String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", new Locale("TR"));
		return df.format(c.getTime());
	}

	/**
	 * @param format "yyyy-MM-dd"
	 * */
	public static String getCurrentDate(String format){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format, new Locale("TR"));
		return df.format(c.getTime());
	}

	public static String getTomorrow(){
		long oneDay = 86400000; // 86400000 miliseconds equals 1 day
		long tomorrow = Util.dateToLong(Util.getCurrentDate()) + oneDay;
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(tomorrow));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", getLocale());
		return df.format(c.getTime());
	}
	
	
	public static Locale getLocale(){
		return Locale.getDefault();
	}
}


