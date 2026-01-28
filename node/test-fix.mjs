/**
 * Test the Orval fix for OpenAPI 3.1 type arrays with $ref
 * This simulates what the fix does in packages/core/src/resolvers/ref.ts
 */

// Simulate the original behavior (before fix)
function getSchemaWithoutFix(schema, schemaByRefPaths) {
  let currentSchema = schemaByRefPaths;

  // Only handles OpenAPI 3.0 nullable property
  if ('nullable' in schema) {
    currentSchema = { ...currentSchema, nullable: schema.nullable };
  }

  return currentSchema;
}

// Simulate the fixed behavior (after fix)
function getSchemaWithFix(schema, schemaByRefPaths) {
  let currentSchema = schemaByRefPaths;

  // Handle OpenAPI 3.0 nullable property
  if ('nullable' in schema) {
    currentSchema = { ...currentSchema, nullable: schema.nullable };
  }

  // Handle OpenAPI 3.1 type array (e.g., type: ["object", "null"])
  // This preserves nullable information when using direct $ref with types array
  if ('type' in schema && Array.isArray(schema.type)) {
    currentSchema = { ...currentSchema, type: schema.type };
  }

  return currentSchema;
}

// Simulate nullable detection (from resolveValue function)
function isNullable(schemaObject) {
  return (
    (Array.isArray(schemaObject.type) && schemaObject.type.includes('null')) ||
    schemaObject.nullable === true
  );
}

// Test case: Pattern 3 - typed property with types array
console.log('Testing OpenAPI 3.1 type array with $ref (Pattern 3)');
console.log('='.repeat(60));

const schema = {
  type: ['object', 'null'],
  $ref: '#/components/schemas/BillingAddressDto',
  description: 'Billing address - can be an address object or null',
};

const referencedSchema = {
  type: 'object',
  properties: {
    street: { type: 'string' },
    city: { type: 'string' },
    zipCode: { type: 'string' },
  },
};

console.log('\nInput schema:');
console.log(JSON.stringify(schema, null, 2));

console.log('\nReferenced schema:');
console.log(JSON.stringify(referencedSchema, null, 2));

// Without fix
const resultWithoutFix = getSchemaWithoutFix(schema, referencedSchema);
console.log('\n❌ WITHOUT FIX:');
console.log('Result schema:', JSON.stringify(resultWithoutFix, null, 2));
console.log('Is nullable?', isNullable(resultWithoutFix));
console.log(
  'Generated TypeScript:',
  isNullable(resultWithoutFix)
    ? 'billingAddress?: BillingAddressDto | null;'
    : 'billingAddress?: BillingAddressDto;'
);

// With fix
const resultWithFix = getSchemaWithFix(schema, referencedSchema);
console.log('\n✅ WITH FIX:');
console.log('Result schema:', JSON.stringify(resultWithFix, null, 2));
console.log('Is nullable?', isNullable(resultWithFix));
console.log(
  'Generated TypeScript:',
  isNullable(resultWithFix)
    ? 'billingAddress?: BillingAddressDto | null;'
    : 'billingAddress?: BillingAddressDto;'
);

console.log('\n' + '='.repeat(60));
console.log(
  isNullable(resultWithFix)
    ? '✅ Fix works! Type array is preserved and nullable is detected.'
    : '❌ Fix failed! Type array was not preserved.'
);
