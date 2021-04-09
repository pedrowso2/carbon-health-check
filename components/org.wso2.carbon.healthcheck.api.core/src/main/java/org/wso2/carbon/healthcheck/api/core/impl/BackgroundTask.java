package org.wso2.carbon.healthcheck.api.core.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BackgroundTask implements Runnable {

	private static final Log log = LogFactory.getLog(BackgroundTask.class);
	
	public void startTaskToThrowsOOMError() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		int dummyArraySize = 15;
		System.out.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
		long memoryConsumed = 0;
		try {
			long[] memoryAllocated = null;
			for (int loop = 0; loop < Integer.MAX_VALUE; loop++) {
				memoryAllocated = new long[dummyArraySize];
				memoryAllocated[0] = 0;
				memoryConsumed += dummyArraySize * Long.SIZE;
				log.info("Memory Consumed till now: " + memoryConsumed);
				dummyArraySize *= dummyArraySize * 2;
				Thread.sleep(500);
			}
		} catch (OutOfMemoryError e) {
			log.info("Catching out of memory error");
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
