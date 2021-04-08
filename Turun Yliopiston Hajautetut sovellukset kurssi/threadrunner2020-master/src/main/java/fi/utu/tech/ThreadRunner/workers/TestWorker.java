package fi.utu.tech.ThreadRunner.workers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWorker implements Worker {

    private static TestWorker instance = null;
    Map<Long, Integer> threadIDs = new HashMap<>();
    List<Integer> results = Collections.synchronizedList(new ArrayList<>());

	public void count(Integer data) throws Exception {
        long threadId = Thread.currentThread().getId();
        int usageCount;
        synchronized(this.threadIDs) {
            usageCount = this.threadIDs.get(threadId) != null ? this.threadIDs.get(threadId) : 0;
            this.threadIDs.put(Thread.currentThread().getId(), usageCount+1);
        }
        results.add(data);
    }

    public static void reset() {
        instance = null;
    }

    public List<Integer> getResults() {
        return this.results;
    }

    public int getThreadCount() {
        synchronized(this.threadIDs) {
            return this.threadIDs.size();
        }
    }

    public List<Integer> getThreadUsage() {
        synchronized(this.threadIDs) {
            return new ArrayList<Integer>(this.threadIDs.values());
        }
    }
    
    /**
     * Get instance of TestWorker. Singleton because we do not know whether the student creates workers for every thread or not
     * @return Instance of singleton worker
     */
    public static Worker getTestWorker() {
        if (instance == null) instance = new TestWorker();
        return instance;
    }
}
