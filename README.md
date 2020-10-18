# order_service
RabbitMQ docker command: docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

java -jar -Dserver.port=8080 /Users/tranthituongvi/.m2/repository/com/example/microservice/order-service/0.0.1-SNAPSHOT/order-service-0.0.1-SNAPSHOT.jar

path: http://localhost:8080/orders
    GET: /{orderId}
        response body:
            {
                "id": "72ae0b75-b3ce-4934-a381-3ae1344a1684",
                "orderDetailList": [
                    {
                        "id": "1da52496-e5ab-4def-8f34-9984e4dd124b",
                        "code": "prod-3",
                        "name": "product 3",
                        "quantity": 3,
                        "price": 50,
                        "totalAmount": 150
                    }
                ],
                "totalAmount": 100,
                "customerId": "5278e2f3-93f0-4005-a184-37b81ecf92ef"
            }
    GET: /{customerId}
            response body:
                [
                    {
                        "id": "72ae0b75-b3ce-4934-a381-3ae1344a1684",
                        "orderDetailList": [
                            {
                                "id": "1da52496-e5ab-4def-8f34-9984e4dd124b",
                                "code": "prod-3",
                                "name": "product 3",
                                "quantity": 3,
                                "price": 50,
                                "totalAmount": 150
                            }
                        ],
                        "totalAmount": 100,
                        "customerId": "5278e2f3-93f0-4005-a184-37b81ecf92ef"
                    }
                ]  
    POST: 
        request body: 
            {
                "customerId": "2edf5044-deae-4b5a-be57-2d47a625b924",
                "totalAmount": 100,
                "orderDetailList": [
                    {
                        "code": "prod-2",
                        "name": "product 2",
                        "quantity": 2,
                        "price": 50,
                        "totalAmount": 100
                    }
                ]
            }
    PUT: 
        request body:
            {
                "customerId": "2edf5044-deae-4b5a-be57-2d47a625b924",
                "totalAmount": 100,
                "id": "2a95972e-eb1c-4662-a227-61656a834cb8",
                "orderDetailList": [
                    {
                        "code": "prod-3",
                        "name": "product 3",
                        "quantity": 3,
                        "price": 50,
                        "totalAmount": 150
                    }
                ]
            }
        response body:
            {
                "id": "72ae0b75-b3ce-4934-a381-3ae1344a1684",
                "orderDetailList": [
                    {
                        "id": "1da52496-e5ab-4def-8f34-9984e4dd124b",
                        "code": "prod-3",
                        "name": "product 3",
                        "quantity": 3,
                        "price": 50,
                        "totalAmount": 150
                    }
                ],
                "totalAmount": 100,
                "customerId": "5278e2f3-93f0-4005-a184-37b81ecf92ef"
            }