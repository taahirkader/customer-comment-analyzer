package com.ikhokha.techcheck;

public class ShakerCheck implements Metric { 
	private final String info = "SHAKER_MENTIONS";
	private final String metricString = "shaker";
	
	public boolean passMetric(String comment) {
		return comment.toLowerCase().contains(metricString);
	}
	
	public String metricInfo() {
		return info;
	}
}
