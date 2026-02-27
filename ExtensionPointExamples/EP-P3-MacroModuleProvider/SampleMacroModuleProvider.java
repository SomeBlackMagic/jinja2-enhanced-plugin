package com.example;

import com.intellij.openapi.project.Project;
import com.jinja2enhanced.extensions.Jinja2MacroDescriptor;
import com.jinja2enhanced.extensions.Jinja2MacroModule;
import com.jinja2enhanced.extensions.Jinja2MacroModuleProvider;
import com.jinja2enhanced.extensions.Jinja2MacroParameterDescriptor;
import com.jinja2enhanced.extensions.Jinja2VariableDescriptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class SampleMacroModuleProvider implements Jinja2MacroModuleProvider {

    @Override
    public @NotNull Map<String, Jinja2MacroModule> getModules(@NotNull Project project) {
        Jinja2MacroModule bootstrapModule = new Jinja2MacroModule(
                "bootstrap5/form.html",
                "Virtual bootstrap form helper module.",
                List.of(
                        new Jinja2MacroDescriptor(
                                "render_field",
                                "Render a single field.",
                                List.of(new Jinja2MacroParameterDescriptor("field", null, "Field instance."))
                        ),
                        new Jinja2MacroDescriptor(
                                "render_form",
                                "Render a complete form.",
                                List.of(new Jinja2MacroParameterDescriptor("form", null, "Form instance."))
                        ),
                        new Jinja2MacroDescriptor(
                                "render_vform",
                                "Render a vertical form layout.",
                                List.of(new Jinja2MacroParameterDescriptor("form", null, "Form instance."))
                        )
                ),
                List.of(
                        new Jinja2VariableDescriptor("theme", "Current bootstrap theme.")
                )
        );

        return Map.of("bootstrap5/form.html", bootstrapModule);
    }
}
