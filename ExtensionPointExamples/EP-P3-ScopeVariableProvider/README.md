# Scope Variable Provider Example

## Purpose
This example demonstrates scope-aware variables contributed by `scopeVariableProvider`:
- `loop.*` variables inside `{% for %}` blocks
- `caller`, `varargs`, `kwargs` inside `{% macro %}` blocks

## Files
- `scope_loop_demo.jinja2`
- `scope_macro_demo.html`

## Manual Verification
1. Open `scope_loop_demo.jinja2` in the IDE.
2. Put the caret after `loop.` and trigger completion:
   Expected items include `index`, `index0`, `revindex`, `first`, `last`, `length`, `cycle`, `depth`, `depth0`, `previtem`, `nextitem`, `changed`.
3. Check inspections:
   `loop.index` should not be reported as undefined, while `loop.unknown_attr` should be reported as `Undefined attribute`.
4. Open `scope_macro_demo.html`.
5. Inside macro body, trigger completion in `{{ ... }}`:
   Expected scope variables include `caller`, `varargs`, and `kwargs`.
6. Outside the macro body, those macro scope variables should not be offered.
