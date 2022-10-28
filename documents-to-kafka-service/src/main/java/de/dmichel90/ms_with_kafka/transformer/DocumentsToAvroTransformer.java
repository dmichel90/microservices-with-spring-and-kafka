package de.dmichel90.ms_with_kafka.transformer;

import de.dmichel90.ms_with_kafka.kafka.avro.model.DocumentAvroModel;
import de.dmichel90.ms_with_kafka.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentsToAvroTransformer {

    public DocumentAvroModel getDocumentsAvroModelFromStatus(Document document) {
        return DocumentAvroModel
                .newBuilder()
                .setId(document.getId())
                .setName(document.getName())
                .setMimeType(document.getMimeType())
                .setPath(document.getPath())
                .setCreatedAt(document.getCreatedAt().getTime())
                .build();
    }
}
