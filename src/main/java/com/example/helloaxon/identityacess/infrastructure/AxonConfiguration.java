package com.example.helloaxon.identityacess.infrastructure;

import com.mongodb.client.MongoCollection;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.FixedValueRevisionResolver;
import org.axonframework.serialization.RevisionResolver;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@Configuration
public class AxonConfiguration {

    @Bean
    public RevisionResolver revisionResolver(@Value("${event.revision:1.0}") String revision) {
        return new FixedValueRevisionResolver(revision);
    }

    @Bean
    public EventStorageEngine eventStorageEngine(
            Serializer serializer,
            MongoTemplate axonMongoTemplate
    ) {
        return new MongoEventStorageEngine(
                serializer,
                new EventUpcasterChain(),
                axonMongoTemplate,
                new DocumentPerEventStorageStrategy());
    }

    @Bean(name = "axonMongoTemplate")
    public MongoTemplate axonMongoTemplate(org.springframework.data.mongodb.core.MongoTemplate mongoTemplate) {
        return new MongoTemplate() {
            @Override
            public MongoCollection<Document> trackingTokensCollection() {
                return mongoTemplate.getCollection("trackingTokens");
            }

            @Override
            public MongoCollection<Document> eventCollection() {
                return mongoTemplate.getCollection("domainEvents");
            }

            @Override
            public MongoCollection<Document> snapshotCollection() {
                return mongoTemplate.getCollection("snapshotEvents");
            }

            @Override
            public MongoCollection<Document> sagaCollection() {
                return mongoTemplate.getCollection("sagas");
            }
        };
    }

}
