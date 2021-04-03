package de.oglimmer.AnnotationTest.logic.c;

import de.oglimmer.AnnotationTest.logic.JsonPathFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This DTO should be uses for all JSON evaluating the JsonPath type!='mega-action'
 */
@Setter
@Getter
@ToString
@JsonPathFilter(".[?(@.type!='mega-action')]")
public class TypeCDto {

    private String type;
    private String street;
    private int zip;

}
