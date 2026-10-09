package com.example;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jinja2enhanced.extensions.Jinja2ApiConfigOverride;
import com.jinja2enhanced.extensions.Jinja2ApiConfigProvider;
import com.jinja2enhanced.extensions.Jinja2Delimiters;
import org.jetbrains.annotations.NotNull;

public final class SampleApiConfigProvider implements Jinja2ApiConfigProvider {
    @Override
    public @NotNull Jinja2ApiConfigOverride getConfigOverride(@NotNull Project project) {
        return Jinja2ApiConfigOverride.builder()
                .addExtension("jinja2.ext.do")
                .withDelimiters(new Jinja2Delimiters("[%", "%]", "[[", "]]", "[#", "#]"))
                .build();
    }

    @Override
    public boolean appliesTo(@NotNull VirtualFile file, @NotNull Project project) {
        return file.getPath().contains("/roles/");
    }
}
