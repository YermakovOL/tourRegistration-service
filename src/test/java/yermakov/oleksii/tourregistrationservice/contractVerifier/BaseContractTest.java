package yermakov.oleksii.tourregistrationservice.contractVerifier;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import yermakov.oleksii.tourregistrationservice.api.TourController;
import yermakov.oleksii.tourregistrationservice.service.TourService;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class BaseContractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    TourService tourService;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    TourController tourController;

    public static final String TOUR_ID_EXP = "123e4567-e89b-12d3-a456-426614174000";

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    ).withReuse(true);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.flyway.user", postgres::getUsername);
        registry.add("spring.flyway.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        entityManager.createQuery("DELETE FROM Tour ").executeUpdate();
        entityManager.createNativeQuery("""
                    INSERT INTO tour (id, name, description, price, start_date, end_date, created_date)
                    VALUES (
                        CAST(?1 AS uuid), -- explicit cast to UUID
                        'Sample Tour',
                        'This is a sample tour description',
                        100.00,
                        '2023-06-01',
                        '2023-06-10',
                        CURRENT_TIMESTAMP
                    );
                """).setParameter(1, TOUR_ID_EXP).executeUpdate();
    }
}