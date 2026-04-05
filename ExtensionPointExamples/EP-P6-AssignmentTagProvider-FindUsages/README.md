# Assignment Tag Provider Find Usages Example

## Goal

Verify that `Alt+F7` on a variable declared via `as` inside a registered assignment tag
finds its usages in the template.

## Preconditions

1. Register an `assignmentTagProvider` for `import_yaml`.
2. Ensure `Goto Definition` for assignment tag variables is already working.

## Verification Steps

1. Open `basic-import-yaml.jinja`.
2. Place the caret on `imported_yaml` in the declaration.
3. Invoke `Alt+F7`.
4. Confirm that usages in `{{ imported_yaml }}` and `{{ imported_yaml.key }}` are found.

## Notes

- The search anchor is the bound variable identifier, not the tag name.
- This keeps `Find Usages` scoped to the variable declaration even when the tag is custom.
