package com.example.jinja2.integration;

import com.jinja2enhanced.extensions.Jinja2InjectionLanguageProvider;
import com.jinja2enhanced.extensions.Jinja2InjectionRule;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Example EP implementation for automatic Jinja2 injection in host languages.
 */
public final class SampleInjectionProvider implements Jinja2InjectionLanguageProvider {

    @Override
    public @NotNull List<Jinja2InjectionRule> getInjectionRules() {
        return List.of(
                new Jinja2InjectionRule(
                        "yaml",
                        "**/tasks/*.yml",
                        Jinja2InjectionRule.InjectionMode.WHEN_DELIMITERS_PRESENT
                ),
                new Jinja2InjectionRule(
                        "HTML",
                        null,
                        Jinja2InjectionRule.InjectionMode.WHEN_DELIMITERS_PRESENT
                )
        );
    }
}
