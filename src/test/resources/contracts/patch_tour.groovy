package contracts

import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "Partially updates tour information by its ID"
    request {
        method PATCH()
        url("/tourCrud" + '/123e4567-e89b-12d3-a456-426614174000')
        body([
                name       : $(consumer(optional(regex('.+'))), producer("Updated name")),
                description: $(consumer(optional(regex('.+'))), producer("Updated description")),
                price      : $(consumer(optional(regex('[0-9]+(\\.[0-9]{1,2})?'))), producer(100.00))
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status NO_CONTENT()
    }
}
