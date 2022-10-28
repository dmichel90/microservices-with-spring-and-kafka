package de.dmichel90.ms_with_kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "documents-to-kafka-service")
public class DocumentsToKafkaServiceConfigData {
    private List<String> folders;
    private Long mockSleepMs;
}
