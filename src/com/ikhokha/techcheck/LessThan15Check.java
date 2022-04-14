package com.ikhokha.techcheck;

public class LessThan15Check implements Metric {
	private final String info = "SHORTER_THAN_15";
	private final int metricCheckVal = 15;
	
	public boolean passMetric(String comment) {
		return (comment.length() < metricCheckVal);
	}
	
	public String metricInfo() {
		return info;
	}
}
