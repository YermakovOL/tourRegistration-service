package contracts

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    description "Updates tour information by its ID"
    request {
        method PUT()
        urlPath('http://localhost:8080/tour/' + $(regex('[0-9]+')))
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
        status OK()
        headers {
            body([
                    id: $(regex('[0-9]+')),
                    name: 'Updated Tour',
                    description: 'Updated description',
                    price: 150.00,
                    startDate: '2024-06-01',
                    endDate: '2024-06-10'
            ])
            contentType(applicationJson())
        }
    }
}
