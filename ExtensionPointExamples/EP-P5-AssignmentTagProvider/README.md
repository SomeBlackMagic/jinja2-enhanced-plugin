# Assignment Tag Provider Example

## Goal

Verify that a custom assignment tag registered via `assignmentTagProvider`
adds variables to scope-based inspections and completion, including nested
structures returned by `resolveType(...)`.

## Preconditions

1. Register a test provider that contributes the `import_yaml` assignment tag.
2. Configure the provider to expose either flat fields (`host`, `port`) or a
   nested structure such as `mysql.host`, `mysql.port`, and `redis.host`.

## Registration Example

### plugin.xml

```xml
<idea-plugin>
    <depends>com.jinja2enhanced</depends>

    <extensions defaultExtensionNs="com.jinja2enhanced">
        <assignmentTagProvider implementation="org.example.jinja.ExampleAssignmentTagProvider"/>
    </extensions>
</idea-plugin>
```

### Java

```java
package org.example.jinja;

import com.intellij.openapi.project.Project;
import com.jinja2enhanced.extensions.Jinja2AssignmentTagDescriptor;
import com.jinja2enhanced.extensions.Jinja2AssignmentTagProvider;
import com.jinja2enhanced.extensions.Jinja2TypeField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ExampleAssignmentTagProvider implements Jinja2AssignmentTagProvider {

    @Override
    public @NotNull List<Jinja2AssignmentTagDescriptor> getAssignmentTags() {
        return List.of(
                new Jinja2AssignmentTagDescriptor(
                        "import_yaml",
                        Jinja2AssignmentTagDescriptor.AssignmentForm.INLINE,
                        null,
                        "Imports YAML into a bound variable.",
                        List.of(
                                Jinja2TypeField.field("host", "Host name.", null),
                                Jinja2TypeField.field("port", "Port number.", null)
                        )
                )
        );
    }

    @Override
    public @Nullable List<Jinja2TypeField> resolveType(@NotNull String tagName,
                                                       @NotNull List<String> rawArgs,
                                                       @NotNull Project project) {
        if (!"import_yaml".equals(tagName)) {
            return null;
        }

        return List.of(
                Jinja2TypeField.field(
                        "mysql",
                        "MySQL config.",
                        "dict",
                        List.of(
                                Jinja2TypeField.field("host", "MySQL host.", "String"),
                                Jinja2TypeField.field("port", "MySQL port.", "Integer")
                        )
                ),
                Jinja2TypeField.field(
                        "redis",
                        "Redis config.",
                        "dict",
                        List.of(
                                Jinja2TypeField.field("host", "Redis host.", "String")
                        )
                )
        );
    }
}
```

## Notes About the Example

- `getAssignmentTags()` registers the tag name and a static fallback schema.
- `resolveType()` is optional and can return fields dynamically based on `rawArgs`.
- Nested children are declared via `Jinja2TypeField.field(name, description, type, children)`.
- Returned nested fields are available in scope completion and undefined-attribute inspection.
- For block tags, use `AssignmentForm.BLOCK` and optionally specify `endTagName`.

## Example Files

- `basic-import-yaml.jinja`: verifies that the bound variable and flat members are visible.
- `nested-import-yaml.jinja`: verifies nested completion and nested undefined-attribute checks.
- `defaults-flat.yml`: sample flat YAML shape.
- `defaults-nested.yml`: sample nested YAML shape.

## Verification Steps

### Flat Example

1. Open `basic-import-yaml.jinja`.
2. Place the caret on `imported_yaml` inside `{{ imported_yaml }}`.
3. Confirm that no `Undefined variable` warning is shown.
4. Trigger completion after `imported_yaml.`.
5. Confirm that `host` and `port` are suggested.

### Nested Example

1. Open `nested-import-yaml.jinja`.
2. Trigger completion after `imported_yaml.`.
3. Confirm that `mysql` and `redis` are suggested.
4. Trigger completion after `imported_yaml.mysql.`.
5. Confirm that `host` and `port` are suggested.
6. Confirm that `{{ imported_yaml.mysql.host }}` is valid.
7. Confirm that `{{ imported_yaml.mysql.missing }}` is reported as an undefined attribute.

## Notes

- This example is intentionally generic and does not depend on Salt, Ansible, or any other framework.
- The tag body is treated as a custom statement; variable semantics come only from the registered EP.
