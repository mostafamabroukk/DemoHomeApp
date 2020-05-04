# HomeDemo1

Spring boot application + internal H2 database.
The challenge is found on https://www.notion.so/Backend-Challenge-vo2-2020-806b0b15b2944f0aada8b4a86849f7ee.

To launch the project: 
Clone the project and run the Main Class, and test the different functions using Postman, or run the test class.

**schema-h2.sql** contains the queries responsible to construct the table.
**data-h2.sql** contains the queries responsible to insert the rows into the table.

* /payments: returns all payments.
* /sumOfPayments/{contractId}: returns sum of payments with contractId.
* /paymentsInTimeFrame/{contractId}: returns payments with contractId, and pass startDate and endDate in the request body.
* /payments/add: insert new payment (it is passed in the request body).
* /payments/update/{paymentId}: update payment with paymentId (the whole new payment is passed in the request body).
* /payments/delete/{paymentId}: delete  payment with paymentId.
