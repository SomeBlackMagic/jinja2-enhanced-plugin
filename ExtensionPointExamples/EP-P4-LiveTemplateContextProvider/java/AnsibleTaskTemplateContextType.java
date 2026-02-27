package org.example.ansible;

import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public final class AnsibleTaskTemplateContextType extends TemplateContextType {

    public AnsibleTaskTemplateContextType() {
        super("JINJA2_ANSIBLE_TASK", "Jinja2 Ansible Task");
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext context) {
        PsiFile file = context.getFile();
        if (!isAnsibleYaml(file)) {
            return false;
        }

        // Keep this logic framework-side: detect only Jinja2-injected ranges in task values.
        return isInsideInjectedJinja(context);
    }

    private boolean isAnsibleYaml(@NotNull PsiFile file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".yml") || name.endsWith(".yaml");
    }

    private boolean isInsideInjectedJinja(@NotNull TemplateActionContext context) {
        TextRange range = context.getSurroundingTextRange();
        if (range == null) {
            return false;
        }
        return range.getLength() > 0;
    }
}
