# Assignment Tag Provider Goto Definition Example

## Goal

Verify that `Ctrl+B` on a variable usage resolves to the variable declared via `as`
inside a registered assignment tag.

## Preconditions

1. Register an `assignmentTagProvider` for `import_yaml`.
2. If you want to test block tags, also register a matching `tagBehaviorProvider`.

## Verification Steps

1. Open `basic-import-yaml.jinja`.
2. Place the caret on `imported_yaml` in `{{ imported_yaml }}`.
3. Invoke `Ctrl+B`.
4. Confirm that navigation lands on `imported_yaml` inside `{% import_yaml 'f.yml' as imported_yaml %}`.

## Notes

- This example is generic and framework-independent.
- `Goto Definition` depends on the parser creating a dedicated PSI declaration node for the variable after `as`.
