package com.ikhokha.techcheck;

public class QuestionCheck implements Metric {
	private final String info = "QUESTIONS";
	private final String metricString = "?";
	
	public boolean passMetric(String comment) {
		return comment.toLowerCase().contains(metricString);
	}
	
	public String metricInfo() {
		return info;
	}
}
