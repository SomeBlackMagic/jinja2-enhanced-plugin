# Test Provider Example

## Goal

This example demonstrates how a plugin can contribute custom Jinja2 tests through the
`testProvider` extension point.

## Files

- `test_demo.jinja2` — template using custom tests in `is` / `is not` expressions.
- `SampleTestProvider.java` — minimal provider implementation adding framework-specific tests.

## How To Verify

1. Register `SampleTestProvider` in your plugin XML:
   ```xml
   <extensions defaultExtensionNs="com.jinja2enhanced">
       <testProvider implementation="com.example.SampleTestProvider"/>
   </extensions>
   ```
2. Open `test_demo.jinja2`.
3. Trigger completion after `is` in a conditional:
   - `{% if value is <caret> %}`
4. Expected completion items include custom tests: `email`, `url`, `uuid`.
5. Hover or press Ctrl+Q on a custom test name:
   Expected: documentation popup shows the description provided by the descriptor.
6. Built-in tests from `DefaultJinja2TestProvider` must still be offered alongside custom ones.

## Expected Result

The `testProvider` EP allows any dependent plugin to contribute domain-specific boolean
predicates for use in Jinja2 `is` expressions.
