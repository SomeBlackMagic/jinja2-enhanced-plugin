# Extension Point Examples

This directory contains runnable examples and implementation skeletons for every extension point exposed by the **Jinja2 Enhanced** IntelliJ plugin.

Each sub-directory is self-contained: it includes a minimal Java/Kotlin provider, demo template files, and a `README.md` with step-by-step verification instructions.

---

## Directory Structure

```
ExtensionPointExamples/
├── EP-P1-FilterProvider/
├── EP-P1-FunctionProvider/
├── EP-P1-TestProvider/
├── EP-P1-TypeSchemaProvider/
├── EP-P1-VariableProvider/
├── EP-P1-TemplateRootProvider-smoke/
├── EP-P2-DocumentationProvider/
├── EP-P2-EnvironmentConfigProvider/
├── EP-P2-InspectionRuleProvider/
├── EP-P2-TagBehaviorProvider/
├── EP-P3-InjectionLanguageProvider/
├── EP-P3-MacroModuleProvider/
├── EP-P3-ScopeVariableProvider/
├── EP-P4-DelimiterProvider/
├── EP-P4-LiveTemplateContextProvider/
├── EP-P5-AssignmentTagProvider/
├── EP-P6-AssignmentTagProvider-FindUsages/
├── EP-P6-AssignmentTagProvider-GotoDefinition/
└── EP-P7-AssignmentTagProvider-Highlighting/
```

---

## Priority Levels

Extension points are grouped by implementation priority:

| Priority | Focus area |
|----------|-----------|
| **P1** | Core language elements — filters, functions, tests, types, variables, template roots |
| **P2** | Configuration & inspections — documentation, environment config, inspection rules, custom tags |
| **P3** | Advanced features — language injection, virtual macro modules, scope variables |
| **P4** | Advanced configuration — custom delimiters, live template contexts |
| **P5–P7** | Custom assignment tags — declaration, find usages, goto definition, highlighting |

---

## P1 — Core Language Elements

### FilterProvider
**Directory:** `EP-P1-FilterProvider/`

Registers custom Jinja2 filters (`slugify`, `to_yaml`, `highlight`) so the IDE provides completion, documentation, and type information for them.

Key files:
- `SampleFilterProvider.java` — implements `Jinja2FilterProvider`
- `filter_demo.jinja2` — usage examples for all three filters

### FunctionProvider
**Directory:** `EP-P1-FunctionProvider/`

Exposes globally callable functions (`url_for`, `get_flashed_messages`, `csrf_token`) with parameter signatures and return types.

Key files:
- `SampleFunctionProvider.java` — implements `Jinja2FunctionProvider`
- `function_demo.jinja2` — function calls with arguments

### TestProvider
**Directory:** `EP-P1-TestProvider/`

Adds boolean predicates used in `is` expressions (`email`, `url`, `uuid`).

Key files:
- `SampleTestProvider.java` — implements `Jinja2TestProvider`
- `test_demo.jinja2` — `{% if value is email %}` style examples, including negation

### TypeSchemaProvider
**Directory:** `EP-P1-TypeSchemaProvider/`

Provides structured type schemas so the IDE can resolve attribute chains (e.g. `current_user.address.city`) and warn on unknown attributes.

Key files:
- `UserTypeSchemaProvider.java` — `User` and `Address` type definitions
- `current_user-valid.jinja` — valid attribute access
- `current_user-invalid.jinja` — unknown attribute (should show a warning)
- `current_user-unknown-tail.jinja` — unknown tail on a nullable field
- `EP-P1-TypeSchemaProvider-smoke.md` — full verification guide

### VariableProvider
**Directory:** `EP-P1-VariableProvider/`

Injects global template variables (`app`, `g`, `config`) with their types, making them available for completion everywhere.

Key files:
- `SampleVariableProvider.java` — implements `Jinja2VariableProvider`
- `variable_demo.jinja2` — `app.debug`, `g.current_user`, `config.ENV`

### TemplateRootProvider *(smoke)*
**Directory:** `EP-P1-TemplateRootProvider-smoke/`

Demonstrates multi-root template resolution across a multi-app project layout, allowing `{% extends %}` and `{% include %}` to resolve paths correctly.

Key files:
- `project/app1/templates/base.html` — base template with content block
- `project/app1/templates/partials/header.html` — reusable partial
- `project/app2/templates/macros/forms.jinja2` — shared macro
- `project/views/page.jinja2` — page that extends, includes, and imports from other roots

---

## P2 — Configuration & Inspections

### DocumentationProvider
**Directory:** `EP-P2-DocumentationProvider/`

Controls what appears in the **Ctrl+Q** quick documentation popup for filters, functions, and tests. Custom providers can override or supplement built-in docs.

Key files:
- `documentation-demo.jinja2` / `documentation-demo.html` — Ctrl+Q targets

### EnvironmentConfigProvider
**Directory:** `EP-P2-EnvironmentConfigProvider/`

Configures Jinja2 environment flags (`loopcontrols`, `i18n`, `strict_undefined`) on a per-project or per-directory basis.

Key files:
- `templates/strict_undefined.jinja2` — undefined variable usage
- `templates/loopcontrols_disabled.jinja2` — `break`/`continue` statements
- `templates/i18n_disabled.jinja2` — `trans` block

