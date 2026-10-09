package com.example;

import com.intellij.openapi.util.ModificationTracker;
import com.jinja2enhanced.extensions.Jinja2ReferenceContext;
import com.jinja2enhanced.extensions.Jinja2ReferenceKind;
import com.jinja2enhanced.extensions.Jinja2ReferenceResolver;
import com.jinja2enhanced.extensions.Jinja2ResolveResult;
import com.jinja2enhanced.extensions.Jinja2SymbolDescriptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleReferenceResolver implements Jinja2ReferenceResolver {
    @Override
    public boolean supports(@NotNull Jinja2ReferenceContext context) {
        return context.kind() == Jinja2ReferenceKind.VARIABLE
                && context.accessChain().equals(List.of("runtime_value"));
    }

    @Override
    public @NotNull Jinja2ResolveResult resolve(@NotNull Jinja2ReferenceContext context) {
        return Jinja2ResolveResult.notApplicable(); // Replace with resolved(metadataPsiElement).
    }

    @Override
    public @NotNull List<Jinja2SymbolDescriptor> variants(@NotNull Jinja2ReferenceContext context) {
        return List.of();
    }

    @Override
    public @NotNull ModificationTracker getModificationTracker(@NotNull com.intellij.openapi.project.Project project) {
        return ModificationTracker.NEVER_CHANGED;
    }
}
