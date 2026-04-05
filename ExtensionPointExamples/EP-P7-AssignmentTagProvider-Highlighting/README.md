# Assignment Tag Provider Highlighting Example

## Goal

Verify that `as` is highlighted as a language keyword inside a registered assignment tag.

## Preconditions

1. Register an `assignmentTagProvider` for `import_yaml`.

## Verification Steps

1. Open `basic-import-yaml.jinja`.
2. Check the `as` token inside the assignment tag.
3. Confirm that it is highlighted with the same keyword style as core Jinja keywords.

## Notes

- The highlighting is semantic and applies only to registered assignment tags.
- This avoids falsely highlighting `as` inside unrelated unknown/custom tags.
