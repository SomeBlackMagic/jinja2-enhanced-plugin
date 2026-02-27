package org.example.ansible;

import com.jinja2enhanced.extensions.Jinja2LiveTemplateContextDescriptor;
import com.jinja2enhanced.extensions.Jinja2LiveTemplateContextProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class AnsibleLiveTemplateContextProvider implements Jinja2LiveTemplateContextProvider {

    @Override
    public @NotNull List<Jinja2LiveTemplateContextDescriptor> getContextTypes() {
        return List.of(
                new Jinja2LiveTemplateContextDescriptor(
                        "JINJA2_ANSIBLE_TASK",
                        "Jinja2 Ansible Task",
                        "org.example.ansible.AnsibleTaskTemplateContextType"
                )
        );
    }
}
