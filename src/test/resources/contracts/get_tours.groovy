package contracts

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    description "Returns a list of all tours with pagination support"
    request {
        method GET()
        urlPath('http://localhost:8080/tour') {
            queryParameters {
                parameter 'page': $(consumer(regex('[0-9]+')), producer(0))
                parameter 'size': $(consumer(regex('[0-9]+')), producer(10))
            }
        }
    }
    response {
        status OK()
        body([
                [
                        id: $(regex('[0-9]+')),
                        name: $(regex('[a-zA-Z]+')),
                        description: $(regex('.+')),
                        price: $(regex('[0-9]+(\\.[0-9]{1,2})?')),
                        startDate: $(regex('\\d{4}-\\d{2}-\\d{2}')),
                        endDate: $(regex('\\d{4}-\\d{2}-\\d{2}'))
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
