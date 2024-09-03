package yermakov.oleksii.tourregistrationservice.api.listener;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import yermakov.oleksii.tourregistrationservice.config.RabbitMqConfiguration;
import yermakov.oleksii.tourregistrationservice.model.SearchQuery;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.TourSearchService;
import yermakov.oleksii.tourregistrationservice.service.mapper.SearchQueryMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
@Profile("rabbitmq")
@SpringBootTest
class TourSearchListenersTest {
    public static final String CORRELATION_ID = "550e8400-e29b-41d4-a716-446655440000";
    @MockBean
    TourSearchService searchService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    SearchQueryMapper searchMapper;

    @Test
    void listenTourResearchRequest() {
        given(searchService.search(any(SearchQuery.class))).willReturn(createSampleTours());
        SearchQuery tourSearchQuery = SearchQuery.builder()
                .query("tourSearch")
                .build();
        Message searchQueryMessage = MessageBuilder
                .withBody(searchMapper.convertQuery(tourSearchQuery))
                .setHeader("correlationId", CORRELATION_ID)
                .build();
        rabbitTemplate.send(RabbitMqConfiguration.TOUR_SEARCH_REQUEST_QUEUE, searchQueryMessage);
    }

    @TestConfiguration
    static class TestConfigurationClass {
        @Autowired
        SearchQueryMapper searchMapper;

        @RabbitListener(queues = RabbitMqConfiguration.TOUR_SEARCH_RESPONSE_QUEUE)
        public void listenTourResearchResponse(Message searchResult) {
            assertThat(searchResult.getBody()).isEqualTo(searchMapper.convertQueryResult(createSampleTours()));
            assertThat((String) searchResult.getMessageProperties().getHeader("correlationId")).isEqualTo(CORRELATION_ID);
        }
    }
    public static List<TourDto> createSampleTours() {
        return List.of(
                TourDto.builder()
                        .id(UUID.randomUUID())
                        .name("Tour 1")
                        .description("Description for Tour 1")
                        .price(BigDecimal.valueOf(199.99))
                        .startDate(LocalDate.of(2024, 1, 10))
                        .endDate(LocalDate.of(2024, 1, 20))
                        .build(),
                TourDto.builder()
                        .id(UUID.randomUUID())
                        .name("Tour 2")
                        .description("Description for Tour 2")
                        .price(BigDecimal.valueOf(299.99))
                        .startDate(LocalDate.of(2024, 2, 10))
                        .endDate(LocalDate.of(2024, 2, 20))
                        .build(),
                TourDto.builder()
                        .id(UUID.randomUUID())
                        .name("Tour 3")
                        .description("Description for Tour 3")
                        .price(BigDecimal.valueOf(399.99))
                        .startDate(LocalDate.of(2024, 3, 10))
                        .endDate(LocalDate.of(2024, 3, 20))
                        .build()
        );
    }
}