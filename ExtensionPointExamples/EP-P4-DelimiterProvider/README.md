# EP-P4 DelimiterProvider Example

This example demonstrates how to validate custom Jinja2 delimiters contributed through `delimiterProvider`.

## Files
- `custom_template.sls`: template using custom delimiters (`[% %]`, `[[ ]]`, `[# #]`).
- `standard_template.jinja`: template using standard delimiters (`{% %}`, `{{ }}`, `{# #}`).
- `preview.html`: simple host HTML file to test embedded custom template snippets.

## Manual Verification Steps
1. Register a test `delimiterProvider` implementation that returns:
   - block: `[%` / `%]`
   - variable: `[[` / `]]`
   - comment: `[#` / `#]`
   - `appliesTo(file, project)` -> `file.getName().endsWith(".sls")`
2. Open `custom_template.sls` in IDE and verify:
   - `[[ user.name ]]` is highlighted as a Jinja expression.
   - `{{ user.name }}` is not highlighted as a Jinja expression in this file.
   - typing `[[` auto-inserts `[[  ]]` with caret in the middle.
3. Open `standard_template.jinja` and verify standard delimiter behavior is preserved.
4. Open `preview.html` and verify custom delimiters are tokenized in mixed HTML context.

## Expected Result
The provider controls delimiter recognition for matching files without introducing framework-specific dependencies.
