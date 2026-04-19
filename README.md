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