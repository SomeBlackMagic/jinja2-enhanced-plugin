# Function Provider Example

## Goal

This example demonstrates how a plugin can contribute callable global functions through the
`functionProvider` extension point.

Functions are distinct from filters (`|`) and tests (`is`): they appear as direct
call expressions in Jinja2 templates — e.g. `{{ url_for('index') }}`.

## Files

- `function_demo.jinja2` — template calling custom global functions.
- `SampleFunctionProvider.java` — provider with positional, keyword, `**kwargs`, and no-argument signatures.

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
5. Trigger parameter completion inside `url_for(`. Expected signature: required `endpoint` and optional
   `**values`; `user_id=` and `filename=` remain valid route-value arguments.
6. Trigger parameter completion inside `get_flashed_messages(`. Expected optional keyword parameters:
   `with_categories` and `category_filter`.
7. Trigger parameter completion inside `csrf_token(`. Expected: no parameters, not an unknown signature.
8. Hover or press Ctrl+Q on a custom function. Expected: its return type and rich documentation
   from the descriptor are shown.
9. Custom function names must not be reported as undefined variables by inspections.
10. Ctrl+B on these sample functions intentionally has no target: the provider supplies no
    `navigationTarget`. A production provider may supply a valid PSI declaration target.
11. Built-in functions from `DefaultJinja2FunctionProvider` must still be offered. If two providers
    contribute the same function name, the descriptor from the first registered provider wins.

## Expected Result

The `functionProvider` EP lets any dependent plugin register framework-level callable globals
(e.g. Flask `url_for`, `get_flashed_messages`) with signatures, return types, documentation, and
optional navigation targets. Local and imported macros keep precedence over a same-named global.