### InspectionRuleProvider
**Directory:** `EP-P2-InspectionRuleProvider/`

Suppresses specific inspection warnings via whitelists (e.g. allow `ansible_*` variables or `safe_attr` attributes without warnings).

Key files:
- `ansible_whitelist.jinja2` — `ansible_facts` (whitelisted) vs `missing_custom` (warned)
- `attribute_whitelist.html` — `data.safe_attr` (whitelisted) vs `data.risky_attr` (warned)

### TagBehaviorProvider
**Directory:** `EP-P2-TagBehaviorProvider/`

Declares custom tags and their parsing mode: block tags that require a closing counterpart (`cache`, `compress`) or inline/self-closing tags (`cache_bust`).

Key files:
- `SampleTagBehaviorProvider.java` — tag declarations with closing aliases
- `tag_behavior_demo.jinja2` — nested block and inline tag examples

---

## P3 — Advanced Features

### InjectionLanguageProvider
**Directory:** `EP-P3-InjectionLanguageProvider/`

Enables Jinja2 syntax highlighting and completion inside host languages (HTML, YAML) based on file patterns or heuristics.

Key files:
- `SampleInjectionProvider.java` — rules for `**/tasks/*.yml` and HTML
- `example.html` / `example.yml` — injection targets

### MacroModuleProvider
**Directory:** `EP-P3-MacroModuleProvider/`

Registers virtual macro modules that don't exist on disk, allowing `{% from 'bootstrap5/form.html' import render_field %}` to resolve with full completion.

Key files:
- `SampleMacroModuleProvider.java` — virtual module with `render_field`, `render_form`, `render_vform`
- `sample_usage.jinja2` — import and usage
- `bootstrap5/form.html` — real template counterpart

### ScopeVariableProvider
**Directory:** `EP-P3-ScopeVariableProvider/`

Injects context-sensitive variables that are only valid in specific scopes: `loop.*` inside `for` blocks, `caller()` / `varargs` / `kwargs` inside macros.

Key files:
- `scope_loop_demo.jinja2` — `loop.index`, `loop.index0`, unknown attribute warning
- `scope_macro_demo.html` — `caller()`, `varargs`, `kwargs`

---

## P4 — Advanced Configuration

### DelimiterProvider
**Directory:** `EP-P4-DelimiterProvider/`

Overrides the default `{% %}`, `{{ }}`, `{# #}` delimiters with custom ones (e.g. `[% %]`, `[[ ]]`, `[# #]` for Salt SLS files).

Key files:
- `standard_template.jinja` — standard delimiter usage
- `custom_template.sls` — custom delimiter usage

### LiveTemplateContextProvider
**Directory:** `EP-P4-LiveTemplateContextProvider/`

Contributes a named live-template context (`JINJA2_ANSIBLE_TASK`) so framework-specific snippets are only suggested in appropriate files.

Key files:
- `java/AnsibleLiveTemplateContextProvider.java`
- `java/AnsibleTaskTemplateContextType.java`
- `resources/liveTemplates/AnsibleJinja2.xml` — `aitem` and `afacts` snippets
- `templates/ansible_task.yml` — test file where snippets should activate

---

## P5–P7 — Assignment Tag Extension Points

These examples build on each other. Start with **P5** and add capabilities incrementally.

### P5 — AssignmentTagProvider
**Directory:** `EP-P5-AssignmentTagProvider/`

Declares a custom assignment tag (`import_yaml`) that binds a variable name to structured YAML content, with support for flat and nested schemas.

Key files:
- `basic-import-yaml.jinja` — flat schema (`host`, `port`)
- `nested-import-yaml.jinja` — nested schema (`mysql.host`, `redis.host`)
- `defaults-flat.yml` / `defaults-nested.yml` — backing YAML files

### P6 — FindUsages
**Directory:** `EP-P6-AssignmentTagProvider-FindUsages/`

Enables **Alt+F7** to find all usages of a variable declared by a custom assignment tag across the project.

Key files:
- `basic-import-yaml.jinja` — declaration and downstream usages

### P6 — GotoDefinition
**Directory:** `EP-P6-AssignmentTagProvider-GotoDefinition/`

Enables **Ctrl+B** to navigate from a variable usage back to its declaration inside a custom assignment tag.

Key files:
- `basic-import-yaml.jinja` — declaration and usage

### P7 — Highlighting
**Directory:** `EP-P7-AssignmentTagProvider-Highlighting/`

Applies semantic keyword highlighting to the `as` token inside a custom assignment tag, making it visually consistent with built-in Jinja2 keywords.

Key files:
- `basic-import-yaml.jinja` — the `as` token to be highlighted

---

## How to Use These Examples

1. **Pick the extension point** you want to implement and open the corresponding directory.
2. **Read the `README.md`** inside that directory — it lists the exact verification steps in the IDE.
3. **Copy the provider skeleton** (Java file) into your plugin's source tree and register it in `plugin.xml`.
4. **Open the demo template files** in an IDE instance running your plugin to verify the behaviour live.

> Each example is intentionally minimal and self-contained. They demonstrate the API surface only — production implementations will add error handling, localization, and richer type information as needed.
