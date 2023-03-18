package com.analyze.multithreaded.fileprocessing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;


public class ExtractorTask implements Runnable {

    String fileDirectory;
    String fileName;
    BlockingQueue<String> blockingQueue;

    public ExtractorTask(String fileDirectory, String fileName,
                         BlockingQueue<String> blockingQueue) {
        this.fileDirectory =fileDirectory;
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        var textList = FileProcessingUtils.readFileLocation(fileDirectory, fileName);
        var stringList = new AtomicReference<>(new ArrayList<String>());
        if (textList != null) {
            textList.forEach(i -> {
                stringList.get().addAll(List.of(i.split(" ")));
            });
        }
        stringList.get().forEach(i -> {
            try { this.blockingQueue.put(i); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });
    }
}
