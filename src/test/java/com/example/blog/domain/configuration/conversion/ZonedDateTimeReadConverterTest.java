package com.example.blog.domain.configuration.conversion;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ZonedDateTimeReadConverterTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void convert_whenDateIsNull_throwsNullPointerException() {
        exception.expect(NullPointerException.class);
        new ZonedDateTimeReadConverter().convert(null);
    }

    @Test
    public void convert_whenDateIsValid_returnsDateConvertedToZonedDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        Date date = Date.from(zonedDateTime.toInstant());

        ZonedDateTime result = new ZonedDateTimeReadConverter().convert(date);

        assertThat(result).isNotNull().isEqualTo(zonedDateTime);
    }
}
