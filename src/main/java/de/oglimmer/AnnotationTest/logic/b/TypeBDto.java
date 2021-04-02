package de.oglimmer.AnnotationTest.logic.b;

import de.oglimmer.AnnotationTest.logic.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@Type("giga-action")
public class TypeBDto {

    private String type;
    private String age;

}
