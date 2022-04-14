package com.ikhokha.techcheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamCheck implements Metric {
	private final String info = "SPAM";
	private final String metricString = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
	
	public boolean passMetric(String comment) {
		Pattern pattern = Pattern.compile(metricString);
		Matcher matcher = pattern.matcher(comment);
		return matcher.find();
	}
	
	public String metricInfo() {
		return info;
	}
}
