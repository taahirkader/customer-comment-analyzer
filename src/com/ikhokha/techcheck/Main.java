package com.ikhokha.techcheck;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new ConcurrentHashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
	     
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		
		for (File commentFile : commentFiles) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					CommentAnalyzer commentAnalyzer = new CommentAnalyzer(commentFile);
					commentAnalyzer.AddMetric(new LessThan15Check());
					commentAnalyzer.AddMetric(new MoverCheck());
					commentAnalyzer.AddMetric(new ShakerCheck());
					commentAnalyzer.AddMetric(new QuestionCheck());
					commentAnalyzer.AddMetric(new SpamCheck());
					Map<String, Integer> fileResults = commentAnalyzer.analyze();
					
					addReportResults(fileResults, totalResults);
				}
			};
			executor.execute(runnable);
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException err) {
			System.out.println("Executor timeout " + err.getMessage());
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static synchronized void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
			target.put(entry.getKey(), target.getOrDefault(entry.getKey(), 0) + entry.getValue());
		}
		
		System.out.println();
	}

}
