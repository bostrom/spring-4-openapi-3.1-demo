# Spring Boot 4 OpenAPI 3.1 Demo

A comprehensive demonstration of OpenAPI 3.1 nullable type patterns with Spring Boot 4, showcasing best practices and anti-patterns for handling nullable types in REST APIs.

## Overview

This project demonstrates three working patterns and one anti-pattern for representing nullable types in OpenAPI 3.1, along with their TypeScript client generation using Orval.

## Key Features

- ✅ Spring Boot 4.0.2 with Java 17
- ✅ OpenAPI 3.1 specification generation via springdoc-openapi
- ✅ Four patterns for nullable types (3 working + 1 anti-pattern)
- ✅ TypeScript client generation with Orval
- ✅ Comprehensive annotations and documentation

## Nullable Type Patterns

### Pattern 1: Polymorphic with oneOf ✅
**Use case**: Field can be one of multiple types, or null

```java
@Schema(
    oneOf = {CreditCardDto.class, BankTransferDto.class},
    types = {"object", "null"},
    description = "Payment method - can be credit card or bank transfer, or null"
)
private Object paymentMethod;
```

**Generated OpenAPI**:
```yaml
paymentMethod:
  type: [object, "null"]
  oneOf:
    - $ref: "#/components/schemas/CreditCardDto"
    - $ref: "#/components/schemas/BankTransferDto"
```

**TypeScript**: `paymentMethod?: CreditCardDto | BankTransferDto | null`

---

### Pattern 2: Single Type with oneOf ✅
**Use case**: Field is a single type/enum, or null

```java
@Schema(
    oneOf = {PaymentStatusDto.class},
    types = {"object", "null"},
    description = "Payment status - can be a status enum or null"
)
private Object status;
```

**Generated OpenAPI**:
```yaml
status:
  type: [object, "null"]
  oneOf:
    - $ref: "#/components/schemas/PaymentStatusDto"
```

**TypeScript**: `status?: 'PENDING' | ... | null`

---

### Pattern 3: Typed Property with types Array ✅ (RECOMMENDED)
**Use case**: Field is a known type, or null - maintains Java type safety

```java
@Schema(
    types = {"object", "null"},
    description = "Billing address - can be an address object or null"
)
private BillingAddressDto billingAddress;
```

**Generated OpenAPI**:
```yaml
billingAddress:
  type: [object, "null"]
  $ref: "#/components/schemas/BillingAddressDto"
```

**TypeScript**: `billingAddress?: BillingAddressDto | null`

**Advantages**:
- ✅ Full type safety in Java code
- ✅ Direct property type (not Object)
- ✅ IDE autocomplete and refactoring support
- ✅ Compile-time type checking

---

### Anti-Pattern: oneOf with Same Type as Property ❌
**Problem**: Creates superfluous $ref that some tools may not handle correctly

```java
@Schema(
    oneOf = {BillingAddressDto.class},
    types = {"object", "null"},
    description = "Shipping address (ANTI-PATTERN: will create superfluous $ref)"
)
private BillingAddressDto shippingAddress;  // Property type matches oneOf content!
```

**Generated OpenAPI** (problematic):
```yaml
shippingAddress:
  type: [object, "null"]
  oneOf:
    - $ref: "#/components/schemas/BillingAddressDto"  # Superfluous!
  $ref: "#/components/schemas/BillingAddressDto"
```

**Why it's problematic**: Having both `oneOf` with a single `$ref` AND a direct `$ref` sibling is redundant. Use Pattern 3 instead.

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- Node.js 16+ (for TypeScript generation)

### Run the Application

```bash
./mvnw spring-boot:run
```

The API will be available at: http://localhost:8080

OpenAPI documentation: http://localhost:8080/swagger-ui.html

OpenAPI spec (YAML): http://localhost:8080/api-docs.yaml

### Generate TypeScript Client

See [node/README.md](./node/README.md) for TypeScript client generation instructions.

## Project Structure

```
.
├── src/main/java/com/example/demo/
│   ├── dto/
│   │   ├── PaymentDto.java              # Main demo DTO with all 4 patterns
│   │   ├── CreditCardDto.java           # Payment method option 1
│   │   ├── BankTransferDto.java         # Payment method option 2
│   │   ├── BillingAddressDto.java       # Address schema
│   │   └── PaymentStatusDto.java        # Enum for status
│   └── controller/
│       └── PaymentController.java       # REST endpoints
├── node/                                # TypeScript client generation
│   ├── src/api/                        # Generated TypeScript types
│   └── README.md                       # Client generation docs
└── docs/
    └── api-docs.yaml                   # Generated OpenAPI spec
```

## Key Configuration

`application.properties`:
```properties
# CRITICAL: Enable OpenAPI 3.1 mode
springdoc.api-docs.version=openapi_3_1

# Enable pretty-printed JSON/YAML
springdoc.writer-with-default-pretty-printer=true
```

## Important Notes

### OpenAPI 3.1 vs 3.0

OpenAPI 3.1 aligns with JSON Schema 2020-12, introducing:
- `type` arrays: `type: ["object", "null"]` replaces `nullable: true`
- Siblings with `$ref`: In 3.0, siblings were ignored; in 3.1, they're merged

### Pattern 3 Recommendation

Pattern 3 is the recommended approach because:
1. Maintains Java type safety
2. Clean OpenAPI schema
3. Best IDE support
4. No `Object` casting needed

**Note**: Orval v8.1.0 had a bug with Pattern 3 that's been fixed in [PR #2879](https://github.com/orval-labs/orval/pull/2879).

## Related Resources

- [OpenAPI 3.1 Specification](https://spec.openapis.org/oas/v3.1.0)
- [JSON Schema 2020-12](https://json-schema.org/draft/2020-12/json-schema-core.html)
- [springdoc-openapi Documentation](https://springdoc.org/)
- [swagger-core @Schema Annotation](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations)

## Contributing

Found an issue or have a suggestion? This is a demo project showcasing OpenAPI 3.1 patterns. Feel free to fork and adapt for your needs!

## License

This is a demonstration project - use freely for learning and reference.
