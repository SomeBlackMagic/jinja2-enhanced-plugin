# Inspection Rule Provider Example

## Goal
Verify that `inspectionRuleProvider` suppresses:
- undefined variable warnings by wildcard patterns (`ansible_*`)
- undefined attribute warnings by attribute name (`safe_attr`)

## Files
- `ansible_whitelist.jinja2` - validates variable whitelist behavior
- `attribute_whitelist.html` - validates attribute whitelist behavior

## How to Verify
1. Open `ansible_whitelist.jinja2`.
2. In **Settings -> Languages -> Jinja2**, set:
   `Undefined variable whitelist (comma-separated, supports *) = ansible_*`
3. Run code inspections in the file.
4. Expected result:
   - No warning for `ansible_facts`
   - Warning remains for `missing_custom`

5. Open `attribute_whitelist.html`.
6. Register an extension implementation of `Jinja2InspectionRuleProvider` that returns:
   - `undefinedAttributeWhitelist = ["safe_attr"]`
7. Run code inspections in the file.
8. Expected result:
   - No warning for `data.safe_attr`
   - Warning remains for `data.risky_attr`

## Minimal Provider Contract
Use extension point:
`com.jinja2enhanced.inspectionRuleProvider`

Provider method:
`Jinja2InspectionRules getRules(Project project)`
