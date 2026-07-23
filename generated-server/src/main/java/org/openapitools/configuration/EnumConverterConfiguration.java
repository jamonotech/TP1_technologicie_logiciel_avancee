package org.openapitools.configuration;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import com.alumni.userservice.model.Gender;
import com.alumni.userservice.model.UserStatus;
import com.alumni.userservice.model.UserType;

import jakarta.annotation.Generated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * This class provides Spring Converter beans for the enum models in the OpenAPI specification.
 *
 * By default, Spring only converts primitive types to enums using Enum::valueOf, which can prevent
 * correct conversion if the OpenAPI specification is using an `enumPropertyNaming` other than
 * `original` or the specification has an integer enum.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-23T09:01:43.370133340Z[Africa/Dakar]", comments = "Generator version: 7.24.0")
@Configuration(value = "org.openapitools.configuration.enumConverterConfiguration")
public class EnumConverterConfiguration {

    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.genderConverter")
    Converter<String, Gender> genderConverter() {
        return new Converter<String, Gender>() {
            @Override
            public Gender convert(String source) {
                return Gender.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.userStatusConverter")
    Converter<String, UserStatus> userStatusConverter() {
        return new Converter<String, UserStatus>() {
            @Override
            public UserStatus convert(String source) {
                return UserStatus.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.userTypeConverter")
    Converter<String, UserType> userTypeConverter() {
        return new Converter<String, UserType>() {
            @Override
            public UserType convert(String source) {
                return UserType.fromValue(source);
            }
        };
    }

}
