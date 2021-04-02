package de.oglimmer.AnnotationTest.logic.b;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;


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

