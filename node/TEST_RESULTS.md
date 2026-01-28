# Orval Fix Test Results

## Test Date

January 29, 2026

## Fix Applied

Added support for OpenAPI 3.1 type arrays with `$ref` in Orval's `packages/core/src/resolvers/ref.ts`

## Test Method

1. Built Orval from source with the fix (commit: 75988f61)
2. Linked the built version to the demo project
3. Regenerated TypeScript types from OpenAPI 3.1 spec
4. Verified the generated types

## Results

### ✅ Pattern 3 (FIXED): Typed Property with types array

**OpenAPI Schema:**

```yaml
billingAddress:
  type:
    - object
    - 'null'
  $ref: '#/components/schemas/BillingAddressDto'
  description: Billing address - can be an address object or null
```

**Before Fix:**

```typescript
billingAddress?: BillingAddressDto;  // Missing | null
```

**After Fix (WORKING):**

```typescript
billingAddress?: BillingAddressDto | null;  // ✅ Correctly includes | null
```

### ✅ Pattern 1: Polymorphic with oneOf (Already Working)

**Generated TypeScript:**

```typescript
paymentMethod?: CreditCardDto | BankTransferDto | null;
```

### ✅ Pattern 2: Single Type with oneOf (Already Working)

**Generated TypeScript:**

```typescript
status?: 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'FAILED' | 'REFUNDED' | 'CANCELLED' | null;
```

## Conclusion

The fix successfully resolves the OpenAPI 3.1 type array support issue. Pattern 3 now generates correct TypeScript types with proper nullable unions.

## Files Modified

- `/Users/bostrom/Projects/OpenSource/orval/packages/core/src/resolvers/ref.ts` (lines 105-108)

## Changes

```typescript
// Handle OpenAPI 3.1 type array (e.g., type: ["object", "null"])
// This preserves nullable information when using direct $ref with types array
if ('type' in schema && Array.isArray(schema.type)) {
  currentSchema = { ...currentSchema, type: schema.type };
}
```

## Ready for PR

✅ Fix validated and working correctly
✅ Generated types match expected behavior
✅ No regression in existing patterns
