package com.mustafaferhan;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.mustafaferhan.mfcalendarview.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @since Jan 8 2014 
 * @author Mustafa Ferhan Akman
 */

public class MFCalendarView extends LinearLayout{

	private static final String TODAY = "today";

	private GregorianCalendar month;
	private CalendarAdapter calendaradapter;

	private Handler handler;
	private ExpandableHeightGridView gridview;
	private String currentSelectedDate;
	private String initialDate;
	private View view;
	private Locale locale;

	onMFCalendarViewListener calendarListener;

	public MFCalendarView(Context context) {
		super(context);
		init(context);
	}

	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public MFCalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public MFCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	void init(Context context){
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = li.inflate(R.layout.mf_calendarview, null, false);

		month = (GregorianCalendar) GregorianCalendar.getInstance();

		month.setTimeInMillis(Util.dateToLong(getInitialDate()));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Util.getLocale());
		currentSelectedDate = df.format(month.getTime());

		calendaradapter = new CalendarAdapter(context, month);

		gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);
		gridview.setAdapter(calendaradapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		TextView title = (TextView) view.findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		RelativeLayout previous = (RelativeLayout) view.findViewById(R.id.previous);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setPreviousMonth();
				refreshCalendar();
			}
		});

		RelativeLayout next = (RelativeLayout) view.findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();
			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {


				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				currentSelectedDate = CalendarAdapter.dayString.get(position);

				String[] separatedTime = currentSelectedDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				// navigate to next or previous month on clicking offdays.
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 15) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter()).setSelected(v);

				month.setTimeInMillis(Util.dateToLong(currentSelectedDate));
				calendaradapter.initCalendarAdapter(month, calendarListener);

				if (calendarListener != null) 
					calendarListener.onDateChanged(currentSelectedDate);
			}
		});

		addView(view);
	}

	protected void setNextMonth() {
		if (month.get(GregorianCalendar.MONTH) == 
				month.getActualMaximum(GregorianCalendar.MONTH)) {
			
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
			
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}
	}

	protected void setPreviousMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

	}

	protected void showToast(String string) {
		Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
	}

	public int getSelectedMonth(){
		return month.get(GregorianCalendar.MONTH) + 1;
	}

	public int getSelectedYear(){
		return month.get(GregorianCalendar.YEAR);
	}

	public void refreshCalendar() {

		TextView title = (TextView) view.findViewById(R.id.title);

		calendaradapter.refreshDays();
		//calendaradapter.notifyDataSetChanged();
		handler.post(calendarUpdater); 

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		if (calendarListener != null) {
			calendarListener.onDisplayedMonthChanged(
					month.get(GregorianCalendar.MONTH) + 1, 
					month.get(GregorianCalendar.YEAR), 
					(String) DateFormat.format("MMMM", month));
		}

	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {

			gridview.setExpanded(true);

			Log.d("", "month:"+ (month.get(GregorianCalendar.MONTH)+1) + 
					" year:" + month.get(GregorianCalendar.YEAR));

			calendaradapter.notifyDataSetChanged();
		}
	};

	public void setOnCalendarViewListener(onMFCalendarViewListener c){
		calendarListener = c;
	}

	public String getInitialDate(){
		if (initialDate == null) {
			return Util.getCurrentDate();
		}
		return initialDate;
	}

	/**
	 * @date "yyyy-MM-dd"
	 * */
	public void setDate(String date){
		if (date.equals(MFCalendarView.TODAY)) {
			initialDate = Util.getCurrentDate();
		}
		else{
			initialDate = date;
		}

		initialDate = date;
		currentSelectedDate = date;
		month.setTimeInMillis(Util.dateToLong(date));
		calendaradapter.initCalendarAdapter(month, calendarListener);

	}

	public String getSelectedDate(){
		return currentSelectedDate;
	}

	/**
	 * @param date like this format: "2014-01-15"
	 * */
	public void setEvents(ArrayList<String> dates){
		calendaradapter.setItems(dates);
		handler.post(calendarUpdater);
	}
}
