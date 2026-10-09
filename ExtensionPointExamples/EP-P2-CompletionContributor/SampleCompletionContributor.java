package com.example;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jinja2enhanced.extensions.Jinja2CompletionContext;
import com.jinja2enhanced.extensions.Jinja2CompletionContributor;
import com.jinja2enhanced.extensions.Jinja2CompletionKind;
import com.jinja2enhanced.extensions.Jinja2CompletionResult;
import org.jetbrains.annotations.NotNull;

public final class SampleCompletionContributor implements Jinja2CompletionContributor {
    @Override
    public void contribute(@NotNull Jinja2CompletionContext context,
                           @NotNull Jinja2CompletionResult result) {
        if (context.kind() == Jinja2CompletionKind.FILTER) {
            result.add(LookupElementBuilder.create("framework_slugify"), 100);
        }
    }
}
