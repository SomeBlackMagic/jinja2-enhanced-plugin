# Assignment Tag Attribute Inspection

## Purpose

This example demonstrates the expected inspection behavior for variables contributed by an assignment-tag-like provider:

- unknown type: no undefined attribute warning
- known type: warning for unknown attribute
- known type: no warning for known attribute

## How to verify

1. Register a test or demo variable provider that contributes `imported_yaml`.
2. For the unknown-type case, return the variable with empty `children`.
3. For the known-type cases, return the variable with one child named `good_attr`.
4. Open the example files in the IDE and run highlighting/inspection.

## Expected result

- `unknown_type_no_warning.jinja2`: no warning on `any_attr`
- `known_type_warning.jinja2`: warning on `bad_attr`
- `known_type_ok.jinja2`: no warning on `good_attr`
