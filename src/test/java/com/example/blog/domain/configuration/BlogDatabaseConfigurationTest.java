package com.example.blog.domain.configuration;

import org.junit.Test;
import org.springframework.data.convert.CustomConversions;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogDatabaseConfigurationTest {
    @Test
    public void customConversions_always_containsAllExpectedConverters() {
        BlogDatabaseConfiguration blogDatabaseConfiguration = new BlogDatabaseConfiguration();

        CustomConversions result = blogDatabaseConfiguration.customConversions();

        assertThat(result.hasCustomReadTarget(Date.class, ZonedDateTime.class)).isTrue();
        assertThat(result.hasCustomWriteTarget(ZonedDateTime.class)).isTrue();
    }
}
