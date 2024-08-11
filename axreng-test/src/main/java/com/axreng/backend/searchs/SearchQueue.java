package com.axreng.backend.searchs;

import com.google.gson.JsonObject;

import java.util.PriorityQueue;

public class SearchQueue {

    private static volatile SearchQueue instance;
    private final PriorityQueue<JsonObject> queue;

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

    public synchronized void put(JsonObject element) {
        queue.add(element);
    }

    public synchronized PriorityQueue<JsonObject> queueOfSearchs() {
        return queue;}
}