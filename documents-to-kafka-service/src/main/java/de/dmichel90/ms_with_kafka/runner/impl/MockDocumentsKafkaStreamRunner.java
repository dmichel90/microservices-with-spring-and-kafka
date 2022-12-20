package de.dmichel90.ms_with_kafka.runner.impl;


import de.dmichel90.ms_with_kafka.config.DocumentsToKafkaServiceConfigData;
import de.dmichel90.ms_with_kafka.exception.DocumentsToKafkaServiceException;
import de.dmichel90.ms_with_kafka.listener.DocumentKafkaFolderListener;
import de.dmichel90.ms_with_kafka.model.Document;
import de.dmichel90.ms_with_kafka.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MockDocumentsKafkaStreamRunner implements StreamRunner {
    private final DocumentKafkaFolderListener documentKafkaFolderListener;
    private final DocumentsToKafkaServiceConfigData documentsToKafkaServiceConfigData;

    public MockDocumentsKafkaStreamRunner(DocumentKafkaFolderListener documentKafkaFolderListener, DocumentsToKafkaServiceConfigData documentsToKafkaServiceConfigData) {
        this.documentKafkaFolderListener = documentKafkaFolderListener;
        this.documentsToKafkaServiceConfigData = documentsToKafkaServiceConfigData;
    }

    @Override
    public void start() {
        final String[] folders = documentsToKafkaServiceConfigData.getFolders().toArray(new String[0]);
        long sleepTimeMs = documentsToKafkaServiceConfigData.getMockSleepMs();
        log.info("Starting mock filtering folders streams for keywords {}", Arrays.toString(folders));
        simulateDocumentStream(sleepTimeMs);
    }

    private void simulateDocumentStream(long sleepTimeMs) {
        AtomicInteger counter = new AtomicInteger(1);
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                while (true) {
                    var document = createMockDocument(counter.get());
                    documentKafkaFolderListener.createRandomDocument(document);
                    counter.getAndIncrement();
                    var counterValue = counter.get();
                    if (counterValue > 3) {
                        counter.set(1);
                    }
                    sleep(sleepTimeMs);
                }
            } catch (Exception e) {
                log.error("Error creating document!", e);
            }
        });
    }

    private Document createMockDocument(int counter) {
        var document = new Document();
        document.setCreatedAt(new Date());
        document.setId(String.valueOf(counter));
        document.setMimeType("text/csv");
        document.setPath("path");
        document.setName("Document" + new Date());
        return document;
    }

    private void sleep(long sleepTimeMs) {
        try {
            Thread.sleep(sleepTimeMs);
        } catch (InterruptedException e) {
            throw new DocumentsToKafkaServiceException("Error while sleeping for waiting new document to create!");
        }
    }

}
