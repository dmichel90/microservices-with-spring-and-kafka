package de.dmichel90.ms_with_kafka;

import de.dmichel90.ms_with_kafka.init.StreamInitializer;
import de.dmichel90.ms_with_kafka.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DocumentsToKafkaServiceApplication implements CommandLineRunner {

    private final StreamInitializer streamInitializer;
    private final StreamRunner streamRunner;

    public DocumentsToKafkaServiceApplication(StreamInitializer streamInitializer, StreamRunner streamRunner) {
        this.streamInitializer = streamInitializer;
        this.streamRunner = streamRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(DocumentsToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App starts...");
        streamInitializer.init();
        streamRunner.start();
    }
}