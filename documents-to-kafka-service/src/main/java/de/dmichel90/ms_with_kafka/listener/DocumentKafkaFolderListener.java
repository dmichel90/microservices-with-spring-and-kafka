package de.dmichel90.ms_with_kafka.listener;

import de.dmichel90.ms_with_kafka.config.KafkaConfigData;
import de.dmichel90.ms_with_kafka.kafka.avro.model.DocumentAvroModel;
import de.dmichel90.ms_with_kafka.kafka.producer.config.service.KafkaProducer;
import de.dmichel90.ms_with_kafka.model.Document;
import de.dmichel90.ms_with_kafka.transformer.DocumentsToAvroTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentKafkaFolderListener {

    private final KafkaConfigData kafkaConfigData;

    private final KafkaProducer<Long, DocumentAvroModel> kafkaProducer;

    private final DocumentsToAvroTransformer documentsToAvroTransformer;

    public DocumentKafkaFolderListener(KafkaConfigData kafkaConfigData, KafkaProducer<Long, DocumentAvroModel> kafkaProducer, DocumentsToAvroTransformer documentsToAvroTransformer) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaProducer = kafkaProducer;
        this.documentsToAvroTransformer = documentsToAvroTransformer;
    }

    public void createRandomDocument(Document document) {
        log.info("Received document {} sending to kafka topic {}", document.getName(), kafkaConfigData.getTopicName());
        DocumentAvroModel documentAvroModel = documentsToAvroTransformer.getDocumentsAvroModelFromStatus(document);
        kafkaProducer.send(kafkaConfigData.getTopicName(), Long.valueOf(document.getId()), documentAvroModel);
    }
}
