package yermakov.oleksii.tourregistrationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Tour
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T18:29:47.794572300+03:00[Europe/Kiev]", comments = "Generator version: 7.5.0")
public class Tour {

  private UUID id;

  private String name;

  private String description;

  private BigDecimal price;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  public Tour() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Tour(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public Tour id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Tour ID
   * @return id
  */
  @Valid 
  @Schema(name = "id", description = "Tour ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Tour name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Tour name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Tour name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tour description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Tour description
   * @return description
  */
  
  @Schema(name = "description", description = "Tour description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Tour price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Tour price
   * @return price
  */
  @NotNull @Valid 
  @Schema(name = "price", description = "Tour price", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Tour startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Tour start date
   * @return startDate
  */
  @Valid 
  @Schema(name = "startDate", description = "Tour start date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startDate")
  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public Tour endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Tour end date
   * @return endDate
  */
  @Valid 
  @Schema(name = "endDate", description = "Tour end date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endDate")
  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tour tour = (Tour) o;
    return Objects.equals(this.id, tour.id) &&
        Objects.equals(this.name, tour.name) &&
        Objects.equals(this.description, tour.description) &&
        Objects.equals(this.price, tour.price) &&
        Objects.equals(this.startDate, tour.startDate) &&
        Objects.equals(this.endDate, tour.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price, startDate, endDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tour {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

