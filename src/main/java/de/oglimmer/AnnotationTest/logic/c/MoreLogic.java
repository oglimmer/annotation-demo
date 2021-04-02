package de.oglimmer.AnnotationTest.logic.c;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;


@Component
public class MoreLogic {

    public MoreLogic(EventBus eventBus) {
        eventBus.register(this);
    }


    @Subscribe
    public void consumeTypeCDto(TypeCDto dto) {
        System.out.println("got a " + dto);
    }


}

