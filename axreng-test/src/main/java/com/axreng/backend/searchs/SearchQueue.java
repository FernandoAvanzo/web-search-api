package com.axreng.backend.searchs;

import java.util.PriorityQueue;

public class SearchQueue {

    private static volatile SearchQueue instance;
    private final PriorityQueue<String> queue;

    private SearchQueue() {
        queue = new PriorityQueue<>();
    }

    public static SearchQueue getInstance() {
        if (instance == null) {
            synchronized (SearchQueue.class) {
                if (instance == null) {
                    instance = new SearchQueue();
                }
            }
        }
        return instance;
    }

    public synchronized void put(String element) {
        queue.add(element);
    }

    public synchronized PriorityQueue<String> queueOfSearchs() {
        return queue;}
}
