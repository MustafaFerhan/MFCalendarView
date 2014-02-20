MFCalendarView
==============

MFCalendarView is a custom event base CalendarView for Android.


#Usage

#####include MFCalendarView in your layout XML as follows:
```xml
<com.mustafaferhan.MFCalendarView
	android:id="@+id/mFCalendarView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" >
</com.mustafaferhan.MFCalendarView>
    
```

#####MFCalendarView has a own date listeners:

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

#####You can set events

```java

	ArrayList<String> eventDays = new ArrayList<String>();
	eventDays.add("2014-02-25");
	eventDays.add(Util.getCurrentDate());
	
	mf.setEvents(eventDays);
		
```

#Contributing

Want to contribute? You are welcome!
