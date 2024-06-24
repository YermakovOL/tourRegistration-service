package yermakov.oleksii.tourregistrationservice.model.document;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Document(indexName = "tours")
public class TourDocument {
    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    @Field(value = "start_date")
    private LocalDate startDate;
    @Field(value = "end_date")
    private LocalDate endDate;
    @Field(value = "created_date")
    private LocalDate createdDate;
}
