package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Deletes a tour by its ID"
    request {
        method DELETE()
        url ("/tourCrud" + '/123e4567-e89b-12d3-a456-426614174000')
    }
    response {
        status NO_CONTENT()
    }
}
