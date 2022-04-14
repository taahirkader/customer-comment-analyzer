package com.ikhokha.techcheck;

public interface Metric {
	boolean passMetric(String comment);
	String metricInfo();
}
