package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Returns information about a tour by its ID"
    request {
        method GET()
        url ("/tourCrud" + '/123e4567-e89b-12d3-a456-426614174000')
    }
    response {
        status OK()
        body([
                name: $(regex('.+')),
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
