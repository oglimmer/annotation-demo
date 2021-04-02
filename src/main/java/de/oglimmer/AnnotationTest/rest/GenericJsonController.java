package de.oglimmer.AnnotationTest.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import de.oglimmer.AnnotationTest.configuration.ClassFinder;
import de.oglimmer.AnnotationTest.logic.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GenericJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private final EventBus eventBus;
    private final ClassFinder classFinder;

    @PostMapping(value = "/intake", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody JsonNode json) throws IOException {
        Class<?> clazz = inferDtoType(json);
        Object obj = objectMapper.treeToValue(json, clazz);
        eventBus.post(obj);
    }

    private Class<?> inferDtoType(JsonNode json) {
        String type = json.get("type").asText();
        return classFinder.getDtoClasses().stream()
                .filter(e -> e.getAnnotation(Type.class) != null && type.equals(e.getAnnotation(Type.class).value()))
                .findFirst()
                .orElseThrow();
    }

}
