package contracts

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    description "Returns information about a tour by its ID"
    request {
        method GET()
        urlPath('http://localhost:8080/tour/' + $(regex('[0-9]+')))
    }
    response {
        status OK()
        body([
                name: $(regex('.+')),
                name: $(regex('[a-zA-Z]+')),
                description: $(regex('.+')),
                price: $(regex('[0-9]+(\\.[0-9]{1,2})?')),
                startDate: $(regex('\\d{4}-\\d{2}-\\d{2}')),
                endDate: $(regex('\\d{4}-\\d{2}-\\d{2}'))
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
