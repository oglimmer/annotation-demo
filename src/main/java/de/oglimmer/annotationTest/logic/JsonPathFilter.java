package de.oglimmer.annotationTest.logic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a JsonPath for a DTO. The idea is: when this JsonPath evaluates into a non-empty object for a given
 * JSON string we will parse this JSON into this DTO.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonPathFilter {
    String value();
}
