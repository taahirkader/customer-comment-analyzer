package com.ikhokha.techcheck;

public class MoverCheck implements Metric {
	private final String info = "MOVER_MENTIONS";
	private final String metricString = "mover";
	
	public boolean passMetric(String comment) {
		return comment.toLowerCase().contains(metricString);
	}
	
	public String metricInfo() {
		return info;
	}
}
