package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Updates tour information by its ID"
    request {
        method PUT()
        url ("/tourCrud" + '/123e4567-e89b-12d3-a456-426614174000')
        body([
                name: $(consumer(regex('.+')), producer('Updated Tour')),
                description: $(consumer(regex('.+')), producer('Updated description')),
                price: $(consumer(regex('[0-9]+(\\.[0-9]{1,2})?')), producer(150.00)),
                startDate: $(consumer(regex('\\d{4}-\\d{2}-\\d{2}')), producer('2024-06-01')),
                endDate: $(consumer(regex('\\d{4}-\\d{2}-\\d{2}')), producer('2024-06-10'))
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status NO_CONTENT()
    }
}
