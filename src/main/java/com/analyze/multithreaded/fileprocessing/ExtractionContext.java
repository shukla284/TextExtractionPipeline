package com.analyze.multithreaded.fileprocessing;

import lombok.Builder;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Builder @Setter
public class ExtractionContext {
    private List<String> fileNames;
    private String directoryName;
    FileExtractionStrategy strategy;
    ExecutorService executorService;
    BlockingQueue<String> blockingQueue;

    public void extract () {

    }
}
