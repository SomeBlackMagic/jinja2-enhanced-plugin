package com.example;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.jinja2enhanced.extensions.Jinja2QuickFixContext;
import com.jinja2enhanced.extensions.Jinja2QuickFixProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleQuickFixProvider implements Jinja2QuickFixProvider {
    @Override
    public @NotNull List<LocalQuickFix> getQuickFixes(@NotNull Jinja2QuickFixContext context) {
        if (!"framework-tag".equals(context.ruleId())) return List.of();
        return List.of(new AddMissingFrameworkArgumentFix());
    }

    private static final class AddMissingFrameworkArgumentFix implements LocalQuickFix {
        @Override
        public @NotNull String getName() {
            return "Add missing framework argument";
        }

        @Override
        public @NotNull String getFamilyName() {
            return "Framework tag fixes";
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
            // Revalidate descriptor.getPsiElement(), then make the smallest write-command change.
        }
    }
}
