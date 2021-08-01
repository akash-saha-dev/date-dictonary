package com.date_dictonary;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class DictonaryService {

	public static void main(String[] args) {
		//first Condition
		Map<String,Integer> inputData=new LinkedHashMap<>();
		inputData.put("2020-01-01", 4);
		inputData.put("2020-01-02", 4);
		inputData.put("2020-01-03", 6);
		inputData.put("2020-01-04", 8);
		inputData.put("2020-01-05", 2);
		inputData.put("2020-01-06", -6);
		inputData.put("2020-01-07", 2);
		inputData.put("2020-01-08", -2);
		//second condition
		Map<String,Integer> inputData1=new LinkedHashMap<>();
		inputData1.put("2020-01-01", 6);
		inputData1.put("2020-01-04", 12);
		inputData1.put("2020-01-05", 14);
		inputData1.put("2020-01-06", 2);
		inputData1.put("2020-01-07", 4);
		Map<String,Integer> output=solution(inputData);
		output.entrySet().stream().forEach((date)-> {
			if(date.getKey().equals("Mon")) {
				System.out.print("'"+date.getKey()+"': "+date.getValue());
			}else {
				System.out.print(", '"+date.getKey()+"': "+date.getValue());
			}
		});
	}
	
	/**
	 * @param dateCorrespondenses
	 * @return Map<Date, Correspondence>
	 */
	public static Map<String,Integer> solution(Map<String,Integer> dateCorrespondences) {
		Map<String,Integer> output=new LinkedHashMap<>();
		
		// all weekdays added
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] shortWeekdays = dfs.getShortWeekdays();
        for (String shortWeekday : shortWeekdays) {
            if(!shortWeekday.isBlank()) output.put(shortWeekday, 0);
        }
        // calculation of weekdays
		dateCorrespondences.entrySet().stream().forEach((dateCorrespondence)->{
			String dateStr=dateCorrespondence.getKey();
			int dateVal=dateCorrespondence.getValue();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date=null;
			try {
				date = format.parse(dateStr);
			} catch (ParseException e) {
				System.out.println("Exception occured in parsing date from string due to : " + e.getMessage());
			}
			String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date).substring(0, 3);
			output.put(dayOfWeek, output.get(dayOfWeek) + dateVal);
		});
		//shifting "Sunday" to the last position		
		int sundayValue=output.get("Sun");
		output.remove("Sun");
		output.put("Sun", sundayValue);
		// Populating values for days which do not have value
		ArrayList<String> keyList = new ArrayList<String>(output.keySet());
		for(int i = 0; i < 7; i++) {
		    String key = keyList.get(i);
		    int value = output.get(key);
		    if(value == 0) {
		    	int prev = output.get((output.keySet().toArray())[i-1]) != null ? output.get((output.keySet().toArray())[i-1]) : 0;
		    	int next = output.get((output.keySet().toArray())[i+1]) != null ? output.get((output.keySet().toArray())[i+1]) : 0;
		    	if(prev == 0) {
		    		output.put(key, next-2);
		    	}else if(next == 0){
		    		output.put(key, prev+2);
		    	}else {
		    		output.put(key, ((prev+next)/2));
		    	}
		    }
		}
		return output;
	}

}
