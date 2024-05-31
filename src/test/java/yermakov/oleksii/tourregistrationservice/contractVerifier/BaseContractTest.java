package yermakov.oleksii.tourregistrationservice.contractVerifier;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class BaseContractTest {
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    public void setUp(){
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
}
