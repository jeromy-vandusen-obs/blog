package com.example.blog.domain.configuration;

import com.example.blog.domain.configuration.conversion.ZonedDateTimeReadConverter;
import com.example.blog.domain.configuration.conversion.ZonedDateTimeWriteConverter;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class BlogDatabaseConfiguration extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient();
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public CustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new ZonedDateTimeReadConverter(), new ZonedDateTimeWriteConverter()));
    }
}
