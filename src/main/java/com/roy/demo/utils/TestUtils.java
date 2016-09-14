package com.roy.demo.utils;

import java.util.ArrayList;
import java.util.List;

import com.roy.demo.model.CourseInfo;

public class TestUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseInfo info1 = new CourseInfo();
		info1.setId(1);
		info1.setCname("1");
		
		CourseInfo info2 = new CourseInfo();
		info2.setId(2);
		info2.setCname("2");
		CourseInfo info3 = new CourseInfo();
		info3.setId(3);
		info3.setCname("3");
		CourseInfo info4 = new CourseInfo();
		info4.setId(4);
		info4.setCname("4");
		
		CourseInfo info5 = new CourseInfo();
		info5.setId(1);
		info5.setCname("5");
		CourseInfo info6 = new CourseInfo();
		info6.setId(2);
		info6.setCname("6");
//		List<CourseInfo> lin3 = new ArrayList<CourseInfo>();
		List<CourseInfo> lin1 = new ArrayList<CourseInfo>();
		lin1.add(info1);
		lin1.add(info2);
		lin1.add(info3);
		lin1.add(info4);
		List<CourseInfo> lin2 = new ArrayList<CourseInfo>();
		lin2.add(info5);
		lin2.add(info6);
		
		lin1.removeAll(lin2);

	}

}
