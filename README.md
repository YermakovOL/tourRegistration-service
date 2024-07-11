
[Project Overview](#project-overview)
[Google OAuth2](#google-oauth2)
[ElasticSearch Configuration](#elasticsearch-configuration)
[Spring Cloud Contract (SCC)](#spring-cloud-contract-scc)
[TestContainers](#testcontainers)
[OpenAPI](#openapi)
[Running the Project](#running-the-project)
[Integration Testing](#integration-testing)

## Project Overview
This project implements CRUD functionality using advanced technologies and practices such as OAuth2, OpenID, ElasticSearch, and RabbitMQ. It consists of two microservices:

- **localhost:8080 - tourRegistration-service**
- **localhost:8081 - [travelAgency-OAuth2-client](https://github.com/YermakovOL/travelAgency-OAuth2-client)**

## Google OAuth2

The tour registration service, in OpenID terminology, is a resource server. Access to some of its functions is only available with an access token (opaque token). To obtain an access token, the end user must either use the HTML page or directly access the endpoints of the travel-agency-client. The Spring framework handles the logic of redirecting the user to the Google authorization page, where the user voluntarily provides their data. Spring uses internal tools to store user data, which will be transmitted in requests to the tourRegistration-service.
Token validation is implemented in the class that implements the `OpaqueTokenIntrospector` interface. (src/main/java/yermakov/oleksii/tourregistrationservice/config/GoogleTokenIntrospector.java)
OAuth2 authentication is enabled in the configuration class through the SecurityFilterChain bean. (src/main/java/yermakov/oleksii/tourregistrationservice/config/SpringSecurityConfig.java)

## ElasticSearch Configuration

ElasticSearch enables efficient and intelligent text data search. Its configuration is defined in a directory with two files:

- **tours_index_mappings.json**: Index mapping settings. The index is set to strict mapping, so unauthorized changes will result in errors.
- **tours_index_settings.json**: Index settings configuration, currently only defining a custom analyzer.
In addition to strict mapping validation, the project logic defines its own index validation during startup by comparing JSON files (defined in the project body and obtained from the index itself).

The Elasticsearch functionality is implemented in the `TourSearchService` interface (src/main/java/yermakov/oleksii/tourregistrationservice/service/TourSearchService.java). 
The `TourSearchServiceImpl` class implements this interface (src/main/java/yermakov/oleksii/tourregistrationservice/service/TourSearchServiceImpl.java). 
In this class, I used `ElasticsearchOperations` as the DAO class, but `ElasticsearchRepository` can also be used.

## Spring Cloud Contract (SCC)

Spring Cloud Contract (SCC) details:
Contracts are designed to document the format of API requests and responses. For each method, I wrote a Groovy contract. Besides documenting, SCC allows generating integration tests. This happens during maven install, generating and running the tests. All necessary test settings should be done in the base class.
- Groovy contract files (`src/test/resources/contracts`).
- Base test class (`src/test/java/yermakov/oleksii/tourregistrationservice/contractVerifier/BaseContractTest.java`).

## TestContainers

A great way to use test Docker containers. In the base class for SCC tests, I define all necessary external dependencies for successful testing.
``    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
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
    }``
- Base test class (`src/test/java/yermakov/oleksii/tourregistrationservice/contractVerifier/BaseContractTest.java`).


## OpenAPI

The project was developed with the specification-first practice, meaning specifications were written first. This allowed defining a single source of truth and generating initial code for APIs and models.
For correct generation, I used Mustache templates. I used the generated code as a template and manually supplemented it, although there is an approach to make all changes in the templates and constantly regenerate them.
- OpenApi specification (`api-specs/tourRegistration.yaml`)
- API specification in `src/main/java/yermakov/oleksii/tourregistrationservice/api/TourCrudApi.java`.
- Model definitions in `src/main/java/yermakov/oleksii/tourregistrationservice/model/TourDto.java`.
- Mustache templates for generating API code (`src/main/resources/templates/openapi-templates`).

## Running the Project

``cd docker
docker-compose up -d
mvn spring-boot:run``

## Integration Testing

``mvn clean install``
