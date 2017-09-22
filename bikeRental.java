package Solution;

import java.util.*;

public class LimeBike {
	static final String AM = "AM";
	static final String PM = "PM";
	// use time class instead of an integer for better code readiness /debug purpose. although the speed will go down a bit
	static class Time {
		
		int hour = 0;
		int min = 0;
		Boolean am = null;
		public Time(String t) {
			String[] segs = t.trim().split(" ");
			String[] nums = segs[0].split(":");
			hour = Integer.parseInt(nums[0].trim());
			min = Integer.parseInt(nums[1].trim());
			String postfix = segs[1].trim();
			if(postfix.equalsIgnoreCase(AM)) {
				am = true;
			} else if(postfix.equalsIgnoreCase(PM)) {
				am = false;
			}
			if(hour > 12 || hour < 0 || min <0 || min >= 61 || am == null ) // min 60 is possible in edge cases i remember	
				throw new IllegalArgumentException("Unknow time input " + t); // unknown time
			 
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(hour);
			sb.append(":");
			if(min < 10)	sb.append("0"); // padding 
			sb.append(min);
			sb.append(" ");
			if (am) {
				sb.append(AM);
			} else {
				sb.append(PM);
			}
			return sb.toString();
		}
	}
	
	static class Point {
		Time time;
		int diff; 
		public Point(Time t, int d) {
			time = t;
			diff = d;
		}
	}
	
	public String timelineSummary(List<String> input ) {
		StringBuilder ret = new StringBuilder();
		Map<String, Point> timeTable = new HashMap<>();
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if(p1.time.am != p2.time.am) {
					if(p1.time.am) {
						return -1; // p1 is am, p2 is pm, p1 first
					} else {
						return 1; // p1 is pm, p2 is am, p2 first
					}
						
				}
				if(p1.time.hour != p2.time.hour) {
					return p1.time.hour % 12 - p2.time.hour % 12; // if(hour == 12)	hour = 0; // for comparison purpose 12:10 am == 00:10 am < 1:10am
 				}
				return p1.time.min - p2.time.min;
			}
		});
		for(String interval : input) {
			String [] times = interval.split(",");
			if(times.length != 2)	continue; // illegal input, skip this line
			try {
				Time start = new Time(times[0]);
				Time end = new Time(times[1]);
				Point startPoint = null;
				if(timeTable.containsKey(start.toString())) {
					startPoint = timeTable.get(start.toString()); // directly change the diff, won't affect the position in priorityQueue
					startPoint.diff ++;
				} else {
					startPoint = new Point(start, 1);
					timeTable.put(start.toString(), startPoint);
					pq.add(startPoint);
				}
				
				Point endPoint = null;
				if(timeTable.containsKey(end.toString())) {
					endPoint = timeTable.get(end.toString());
					endPoint.diff --;
				} else {
					endPoint = new Point(end, -1);
					timeTable.put(end.toString(), endPoint);
					pq.add(endPoint);
				}
			} catch(Exception e) {
				// log and continue
				System.out.println(e.getMessage());
				continue;
			}
		}
		
		if(pq.isEmpty())	return ret.toString();
		Point first = pq.poll();
		int count = first.diff;
		Time pre = first.time;
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			if (cur.diff == 0) {
				continue; // nothing happened. the number increased == the number decrease
			}
			if(count > 0) { // is none of bike is rented, skip the print
				ret.append(pre.toString());
				ret.append(", ");
				ret.append(cur.time.toString());
				ret.append(", ");
				ret.append(count);
				ret.append('\n');
			}
			count += cur.diff;
			pre = cur.time;
		}
		return ret.toString();
	}
	
	public static void main(String[] args) {
		LimeBike b = new LimeBike();
		List<String> input = new ArrayList<String> ();
		input.add("7:13 AM, 7:23 AM");
		input.add("6:50 AM, 7:08 AM");
		input.add("7:10 AM, 7:30 AM");
		input.add("6:52 AM, 7:33 AM");
		input.add("6:58 AM, 7:23 AM");
		input.add("12:13 AM, 6:50 AM");
		input.add("7:13 PM, 7:23 PM");
		input.add("12:13 PM, 8:23 PM");
		// not good support for cross day timestamp, i.e 11:30pm - 12:05am.  need day info to give better support. should not be a requirement here.
		System.out.println(b.timelineSummary(input));
	}
}
