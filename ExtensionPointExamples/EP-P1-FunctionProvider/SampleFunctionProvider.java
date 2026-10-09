package com.example;

import com.jinja2enhanced.extensions.Jinja2FunctionDescriptor;
import com.jinja2enhanced.extensions.Jinja2DynamicParameter;
import com.jinja2enhanced.extensions.Jinja2FunctionProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleFunctionProvider implements Jinja2FunctionProvider {

    @Override
    public @NotNull List<Jinja2FunctionDescriptor> getFunctions() {
        return List.of(
                new Jinja2FunctionDescriptor(
                        "url_for",
                        "Generate a URL for an endpoint and optional values.",
                        List.of(
                                new Jinja2DynamicParameter("endpoint",
                                        Jinja2DynamicParameter.Kind.POSITIONAL, true, "str",
                                        "Endpoint name."),
                                new Jinja2DynamicParameter("values",
                                        Jinja2DynamicParameter.Kind.KWARG, false, "object",
                                        "Route values, such as user_id or filename.")
                        ),
                        "str",
                        "Builds a URL for an application endpoint. "
                                + "Pass route values as keyword arguments.",
                        null
                ),
                new Jinja2FunctionDescriptor(
                        "get_flashed_messages",
                        "Return flash messages queued for the current request.",
                        List.of(
                                new Jinja2DynamicParameter("with_categories",
                                        Jinja2DynamicParameter.Kind.KEYWORD, false, "bool",
                                        "Include each message category in the result."),
                                new Jinja2DynamicParameter("category_filter",
                                        Jinja2DynamicParameter.Kind.KEYWORD, false, "list[str]",
                                        "Restrict results to these categories.")
                        ),
                        "list[str]",
                        "Returns the pending flash messages. When with_categories is true, "
                                + "each item contains a category and a message.",
                        null
                ),
                new Jinja2FunctionDescriptor(
                        "csrf_token",
                        "Return the CSRF token for the current session.",
                        List.of(),
                        "str",
                        "Returns the token to include in a state-changing HTML form.",
                        null
                )
        );
    }
}
