package de.dmichel90.ms_with_kafka.init.impl;


import de.dmichel90.ms_with_kafka.config.KafkaConfigData;
import de.dmichel90.ms_with_kafka.init.StreamInitializer;
import de.dmichel90.ms_with_kafka.kafka.admin.client.KafkaAdminClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaStreamInitializer implements StreamInitializer {
    private final KafkaConfigData kafkaConfigData;

    private final KafkaAdminClient kafkaAdminClient;

    public KafkaStreamInitializer(KafkaConfigData configData, KafkaAdminClient adminClient) {
        this.kafkaConfigData = configData;
        this.kafkaAdminClient = adminClient;
    }

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        log.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
