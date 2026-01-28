# TypeScript Client Generator

This folder contains the setup for generating TypeScript types and API clients from the OpenAPI 3.1 specification.

## Setup

Install dependencies:

```bash
npm install
```

## Usage

### Generate TypeScript types and API client

```bash
npm run generate
```

This will:

1. Read the OpenAPI spec from `../docs/api-docs.yaml`
2. Generate TypeScript types in `src/api/types/`
3. Generate API client functions in `src/api/`
4. Format the generated code with Prettier

### Watch mode

To regenerate types automatically when the OpenAPI spec changes:

```bash
npm run generate:watch
```

### Type checking

Run TypeScript type checking on generated code:

```bash
npm run type-check
```

## Generated Files

- `src/api/types/*.ts` - TypeScript type definitions for all schemas
- `src/api/api.ts` - API client functions
- `src/api/*.msw.ts` - Mock Service Worker handlers (for testing)

## OpenAPI 3.1 Features Demonstrated

The generated types will showcase:

1. **Pattern 1 - Polymorphic types**: `paymentMethod` uses discriminated union types
2. **Pattern 2 - Single type with null**: `status` shows enum | null pattern
3. **Pattern 3 - Typed nullable**: `billingAddress` demonstrates clean nullable object type
4. **Anti-pattern**: `shippingAddress` shows how superfluous $ref affects type generation

Check the generated types to see how different annotation patterns translate to TypeScript!

## Orval OpenAPI 3.1 Support

### Pattern 3 Fix (Contributed)

**Issue**: Orval v8.1.0 and earlier didn't properly handle OpenAPI 3.1 `type` arrays when used with `$ref`, causing Pattern 3 to generate incorrect TypeScript types:

```typescript
// Generated (incorrect):
billingAddress?: BillingAddressDto;  // Missing | null

// Expected (correct):
billingAddress?: BillingAddressDto | null;
```

**Status**: Fixed in [PR #2879](https://github.com/orval-labs/orval/pull/2879) 🎉

**Workaround** (until the fix is merged): Use Pattern 1 or Pattern 2 for nullable types, or manually patch `node_modules/@orval/core/src/resolvers/ref.ts` to add type array support.

**Testing**: See [TEST_RESULTS.md](./TEST_RESULTS.md) for validation of the fix with this demo project.
