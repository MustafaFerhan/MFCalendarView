package com.mustafaferhan.mfcalendarview_demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.Util;
import com.mustafaferhan.onMFCalendarViewListener;
import com.mustafaferhan.mfcalendarview.R;

public class MainActivity extends Activity {

	MFCalendarView mf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mf = (MFCalendarView) findViewById(R.id.mFCalendarView);

		mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
			
			@Override
			public void onDisplayedMonthChanged(int month, int year, String monthStr) {

				StringBuffer bf = new StringBuffer()
				.append(" month:")
				.append(month)
				.append(" year:")
				.append(year)
				.append(" monthStr: ")
				.append(monthStr);
				
				Toast.makeText(MainActivity.this,  bf.toString(),
						Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void onDateChanged(String date) {
			
				Toast.makeText(MainActivity.this, "onDateChanged:" + date, 
						Toast.LENGTH_SHORT).show();
			}
		});
		
		/**
		 * you can set calendar date anytime
		 * */
		//mf.setDate("2014-02-19");
		
		
		/**
		 * calendar events samples 
		 * */
		ArrayList<String> eventDays = new ArrayList<String>();
		eventDays.add("2014-02-25");
		eventDays.add(Util.getTomorrow());
		eventDays.add(Util.getCurrentDate());
		
		
		mf.setEvents(eventDays);
		
		Log.e("","locale:" + Util.getLocale());
		
	}
}
