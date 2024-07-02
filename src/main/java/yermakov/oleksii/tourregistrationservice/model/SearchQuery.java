package yermakov.oleksii.tourregistrationservice.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SearchQuery {

    @Size(min = 1, message = "Query must not be empty")
    private String query;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Start date must be in the format yyyy-MM-dd")
    private String minStartDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "End date must be in the format yyyy-MM-dd")
    private String maxStartDate;

    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum price must be positive")
    private String minPrice;

    @DecimalMax(value = "10000.0", inclusive = true, message = "Maximum price must be less than 10000")
    private String maxPrice;

    public SearchQuery(String query, String minStartDate, String maxStartDate, String minPrice, String maxPrice) {
        this.query = query;
        this.minStartDate = minStartDate;
        this.maxStartDate = maxStartDate;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    @AssertTrue(message = "start date is later than end date")
    public boolean isValidDateRange() {
        if (minStartDate == null || maxStartDate == null) {
            return true;
        }

        LocalDate parsedStartDate = LocalDate.parse(minStartDate);
        LocalDate parsedEndDate = LocalDate.parse(maxStartDate);

        return parsedStartDate.isBefore(parsedEndDate);
    }
    @AssertTrue(message = "min price is bigger than max price")
    public boolean isValidPriceRange() {
        if (minPrice == null || maxPrice == null) {
            return true;
        }

        int min = Integer.parseInt(minPrice);
        int max = Integer.parseInt(maxPrice);

        return min < max;
    }
}
