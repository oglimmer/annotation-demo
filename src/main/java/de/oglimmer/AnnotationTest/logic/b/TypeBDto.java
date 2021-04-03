package de.oglimmer.AnnotationTest.logic.b;

import de.oglimmer.AnnotationTest.logic.JsonPathFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * This DTO should be uses for all JSON evaluating the JsonPath `attribute age is available`.
 */
@Setter
@Getter
@ToString
@JsonPathFilter(".[?(@.age)]")
public class TypeBDto {

    private String age;

}
