# Macro Module Provider Example

## Goal

This example demonstrates how a plugin can expose virtual Jinja2 macro modules through the
`macroModuleProvider` extension point.

## Files

- `sample_usage.jinja2` shows imports and usages that should be resolved/completed.
- `bootstrap5/form.html` is a regular template file used to validate merge behavior with PSI.
- `SampleMacroModuleProvider.java` is a minimal provider implementation.

## How To Verify

1. Register `SampleMacroModuleProvider` in your plugin XML:
   ```xml
   <extensions defaultExtensionNs="com.jinja2enhanced">
       <macroModuleProvider implementation="com.example.SampleMacroModuleProvider"/>
   </extensions>
   ```
2. Open `sample_usage.jinja2`.
3. Trigger completion in:
   - `{% from "bootstrap5/form.html" import <caret> %}`
   - `{{ forms.<caret> }}`
4. Expected completion items:
   - Macros: `render_field`, `render_form`, `render_vform`
   - Exported variables: `theme`
5. Expected resolve behavior:
   - `render_field` imported from `from ... import` resolves without unresolved warnings.
   - `forms.render_field` from module alias resolves without unresolved warnings.
6. If `bootstrap5/form.html` also contains macros/exports, completion should merge provider + PSI data with
   provider names taking precedence on duplicates.
