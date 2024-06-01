package contracts
//TODO: проблема в этом контракте, наверное в optional,
// так же стоит расмотреть рефактор всех контрактов по типу как предлагают созадтели
// https://github.com/spring-cloud-samples/spring-cloud-contract-samples/tree/main/producer_java/src/test/java/contracts/beer/rest
import org.springframework.cloud.contract.spec.Contract;


Contract.make {
    description "Partially updates tour information by its ID"
    request {
        method PATCH()
        urlPath('http://localhost:8080/tour/' + $(regex('[0-9]+')))
        body([
                name       : $(consumer(optional(regex('.+'))),producer("Updated name")),
                description: $(consumer(optional(regex('.+'))),producer("Updated description")),
                price      : $(consumer(optional(regex('[0-9]+(\\.[0-9]{1,2})?'))),producer(100.00))
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body([
                id: $(regex('[0-9]+')),
                name       : $(regex('.+')),
                description: $(regex('.+')),
                price      : $(regex('[0-9]+(\\.[0-9]{1,2})?')),
                startDate  : $(regex('\\d{4}-\\d{2}-\\d{2}')),
                endDate    : $(regex('\\d{4}-\\d{2}-\\d{2}'))
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
