package de.oglimmer.AnnotationTest.logic.c;

import de.oglimmer.AnnotationTest.logic.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Type("peta-action")
public class TypeCDto {

    private String type;
    private String street;
    private int zip;

}
