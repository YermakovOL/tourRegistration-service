package yermakov.oleksii.tourregistrationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    public static final String TOUR_SEARCH_REQUEST_QUEUE = "tour-search-request";
    public static final String TOUR_SEARCH_RESPONSE_QUEUE = "tour-search-response";
    private static final String TOUR_SEARCH_REQUEST_EXCHANGE = "tour-search-request-exchange";
    private static final String TOUR_SEARCH_RESPONSE_EXCHANGE = "tour-search-response-exchange";

    @Bean
    Queue requestTourSearchQueue(){
        return new Queue(TOUR_SEARCH_REQUEST_QUEUE, false);
    }
    @Bean
    Queue responseTourSearchQueue(){
        return new Queue(TOUR_SEARCH_RESPONSE_QUEUE, false);
    }
    @Bean
    Exchange tourSearchRequestExchange(){
        return new DirectExchange(TOUR_SEARCH_REQUEST_EXCHANGE, false, false);
    }
    @Bean
    Exchange tourSearchResponseExchange(){
        return new DirectExchange(TOUR_SEARCH_RESPONSE_EXCHANGE, false, false);
    }
    @Bean
    Binding tourSearchRequestBinding(Queue requestTourSearchQueue, Exchange tourSearchRequestExchange ){
        return BindingBuilder.bind(requestTourSearchQueue).to(tourSearchRequestExchange).with(TOUR_SEARCH_REQUEST_QUEUE).noargs();
    }
    @Bean
    Binding tourSearchResponseBinding(Queue responseTourSearchQueue, Exchange tourSearchResponseExchange){
        return BindingBuilder.bind(responseTourSearchQueue).to(tourSearchResponseExchange).with(TOUR_SEARCH_RESPONSE_QUEUE).noargs();
    }
}
