# Tag Behavior Provider Example

## Goal

This example demonstrates how a plugin can declare custom Jinja2 statement tags and their
parser behavior through the `tagBehaviorProvider` extension point.

The provider tells the parser whether a tag is:
- **block** — requires a matching closing tag (`{% endmytag %}`),
- **inline** — self-contained, no closing tag needed.

Custom closing aliases can also be declared for non-standard `end*` conventions.

## Files

- `tag_behavior_demo.jinja2` — template using custom block and inline tags.
- `SampleTagBehaviorProvider.java` — minimal provider implementation.

## How To Verify

1. Register `SampleTagBehaviorProvider` in your plugin XML:
   ```xml
   <extensions defaultExtensionNs="com.jinja2enhanced">
       <tagBehaviorProvider implementation="com.example.SampleTagBehaviorProvider"/>
   </extensions>
   ```
2. Open `tag_behavior_demo.jinja2`.
3. Verify that `{% cache %}...{% endcache %}` is parsed as a block with no errors:
   - The inner content is indented and recognized as the block body.
   - No "unexpected token" or "unclosed block" parse errors are shown.
4. Verify that `{% cache_bust %}` is parsed as an inline tag with no errors:
   - The tag requires no closing `{% endcache_bust %}`.
5. Verify that `{% compress %}...{% endcompress %}` is parsed correctly using the
   custom alias (standard `endcompress` convention).
6. Remove the provider registration and reopen the file:
   Expected: the parser falls back to heuristic detection; block/inline classification
   may change but must not crash.

## Expected Result

The `tagBehaviorProvider` EP enables parser-level support for framework-specific statement
tags (e.g. Django's `{% cache %}`, `{% compress %}`) without modifying the core plugin.
