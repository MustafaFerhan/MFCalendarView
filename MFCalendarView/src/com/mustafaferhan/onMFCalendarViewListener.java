package com.mustafaferhan;


/**
 * @since Jan 8 2014
 * @author Mustafa Ferhan Akman
 * */
public interface onMFCalendarViewListener {

	void onDateChanged(String date);

	/**
	 * @param month first month is: 1 (January) and last month is: 12 (Dec)
	 * @param year
	 * @param monthStr provides local month name like January (English) or Ocak (Turkish)
	 * */
	void onDisplayedMonthChanged(int month, int year, String monthStr);
	
}
