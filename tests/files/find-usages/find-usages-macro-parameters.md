# Find Usages: Macro Parameters (runIde)

Use this file for manual verification:

- `docs/examples/find-usages-macro-parameters.jinja2`

## Scenario A: `greet` macro parameter `name`

1. Open `find-usages-macro-parameters.jinja2`.
2. Put caret on parameter declaration `name` in `{% macro greet(name, title='Mx') %}`.
3. Run **Find Usages**.

Expected:

- Usages are found only inside `greet` body (`{{ name }}` occurrences).
- `{{ name }}` in `wrapper` macro is not included.
- Outer `{{ name }}` at the bottom is not included.

## Scenario B: `greet` macro parameter `title`

1. Put caret on parameter declaration `title` in `{% macro greet(name, title='Mx') %}`.
2. Run **Find Usages**.

Expected:

- Usage in `Greeting: {{ title }} {{ name }}` is found.
- Outer `{{ title }}` at the bottom is not included.

## Scenario C: `wrapper` macro parameter `name`

1. Put caret on parameter declaration `name` in `{% macro wrapper(name) %}`.
2. Run **Find Usages**.

Expected:

- Only `Wrapper usage: {{ name }}` is found.
- `greet` parameter usages are not included.
- Outer `{{ name }}` at the bottom is not included.
