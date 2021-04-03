package de.oglimmer.AnnotationTest.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.eventbus.EventBus;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import de.oglimmer.AnnotationTest.configuration.ClassFinder;
import de.oglimmer.AnnotationTest.logic.JsonPathFilter;
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
    private Configuration configuration;

    private final EventBus eventBus;
    private final ClassFinder classFinder;

    {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configuration = Configuration
                .builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonNodeJsonProvider(om))
                .build();
    }

    /**
     * REST endpoint which takes different json schema.
     */
    @PostMapping(value = "/intake", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody JsonNode json) throws IOException {
        Class<?> clazz = inferDtoType(json);
        Object obj = objectMapper.treeToValue(json, clazz);
        eventBus.post(obj);
    }

    /**
     * Find the right target DTO for this json (using the annotation JsonPathFilter)
     */
    private Class<?> inferDtoType(JsonNode json) {
        return classFinder.getDtoClasses().stream()
                .filter(e -> e.getAnnotation(JsonPathFilter.class) != null)
                .filter(e -> isJsonValidForFilter(json, e.getAnnotation(JsonPathFilter.class).value()))
                .findFirst()
                .orElseThrow();
    }

    /**
     * We use the JsonPath library to check the filter against json
     */
    private boolean isJsonValidForFilter(JsonNode json, String filter) {
        JsonNode node = JsonPath.using(configuration).parse(json).read(filter);
        return !node.isEmpty();
    }

}
