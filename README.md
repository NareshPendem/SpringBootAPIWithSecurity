**Shopping Cart - Checkout Service**
----
  The API end point accepts multiple items with item identifier, item quantity, and price. Each transaction can only have one currency rate and one tax rate. 
  The response is in JSON format and has itemized response with per item pricing, cart level pricing and order identifier.
  The web service accept and respond in JSON Payload.

* **URL**

  /checkout/

* **Method:**

  `POST`
  
*  **URL Params**

	None

* **Data Params**

    **Sample Request Payload:** {
   "currencyRate" : 1,
   "taxRate":5,
   "items":[
      {
        "product" : {
		 "id":5,
		 "name" : "Disinfectant Wipes",
		 "price":10
		 },
         "quantity":2
      },
            {
        "product" : {
		 "id":248,
		 "name" : "Hand Sanitizer",
		 "price":20
		 },
         "quantity":2
      }
   ]
}

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** {
    "id": "4e9f525f-6c1f-485a-ad22-196f5286d825",
    "totalCartPrice": 63.00,
    "totalTaxApplied": 3.00,
    "items": [
        {
            "product": {
                "id": "5",
                "name": "Disinfectant Wipes",
                "price": 10
            },
            "quantity": 2,
            "totalPrice": 21.00,
            "taxPriceApplied": 1.00
        },
        {
            "product": {
                "id": "248",
                "name": "Hand Sanitizer",
                "price": 20
            },
            "quantity": 2,
            "totalPrice": 42.00,
            "taxPriceApplied": 2.00
        }
    ]
	}
 
* **Error Response:**

  * **Code:** 400 Bad Request <br />
    **Content:** `{
    "id": "cdb6063c-770e-4e70-ab40-1524eb35c441",
    "errors": [
        "Product id cannot be null",
        "Product price cannot be null"
    ]
  }`

  OR

  * **Code:** 404 Not Found <br />
    **Content:** `{
    "id": "d60fdb7b-7082-48fb-aede-8b21a2d88740",
    "errors": [
        "Resource Not Available"
    ]
  }
	
  OR
  
  * **Code:** 415 Unsupported Media Type <br />
    **Content:** `{
    "id": "f6994ea1-6aab-4d09-9937-443935c553a3",
    "errors": [
        "UnSupported Media Type"
    ]
  }

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)