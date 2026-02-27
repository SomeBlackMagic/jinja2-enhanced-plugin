# Filter Provider Example

## Goal

This example demonstrates how a plugin can contribute custom Jinja2 filters through the
`filterProvider` extension point.

## Files

- `filter_demo.jinja2` — template using custom filters that should be offered in completion.
- `SampleFilterProvider.java` — minimal provider implementation adding framework-specific filters.

## How To Verify

1. Register `SampleFilterProvider` in your plugin XML:
   ```xml
   <extensions defaultExtensionNs="com.jinja2enhanced">
       <filterProvider implementation="com.example.SampleFilterProvider"/>
   </extensions>
   ```
2. Open `filter_demo.jinja2`.
3. Trigger completion after `|` in an expression context:
   - `{{ value | <caret> }}`
4. Expected completion items include custom filters: `slugify`, `to_yaml`, `highlight`.
5. Hover or press Ctrl+Q on a custom filter name:
   Expected: documentation popup shows the description provided by the descriptor.
6. Built-in filters from `DefaultJinja2FilterProvider` must still be offered alongside custom ones.

## Expected Result

The `filterProvider` EP allows any dependent plugin to extend the completion list with
domain-specific filters without modifying the core plugin.
