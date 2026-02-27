# EP-P1 Type Schema Provider Smoke Example

## Goal
Validate attribute completion and undefined-attribute inspection powered by `typeSchemaProvider`.

## Files

- `docs/examples/type-schema-provider/UserTypeSchemaProvider.java`
- `docs/examples/type-schema-provider/current_user-valid.jinja`
- `docs/examples/type-schema-provider/current_user-invalid.jinja`
- `docs/examples/type-schema-provider/current_user-unknown-tail.jinja`

## Manual Verification Steps

1. Register `UserTypeSchemaProvider` in your plugin's `plugin.xml` using the `com.jinja2enhanced.typeSchemaProvider` extension.
2. Open `current_user-valid.jinja` and trigger completion at `{{ current_user.<caret> }}`.
3. Verify completion suggests `email`, `username`, `address`, and `profile`.
4. In `current_user-valid.jinja`, confirm no `Undefined attribute` warning for `current_user.email` and `current_user.address.city`.
5. Open `current_user-invalid.jinja` and confirm `current_user.nonexistent` is reported as `Undefined attribute`.
6. Open `current_user-unknown-tail.jinja` and confirm `current_user.profile.avatar` is not reported because `profile` has unknown field type (`type = null`).

## Expected Results

- Known fields from registered schemas appear in member completion.
- Missing fields are reported by `Jinja2UndefinedAttributeInspection`.
- Unknown tails are tolerated when the schema field type is intentionally unspecified.
