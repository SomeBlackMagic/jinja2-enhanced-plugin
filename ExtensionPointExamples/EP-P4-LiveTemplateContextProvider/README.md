# Live Template Context Provider Example

This example shows how an external framework plugin can contribute a custom Jinja2 live template context
through `com.jinja2enhanced.liveTemplateContextProvider`.

## Goal

Enable framework-specific live templates only in files where framework-specific Jinja2 injection is active.

## Files

- `java/AnsibleLiveTemplateContextProvider.java`: provider that contributes `JINJA2_ANSIBLE_TASK`.
- `java/AnsibleTaskTemplateContextType.java`: IntelliJ `TemplateContextType` implementation.
- `resources/liveTemplates/AnsibleJinja2.xml`: framework live templates bound to `JINJA2_ANSIBLE_TASK`.
- `templates/ansible_task.yml`: host YAML file where the framework context should be active.
- `templates/snippets.jinja2`: plain Jinja2 file for negative check.

## Manual Verification Steps

1. Build and run IDE with both plugins installed:
   - `jinja2-enhanced-plugin` (this plugin)
   - external framework plugin that includes classes from `java/` and `resources/`.
2. Open `templates/ansible_task.yml`.
3. Put caret inside `{{ ... }}` or `{% ... %}` region in the task value.
4. Trigger Live Templates (`Ctrl+J`) and verify templates from `AnsibleJinja2.xml` are available.
5. Open `templates/snippets.jinja2`.
6. Trigger Live Templates and verify the framework templates are not offered unless context logic allows it.

## Expected Result

- Templates with `<option name="JINJA2_ANSIBLE_TASK" value="true"/>` appear only in framework-specific Jinja2 locations.
- Built-in templates from `liveTemplates/Jinja2.xml` continue to work unchanged.
