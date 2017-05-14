Simple ShoppingCart rest api using Spring MVC @RestController

A running example of this micro-service can be seen at the following endpoint.

Host: localhost 	(default in local)

Port: 8080 			(default in local)

HTTP Method: GET


Retrieve Discounts (no filters): http://localhost:8080/rest/v1/users/qa-test-user/discounts

Retrieve Discounts with productId filter: http://localhost:8080/rest/v1/users/qa-test-user/discounts?productId=sku-1234567890

Retrieve Discounts - error - user not found: http://localhost:8080/rest/v1/users/bad-user/discounts

Request user: http://localhost:8080/rest/v1/users/<uuid>/discounts
