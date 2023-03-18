package com.analyze.multithreaded.fileprocessing;

import lombok.*;

import java.io.File;
import java.util.Arrays;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Setter @Builder
public class ExtractorProcessor {
    ExtractionContext context;

    @Getter @Setter @Builder @AllArgsConstructor
    public static class DirectoryStrategy implements FileExtractionStrategy {
        BlockingQueue<String> blockingQueue;
        ExecutorService executorService;
        String directoryName;
        @Override
        public void execute() {
            var directoryPath = new File(directoryName);
            if (FileProcessingUtils.isValidDirectory(directoryPath)) {
                var fileList = directoryPath.list();
                if (fileList != null)
                    Arrays.stream(fileList)
                            .forEach(i -> executorService.submit(
                                    new ExtractorTask(directoryName, i, blockingQueue)
                            ));
            }
        }
    }
    public static class ListStrategy implements FileExtractionStrategy {
        BlockingQueue<String> blockingQueue;
        ExecutorService executorService;
        List<String> fileList;

        @Override
        public void execute() {
            fileList.forEach(i -> executorService.submit(
                            new ExtractorTask(null, i, blockingQueue)
                    ));
        }
    }
}
