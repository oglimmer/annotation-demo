package de.oglimmer.AnnotationTest.logic.a;

import de.oglimmer.AnnotationTest.logic.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Type("mega-action")
public class TypeADto {

    private String type;
    private String name;

}
