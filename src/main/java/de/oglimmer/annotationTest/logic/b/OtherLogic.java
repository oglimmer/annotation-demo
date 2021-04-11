package de.oglimmer.annotationTest.logic.b;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * just an example (Guava's EventBus) subscriber for a dto.
 */
@Component
public class OtherLogic {

    public OtherLogic(EventBus eventBus) {
        eventBus.register(this);
    }


    @Subscribe
    public void consumeTypeBDto(TypeBDto dto) {
        System.out.println("got a " + dto);
    }


}

