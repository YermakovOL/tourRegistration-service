/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package yermakov.oleksii.tourregistrationservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import yermakov.oleksii.tourregistrationservice.model.Tour;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T18:29:47.794572300+03:00[Europe/Kiev]", comments = "Generator version: 7.5.0")
@Validated
@Tag(name = "tourCrud", description = "the tourCrud API")
public interface TourCrudApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /tourCrud/{tourId} : Delete tour
     * Deletes a tour by its ID.
     *
     * @param tourId Tour ID (required)
     * @return Tour successfully deleted (status code 204)
     *         or Tour not found (status code 404)
     */
    @Operation(
        operationId = "deleteTourById",
        summary = "Delete tour",
        description = "Deletes a tour by its ID.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Tour successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/tourCrud/{tourId}"
    )
    
    default ResponseEntity<Void> deleteTourById(
        @Parameter(name = "tourId", description = "Tour ID", required = true, in = ParameterIn.PATH) @PathVariable("tourId") UUID tourId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /tourCrud/{tourId} : Get tour by ID
     * Returns information about a tour by its ID.
     *
     * @param tourId Tour ID (required)
     * @return Successful response with tour data (status code 200)
     *         or Tour not found (status code 404)
     */
    @Operation(
        operationId = "getTourById",
        summary = "Get tour by ID",
        description = "Returns information about a tour by its ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response with tour data", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Tour.class))
            }),
            @ApiResponse(responseCode = "404", description = "Tour not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/tourCrud/{tourId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Tour> getTourById(
        @Parameter(name = "tourId", description = "Tour ID", required = true, in = ParameterIn.PATH) @PathVariable("tourId") UUID tourId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"endDate\" : \"2000-01-23\", \"price\" : 0.8008281904610115, \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startDate\" : \"2000-01-23\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /tourCrud : Get list of tours
     * Returns a list of all tours with pagination support.
     *
     * @param page Page number (starting from 0) (optional, default to 0)
     * @param size Page size (optional, default to 10)
     * @return Successful response with an array of tours (status code 200)
     */
    @Operation(
        operationId = "getTours",
        summary = "Get list of tours",
        description = "Returns a list of all tours with pagination support.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response with an array of tours", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Tour.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/tourCrud",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Tour>> getTours(
        @Parameter(name = "page", description = "Page number (starting from 0)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @Parameter(name = "size", description = "Page size", in = ParameterIn.QUERY) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"endDate\" : \"2000-01-23\", \"price\" : 0.8008281904610115, \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startDate\" : \"2000-01-23\" }, { \"endDate\" : \"2000-01-23\", \"price\" : 0.8008281904610115, \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startDate\" : \"2000-01-23\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /tourCrud/{tourId} : Partially update tour
     * Partially updates tour information by its ID.
     *
     * @param tourId Tour ID (required)
     * @param tour Partially updated tour data (required)
     * @return Tour successfully partially updated (status code 200)
     *         or Tour not found (status code 404)
     */
    @Operation(
        operationId = "patchTourById",
        summary = "Partially update tour",
        description = "Partially updates tour information by its ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Tour successfully partially updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Tour.class))
            }),
            @ApiResponse(responseCode = "404", description = "Tour not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/tourCrud/{tourId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Tour> patchTourById(
        @Parameter(name = "tourId", description = "Tour ID", required = true, in = ParameterIn.PATH) @PathVariable("tourId") UUID tourId,
        @Parameter(name = "Tour", description = "Partially updated tour data", required = true) @Valid @RequestBody Tour tour
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"endDate\" : \"2000-01-23\", \"price\" : 0.8008281904610115, \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startDate\" : \"2000-01-23\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /tourCrud : Create a new tour
     * Creates a new tour based on the provided data.
     *
     * @param tour New tour data (required)
     * @return Tour successfully created (status code 201)
     */
    @Operation(
        operationId = "postTour",
        summary = "Create a new tour",
        description = "Creates a new tour based on the provided data.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Tour successfully created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Tour.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/tourCrud",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Tour> postTour(
        @Parameter(name = "Tour", description = "New tour data", required = true) @Valid @RequestBody Tour tour
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"endDate\" : \"2000-01-23\", \"price\" : 0.8008281904610115, \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startDate\" : \"2000-01-23\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /tourCrud/{tourId} : Update tour
     * Updates tour information by its ID.
     *
     * @param tourId Tour ID (required)
     * @param tour Updated tour data (required)
     * @return Tour successfully updated (status code 200)
     *         or Tour not found (status code 404)
     */
    @Operation(
        operationId = "putTourById",
        summary = "Update tour",
        description = "Updates tour information by its ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Tour successfully updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Tour.class))
            }),
            @ApiResponse(responseCode = "404", description = "Tour not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/tourCrud/{tourId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Tour> putTourById(
        @Parameter(name = "tourId", description = "Tour ID", required = true, in = ParameterIn.PATH) @PathVariable("tourId") UUID tourId,
        @Parameter(name = "Tour", description = "Updated tour data", required = true) @Valid @RequestBody Tour tour
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"endDate\" : \"2000-01-23\", \"price\" : 0.8008281904610115, \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startDate\" : \"2000-01-23\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
     class ApiUtil {
        public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
        HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
        res.setCharacterEncoding("UTF-8");
        res.addHeader("Content-Type", contentType);
        res.getWriter().print(example);
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
        }
    }
