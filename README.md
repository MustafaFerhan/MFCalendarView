MFCalendarView
==============

MFCalendarView is a custom event base CalendarView for Android.

It based on http://www.androiddevelopersolution.com/2013/05/android-calendar-sync.html but I have developed some features and fixed several bugs.

![Screenshot](https://raw2.github.com/MustafaFerhan/MFCalendarView/master/calendarview-half.png)


#Usage

#####include MFCalendarView in your layout XML as follows:
```xml
<com.mustafaferhan.MFCalendarView
	android:id="@+id/mFCalendarView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" >
</com.mustafaferhan.MFCalendarView>
```

#####MFCalendarView has own listeners:

```java
mf = (MFCalendarView) findViewById(R.id.mFCalendarView);
mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
			
	@Override
		public void onDisplayedMonthChanged(int month, int year, String monthStr) {
				//codes		
			}
			
			@Override
			public void onDateChanged(String date) {
				//codes
			}
	});
```

#####You can set events:

```java

	ArrayList<String> eventDays = new ArrayList<String>();
	eventDays.add("2014-02-25");
	eventDays.add(Util.getCurrentDate());
	
	mf.setEvents(eventDays);
```

#Contributing

Want to contribute? You are welcome!
