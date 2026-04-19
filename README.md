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
   ----
 ## Run Instructions

### Clone Repository

```bash
git clone https://github.com/Nande1994/Restful-API.git
cd Restful-API

Execute All Tests
mvn clean test

Run TestNG Suite
mvn test -DsuiteXmlFile=testng.xml

Generate Allure Report
allure serve allure-results

## Assumptions

The following assumptions were made during implementation:

- The public Restful Booker API remains available during execution.
- Valid authentication credentials remain unchanged.
- Test data created during execution can be safely modified or deleted.
- The environment is intended for test/demo purposes rather than production use.

---


## High-Level Test Scenarios

### Positive Scenarios

- Verify token generation with valid credentials.
- Verify booking creation with valid data.
- Verify booking retrieval by ID.
- Verify booking update using authenticated access.
- Verify booking deletion using authenticated access.
- Verify API health check response.

### Negative Scenarios

- Verify deleted booking returns `404`.

### Why These Were Prioritised

These scenarios were selected to cover the highest-value business flows:

- Authentication
- Core CRUD booking lifecycle
- Access control
- Service availability

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

## Test Coverage Summary

### Positive Scenarios

- Verify token generation with valid credentials
- Verify booking creation with valid payload data
- Verify booking retrieval by booking ID
- Verify booking update using authenticated access
- Verify booking deletion using authenticated access
- Verify API health check endpoint response

### Negative Scenarios

- Verify invalid login does not return a usable token
- Verify booking update without token returns forbidden response
- Verify retrieving a non-existing booking returns `404`

### Test Strategy

Scenarios were prioritised using a risk-based approach focusing on:

- Authentication security
- Core booking business flows
- Access control
- API availability
- Defensive negative coverage

## Known Limitations

- Public APIs may occasionally be unstable or rate limited.
- Test execution currently targets a single environment.
- No database-level validation is available for backend verification.
- Parallel execution has not yet been enabled.
- Additional negative and boundary scenarios could be expanded further.
 
## Reporting

The framework integrates with **Allure Reports** to provide clear and readable test execution results for both technical and non-technical stakeholders.

### Reporting Features

- Pass / Fail execution summary
- Test duration and execution timeline
- Environment details
- Individual test case results
- Request / response attachments (where applicable)
- Historical trends (when report history is retained)

### Generate Report Locally

```bash
allure serve allure-results   
## CI/CD

A GitHub Actions pipeline has been implemented to automate test execution and provide fast feedback on code changes.

### Trigger Conditions

- Push to `main`
- Pull requests

### Pipeline Responsibilities

- Check out source code
- Set up Java 17
- Restore Maven dependencies using cache
- Execute automated API tests
- Upload test artifacts
- Upload Allure results

### Outcome

This ensures that tests run consistently in a clean environment and that execution results are accessible from GitHub Actions.   

## Future Enhancements

Given additional time, the following improvements would be implemented:

- JSON schema validation for stronger response contract checks
- Data-driven testing using external test data sources
- Parallel execution to reduce runtime
- Retry logic for unstable environments
- Environment profiles (DEV / QA / UAT / PROD)
- Published Allure HTML reports via GitHub Pages
- Expanded negative, boundary, and edge-case coverage
- Docker support for consistent execution across machines
- Contract testing for API compatibility assurance
- Integration with Jira / Test Management tools

## Author

**Sylvia Hili**  
QA Engineer | Test Automation | API Testing | CI/CD
