package com.date_dictonary;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictonaryServiceTest {
	
	DictonaryService dictonaryService;
	
	@BeforeEach                                         
    void setUp() {
		dictonaryService = new DictonaryService();
    }
	
	@SuppressWarnings("static-access")
	@Test                                               
    @DisplayName("test Case 1")   
    void testDictonaryService1() {
		Map<String,Integer> inputData=new LinkedHashMap<>();
		inputData.put("2020-01-01", 4);
		inputData.put("2020-01-02", 4);
		inputData.put("2020-01-03", 6);
		inputData.put("2020-01-04", 8);
		inputData.put("2020-01-05", 2);
		inputData.put("2020-01-06", -6);
		inputData.put("2020-01-07", 2);
		inputData.put("2020-01-08", -2);
		Map<String, Integer> output = dictonaryService.solution(inputData);
        Assertions.assertEquals(-6, output.get("Mon"));
        Assertions.assertEquals(8, output.get("Sat"));
    }
	
	@SuppressWarnings("static-access")
	@Test                                               
    @DisplayName("test Case 2")   
    void testDictonaryService2() {
		Map<String,Integer> inputData=new LinkedHashMap<>();
		inputData.put("2020-01-01", 6);
		inputData.put("2020-01-04", 12);
		inputData.put("2020-01-05", 14);
		inputData.put("2020-01-06", 2);
		inputData.put("2020-01-07", 4);
		Map<String, Integer> output = dictonaryService.solution(inputData);
        Assertions.assertEquals(10, output.get("Fri"));
        Assertions.assertEquals(4, output.get("Tue"));
    }
	
}
