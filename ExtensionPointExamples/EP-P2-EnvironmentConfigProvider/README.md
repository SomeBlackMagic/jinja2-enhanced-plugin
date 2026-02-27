# EP-P2 EnvironmentConfigProvider Example

This folder contains manual verification files for `Jinja2EnvironmentConfigProvider` integration.

## What to verify

1. Strict undefined behavior can upgrade `Undefined variable` from WARNING to ERROR.
2. `loopcontrols` extension enables `{% break %}` and `{% continue %}`.
3. `i18n` extension enables `{% trans %}` blocks.

## Files

- `templates/strict_undefined.jinja2` - undefined root variable usage.
- `templates/loopcontrols_disabled.jinja2` - `break` and `continue` statements.
- `templates/i18n_disabled.jinja2` - `trans` block.
- `templates/sample_output.html` - simple rendered-like page that includes the same constructs.

## Manual check steps

1. Open each template under `templates/`.
2. Ensure plugin settings currently disable `loopcontrols` and `i18n`.
3. Confirm inspection results:
   - `loopcontrols_disabled.jinja2` reports extension-required errors for `break` and `continue`.
   - `i18n_disabled.jinja2` reports extension-required error for `trans`.
   - `strict_undefined.jinja2` reports `Undefined variable` as WARNING without provider.
4. Register a test provider that returns:
   - `getEnabledExtensions`: `jinja2.ext.loopcontrols`, `jinja2.ext.i18n`
   - `getUndefinedBehavior`: `STRICT`
5. Reopen/rehighlight the files and verify:
   - `break`/`continue` errors disappear.
   - `trans` extension-required error disappears.
   - `Undefined variable` in `strict_undefined.jinja2` is now ERROR.

## Provider skeleton

```java
public final class DemoEnvironmentConfigProvider implements Jinja2EnvironmentConfigProvider {
    @Override
    public @NotNull Set<String> getEnabledExtensions(@NotNull Project project) {
        return Set.of("jinja2.ext.loopcontrols", "jinja2.ext.i18n");
    }

    @Override
    public @NotNull UndefinedBehavior getUndefinedBehavior(@NotNull Project project) {
        return UndefinedBehavior.STRICT;
    }
}
```
