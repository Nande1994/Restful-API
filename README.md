# Restful Booker API Automation Framework

## Overview

This repository contains a maintainable and scalable API automation framework developed as part of a QA Engineer technical challenge using the public **Restful Booker API**.

The objective of this submission was not simply to automate endpoints, but to demonstrate how a modern QA Engineer would design a framework that is:

- Easy to understand for new team members
- Easy to maintain as the product grows
- Reusable across future endpoints
- Suitable for CI/CD pipelines
- Capable of producing clear execution reporting

The framework focuses on clean architecture, separation of concerns, and practical engineering standards.

---

## Tech Stack

| Technology | Purpose |
|-----------|---------|
| Java 17 | Core programming language |
| Rest Assured | API automation |
| TestNG | Test execution and assertions |
| Maven | Build and dependency management |
| Allure Report | Rich HTML reporting |
| GitHub Actions | Continuous Integration |

---

## Framework Architecture

The framework follows a layered design pattern to improve maintainability, readability, and reuse.

```text
src/test/java
├── base
│   └── BaseTest.java
│
├── clients
│   ├── AuthClient.java
│   └── BookingClient.java
│
├── models
│   ├── Booking.java
│   ├── BookingDates.java
│   └── CreateBookingResponse.java
│
├── tests
│   ├── AuthTests.java
│   ├── BookingTests.java
│   └── HealthCheckTests.java
│
└── utils
    └── TestDataFactory.java
    
   
## Design Principles Applied

### Separation of Concerns

- Test classes contain scenarios and assertions only
- Client classes manage API requests
- Models represent request / response payloads
- Utility classes provide reusable test data and helpers

### Reusability

Shared components allow new tests to be added with minimal duplication.

### Scalability

Additional endpoints can be introduced by adding:

- New client classes
- New models
- New test scenarios

Without impacting existing tests

## Functional Coverage

### Authentication
- Generate token using valid credentials

### Booking Lifecycle
- Create booking
- Retrieve booking by ID
- Update existing booking
- Delete booking
- Verify deleted booking returns `404`

### Service Monitoring
- Verify `/ping` endpoint returns healthy response


High-Level Test Scenarios
Authentication
Verify that a token is generated when valid credentials are submitted.
Booking Management
Verify that a booking can be created successfully with valid data.
Verify that a created booking can be retrieved by booking ID.
Verify that an existing booking can be updated successfully using a valid token.
Verify that an existing booking can be deleted successfully using a valid token.
Verify that retrieving a deleted booking returns 404.
Health Check
Verify that the health check endpoint returns a successful response.

If you want it a little more senior, use this version:

High-Level Test Scenarios


The framework currently covers the following high-level scenarios:

Authentication token generation with valid credentials
Booking creation with valid payload data
Booking retrieval by ID
Booking update using authenticated access
Booking deletion using authenticated access
Validation that deleted bookings are no longer accessible
API health check verification

