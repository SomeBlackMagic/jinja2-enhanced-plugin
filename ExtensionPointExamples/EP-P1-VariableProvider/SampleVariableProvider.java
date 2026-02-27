package com.example;

import com.intellij.psi.PsiElement;
import com.jinja2enhanced.extensions.Jinja2VariableDescriptor;
import com.jinja2enhanced.extensions.Jinja2VariableProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleVariableProvider implements Jinja2VariableProvider {

    @Override
    public @NotNull List<Jinja2VariableDescriptor> getVariables(@NotNull PsiElement position) {
        return List.of(
                new Jinja2VariableDescriptor("app", "The Flask/WSGI application instance.", List.of(
                        new Jinja2VariableDescriptor("name",    "Application name."),
                        new Jinja2VariableDescriptor("version", "Application version string."),
                        new Jinja2VariableDescriptor("debug",   "True when running in debug mode.")
                )),
                new Jinja2VariableDescriptor("g",      "Request-global proxy object for sharing data."),
                new Jinja2VariableDescriptor("config", "Application configuration mapping.")
        );
    }
}
