package yermakov.oleksii.tourregistrationservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import yermakov.oleksii.tourregistrationservice.model.Tour;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.mapper.TourMapper;
import yermakov.oleksii.tourregistrationservice.service.repository.TourRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TourServiceImplTest {
    @MockBean
    TourRepository tourRepository;
    @Autowired
    TourMapper tourMapper;
    @Autowired
    TourService tourService;

    private static final List<TourDto> tourDtoList = getExpTourDto();
    private static final List<Tour> tourList = getExpTour();


    @Test
    void getTours() {
        // given
        int pageSize = 10;
        int totalTours = 3;
        given(tourRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(tourList, PageRequest.of(0, pageSize), totalTours));

        // when
        Page<TourDto> result = tourService.getTours(0, pageSize);

        // then
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(tourDtoList);
    }

    @Test
    void saveTour() {
        //given
        Tour tourExp = tourList.get(0);

        UUID randomUUID = UUID.randomUUID();

        Tour tourExpWithId = Tour.builder()
                .id(randomUUID)
                .name(tourExp.getName())
                .description(tourExp.getDescription())
                .price(tourExp.getPrice())
                .startDate(tourExp.getStartDate())
                .endDate(tourExp.getEndDate())
                .createdDate(tourExp.getCreatedDate())
                .build();

        given(tourRepository.save(any(Tour.class))).willReturn(tourExpWithId);

        //when
        TourDto tourDto = tourDtoList.get(0);
        String uri = tourService.saveTour(tourDto);
        //then
        assertThat(uri).isEqualTo(randomUUID.toString());
    }

    @Test
    void deleteTourById() {
        given(tourRepository.findById(any(UUID.class))).willReturn(Optional.of(new Tour()));
        UUID tourId = UUID.randomUUID();
        Boolean b = tourService.deleteTourById(tourId);
        verify(tourRepository).deleteById(tourId);
        assertThat(b).isTrue();
    }
    @Test
    void deleteTourByIdNot(){
        given(tourRepository.findById(any(UUID.class))).willReturn(Optional.empty());
        verify(tourRepository, never()).deleteById(any(UUID.class));
        Boolean b = tourService.deleteTourById(UUID.randomUUID());
        assertThat(b).isFalse();
    }

    @Test
    void getTourById() {
        UUID randomUUID = UUID.randomUUID();
        given(tourRepository.findById(randomUUID)).willReturn(Optional.of(tourList.get(0)));

        Optional<TourDto> tourById = tourService.getTourById(randomUUID);

        assertThat(tourById).isEqualTo(Optional.of(tourDtoList.get(0)));
    }
    @Test
    void getTourByIdNotFound() {
        UUID randomUUID = UUID.randomUUID();
        given(tourRepository.findById(randomUUID)).willReturn(Optional.empty());

        Optional<TourDto> tourById = tourService.getTourById(randomUUID);

        assertThat(tourById).isEmpty();
    }



    public static List<TourDto> getExpTourDto() {
        TourDto tour1 = TourDto.builder()
                .id(UUID.randomUUID())
                .name("Tour to Paris")
                .description("A wonderful tour to Paris")
                .price(BigDecimal.valueOf(1000))
                .startDate(LocalDate.of(2024, 7, 1))
                .endDate(LocalDate.of(2024, 7, 10))
                .build();

        TourDto tour2 = TourDto.builder()
                .id(UUID.randomUUID())
                .name("Tour to Rome")
                .description("An exciting tour to Rome")
                .price(BigDecimal.valueOf(1200))
                .startDate(LocalDate.of(2024, 8, 15))
                .endDate(LocalDate.of(2024, 8, 25))
                .build();

        TourDto tour3 = TourDto.builder()
                .id(UUID.randomUUID())
                .name("Tour to New York")
                .description("An adventurous tour to New York")
                .price(BigDecimal.valueOf(1500))
                .startDate(LocalDate.of(2024, 9, 5))
                .endDate(LocalDate.of(2024, 9, 15))
                .build();

        return Arrays.asList(tour1, tour2, tour3);
    }

    public static List<Tour> getExpTour() {

        return tourDtoList.stream().map(tourDto -> Tour.builder()
                        .id(tourDto.getId())
                        .name(tourDto.getName())
                        .description(tourDto.getDescription())
                        .price(tourDto.getPrice())
                        .startDate(tourDto.getStartDate())
                        .endDate(tourDto.getEndDate())
                        .createdDate(LocalDate.now())
                        .build())
                .collect(Collectors.toList());
    }
}