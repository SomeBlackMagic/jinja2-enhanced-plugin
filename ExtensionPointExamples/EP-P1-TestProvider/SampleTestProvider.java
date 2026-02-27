package com.example;

import com.jinja2enhanced.extensions.Jinja2TestDescriptor;
import com.jinja2enhanced.extensions.Jinja2TestProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleTestProvider implements Jinja2TestProvider {

    @Override
    public @NotNull List<Jinja2TestDescriptor> getTests() {
        return List.of(
                new Jinja2TestDescriptor("email", "Value is a valid e-mail address."),
                new Jinja2TestDescriptor("url",   "Value is a valid URL."),
                new Jinja2TestDescriptor("uuid",  "Value is a valid UUID string.")
        );
    }
}
