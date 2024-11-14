package com.pelicula.utils;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.time.Instant;
import java.time.format.DateTimeParseException;

public class DateScalar {

    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .description("Custom Date scalar type")
            .coercing(new Coercing<Instant, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    return ((Instant) dataFetcherResult).toString();
                }

                @Override
                public Instant parseValue(Object input) {
                    try {
                        return Instant.parse(input.toString());
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Invalid date format");
                    }
                }

                @Override
                public Instant parseLiteral(Object input) {
                    try {
                        return Instant.parse(input.toString());
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Invalid date format");
                    }
                }
            })
            .build();
}