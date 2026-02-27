package com.example;

import com.jinja2enhanced.extensions.Jinja2FunctionDescriptor;
import com.jinja2enhanced.extensions.Jinja2FunctionProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleFunctionProvider implements Jinja2FunctionProvider {

    @Override
    public @NotNull List<Jinja2FunctionDescriptor> getFunctions() {
        return List.of(
                new Jinja2FunctionDescriptor("url_for",
                        "Generate a URL for the given endpoint and values."),
                new Jinja2FunctionDescriptor("get_flashed_messages",
                        "Return all flash messages queued for the current request."),
                new Jinja2FunctionDescriptor("csrf_token",
                        "Return the CSRF token for the current session.")
        );
    }
}
