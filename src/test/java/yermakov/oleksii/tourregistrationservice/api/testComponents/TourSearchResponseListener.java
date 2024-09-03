package yermakov.oleksii.tourregistrationservice.api.testComponents;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import yermakov.oleksii.tourregistrationservice.config.RabbitMqConfiguration;

@Slf4j
@Component
public class TourSearchResponseListener {
    @RabbitListener(queues = RabbitMqConfiguration.TOUR_SEARCH_RESPONSE_QUEUE)
    public void listenTourResearchResponse(Message searchResult) {
        
    }
}

