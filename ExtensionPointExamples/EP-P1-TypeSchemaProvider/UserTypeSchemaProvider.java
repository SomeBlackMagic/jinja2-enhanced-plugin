package demo;

import com.jinja2enhanced.extensions.Jinja2TypeField;
import com.jinja2enhanced.extensions.Jinja2TypeSchema;
import com.jinja2enhanced.extensions.Jinja2TypeSchemaProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class UserTypeSchemaProvider implements Jinja2TypeSchemaProvider {

    @Override
    public @NotNull List<Jinja2TypeSchema> getSchemas() {
        return List.of(
                new Jinja2TypeSchema(
                        "User",
                        "Application user object.",
                        List.of(
                                new Jinja2TypeField("email", "Primary email.", null),
                                new Jinja2TypeField("username", "Display name.", null),
                                new Jinja2TypeField("address", "Postal address.", "Address"),
                                new Jinja2TypeField("profile", "External profile object.", null)
                        )
                ),
                new Jinja2TypeSchema(
                        "Address",
                        "Address value object.",
                        List.of(
                                new Jinja2TypeField("city", "City name.", null),
                                new Jinja2TypeField("zip", "Postal code.", null)
                        )
                )
        );
    }

    @Override
    public @NotNull Map<String, String> getVariableTypeBindings() {
        return Map.of("current_user", "User");
    }
}
