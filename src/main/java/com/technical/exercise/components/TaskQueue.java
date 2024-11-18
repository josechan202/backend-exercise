package com.technical.exercise.components;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class TaskQueue {
    private final BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    public void produce(Task task) throws InterruptedException {
        queue.put(task);
    }

    public Task consume() throws InterruptedException {
        return queue.take();
    }
}