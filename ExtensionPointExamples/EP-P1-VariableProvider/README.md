# Variable Provider Example

## Goal

This example demonstrates how a plugin can inject additional global variables into Jinja2
completion through the `variableProvider` extension point.

Unlike `scopeVariableProvider` (which is context-sensitive and contributes loop/macro
scope variables), `variableProvider` contributes **globally available** variables that
are always present in any expression context.

## Files

- `variable_demo.jinja2` — template referencing custom global variables.
- `SampleVariableProvider.java` — minimal provider implementation.

## How To Verify

1. Register `SampleVariableProvider` in your plugin XML:
   ```xml
   <extensions defaultExtensionNs="com.jinja2enhanced">
       <variableProvider implementation="com.example.SampleVariableProvider"/>
   </extensions>
   ```
2. Open `variable_demo.jinja2`.
3. Trigger completion in `{{ <caret> }}`:
   Expected items include custom globals: `app`, `g`, `config`.
4. Trigger completion on `{{ app.<caret> }}`:
   Expected: child descriptors `name`, `version`, `debug` appear.
5. Custom variables must not produce "undefined variable" inspection warnings.
6. Built-in globals from `DefaultJinja2VariableProvider` must still be offered.

## Expected Result

The `variableProvider` EP lets any dependent plugin declare framework-level globals
(e.g. Flask `g`, `config`, `app`) that are always injected into Jinja2 templates.
