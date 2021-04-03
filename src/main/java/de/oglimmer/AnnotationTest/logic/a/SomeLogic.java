package de.oglimmer.AnnotationTest.logic.a;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * just an example (Guava's EventBus) subscriber for a dto.
 */
@Component
public class SomeLogic {

    public SomeLogic(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void consumeTypeADto(TypeADto dto) {
        System.out.println("got a " + dto);
    }

}
