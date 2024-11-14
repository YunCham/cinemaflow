package com.pelicula.config;

import com.pelicula.utils.DateScalar;
import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Configuration
public class GraphQLConfig {
//    @Bean
//    public GraphQLScalarType localDateTimeScalar() {
//        return GraphQLScalarType.newScalar()
//                .name("LocalDateTime")
//                .description("Java LocalDateTime scalar")
//                .coercing(new Coercing<LocalDateTime, String>() {
//                    @Override
//                    public String serialize(Object dataFetcherResult) {
//                        if (dataFetcherResult instanceof LocalDateTime) {
//                            return ((LocalDateTime) dataFetcherResult).toString();
//                        }
//                        throw new CoercingSerializeException("Expected a LocalDateTime object.");
//                    }
//
//                    @Override
//                    public LocalDateTime parseValue(Object input) {
//                        try {
//                            if (input instanceof String) {
//                                return LocalDateTime.parse((String) input);
//                            }
//                            throw new CoercingParseValueException("Expected a String");
//                        } catch (DateTimeParseException e) {
//                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input));
//                        }
//                    }
//
//                    @Override
//                    public LocalDateTime parseLiteral(Object input) {
//                        if (input instanceof StringValue) {
//                            try {
//                                return LocalDateTime.parse(((StringValue) input).getValue());
//                            } catch (DateTimeParseException e) {
//                                throw new CoercingParseLiteralException(e);
//                            }
//                        }
//                        throw new CoercingParseLiteralException("Expected a StringValue.");
//                    }
//                }).build();
//    }
}