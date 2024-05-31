package contracts

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    description "Deletes a tour by its ID"
    request {
        method DELETE()
        urlPath('http://localhost:8080/tour/' + $(regex('[0-9]+')))
    }
    response {
        status NO_CONTENT()
    }
}
