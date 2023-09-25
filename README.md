<h2 align="center">
Airline Tickets API
</h2>

<p align="center">Retrieve airline tickets from different integrations</p>

## Requirements

- [JDK 11](https://sdkman.io/)
- [Kotlin 1.9.0](https://sdkman.io/)

## Technologies/Frameworks

- [Gradle](https://gradle.org/)
- [Ktor](https://ktor.io/)
- [OkHttp](https://square.github.io/okhttp/)
- [JUnit4](https://junit.org/junit4/)

## Start

To start the application, execute a Gradle Wrapper *run* task.

```bash
./gradlew run
```

```
curl --request GET \
--url 'http://localhost/retrieve-solicitations'
```

## Endpoints

### REST

- ***GET*** /retrieve-solicitations

<details>
 <summary><b>Response</b></summary>

```json
{
  "cheapestTickets": [
    {
      "company": "LATAM Airlines Brasil",
      "origin": "FLN",
      "destination": "GRU",
      "date": "01-09-2022",
      "price": 1031.48
    },
    {
      "company": "LATAM Airlines Brasil",
      "origin": "SDU",
      "destination": "CGH",
      "date": "01-10-2022",
      "price": 446.83
    }
  ],
  "summary": {
    "error": [
      "Could not extract tickets for SAO to FLN"
    ]
  }
}
```

</details>

## Tests

```bash
./gradlew test
```