    package contracts

    import org.springframework.cloud.contract.spec.Contract

    Contract.make {
        description "Creates a new tour based on the provided data"
        request {
            method POST()
            url "/tourCrud"
            body([
                    name       : $(consumer(regex('.+')), producer('Sample Tour')),
                    description: $(consumer(regex('.+')), producer('A wonderful tour')),
                    price      : $(consumer(regex('[0-9]+(\\.[0-9]{1,2})?')), producer(100.00)),
                    startDate  : $(consumer(regex('\\d{4}-\\d{2}-\\d{2}')), producer('2024-06-01')),
                    endDate    : $(consumer(regex('\\d{4}-\\d{2}-\\d{2}')), producer('2024-06-10'))
            ])
            headers {
                contentType(applicationJson())
            }
        }
        response {
            status CREATED()
            headers {
                header('Location', $(" /tourCrud/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"))
            }
        }
    }
