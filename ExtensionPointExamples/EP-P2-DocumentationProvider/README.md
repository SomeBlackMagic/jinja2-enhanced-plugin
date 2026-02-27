# EP P2 Example: elementDocumentationProvider

## Goal
Verify that Ctrl+Q documentation is resolved in this order:
1. `elementDocumentationProvider` extensions (first non-null wins)
2. built-in Jinja2 documentation provider
3. IntelliJ default "No documentation found"

## Files
- `documentation-demo.jinja2`
- `documentation-demo.html`

## How To Verify
1. Start IDE with this plugin build.
2. Open `documentation-demo.jinja2`.
3. Put caret on `to_json` in `{{ user | to_json }}` and press `Ctrl+Q`.
Expected: documentation from a custom `elementDocumentationProvider` if one is installed.
4. Put caret on `upper` in `{{ user.name | upper }}` and press `Ctrl+Q`.
Expected: built-in Jinja2 filter documentation.
5. Put caret on `defined` in `{% if user.email is defined %}` and press `Ctrl+Q`.
Expected: built-in Jinja2 test documentation.
6. Put caret on `unknown_filter` in `{{ user | unknown_filter }}` and press `Ctrl+Q`.
Expected: no custom or built-in docs, IntelliJ shows default "No documentation" message.

## Optional Custom Provider Check
Install any plugin that registers:
- EP: `com.jinja2enhanced.elementDocumentationProvider`
- Element: `FILTER`
- Name: `to_json`

Then repeat step 3 and confirm custom HTML is shown.
