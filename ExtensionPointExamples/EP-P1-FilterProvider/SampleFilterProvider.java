package com.example;

import com.jinja2enhanced.extensions.Jinja2FilterDescriptor;
import com.jinja2enhanced.extensions.Jinja2FilterProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleFilterProvider implements Jinja2FilterProvider {

    @Override
    public @NotNull List<Jinja2FilterDescriptor> getFilters() {
        return List.of(
                new Jinja2FilterDescriptor("slugify",   "Convert a string to a URL-friendly slug."),
                new Jinja2FilterDescriptor("to_yaml",   "Serialize a value to YAML format."),
                new Jinja2FilterDescriptor("highlight", "Apply syntax highlighting to a code snippet.")
        );
    }
}
