# Function Provider Example

## Goal

This example demonstrates how a plugin can contribute callable global functions through the
`functionProvider` extension point.

Functions are distinct from filters (`|`) and tests (`is`): they appear as direct
call expressions in Jinja2 templates — e.g. `{{ url_for('index') }}`.

## Files

- `function_demo.jinja2` — template calling custom global functions.
- `SampleFunctionProvider.java` — minimal provider implementation.

## How To Verify

1. Register `SampleFunctionProvider` in your plugin XML:
   ```xml
   <extensions defaultExtensionNs="com.jinja2enhanced">
       <functionProvider implementation="com.example.SampleFunctionProvider"/>
   </extensions>
   ```
2. Open `function_demo.jinja2`.
3. Trigger completion at the start of an expression:
   - `{{ <caret> }}`
4. Expected completion items include custom functions: `url_for`, `get_flashed_messages`, `csrf_token`.
5. Hover or press Ctrl+Q on a custom function name:
   Expected: documentation popup shows the description from the descriptor.
6. Custom function names must not be reported as undefined variables by inspections.
7. Built-in functions from `DefaultJinja2FunctionProvider` must still be offered.

## Expected Result

The `functionProvider` EP lets any dependent plugin register framework-level callable
globals (e.g. Flask `url_for`, `get_flashed_messages`) visible in Jinja2 templates.
