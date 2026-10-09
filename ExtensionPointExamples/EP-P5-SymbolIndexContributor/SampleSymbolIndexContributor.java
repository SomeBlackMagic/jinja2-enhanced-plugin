package com.example;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPointerManager;
import com.jinja2enhanced.extensions.Jinja2FileIndexContext;
import com.jinja2enhanced.extensions.Jinja2IndexedSymbol;
import com.jinja2enhanced.extensions.Jinja2SymbolIndexContributor;
import com.jinja2enhanced.extensions.Jinja2SymbolKind;
import com.jinja2enhanced.extensions.Jinja2SymbolVisibility;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class SampleSymbolIndexContributor implements Jinja2SymbolIndexContributor {
    @Override
    public @NotNull Collection<Jinja2IndexedSymbol> index(@NotNull PsiFile file,
                                                            @NotNull Jinja2FileIndexContext context) {
        PsiElement name = findComponentName(file);
        if (name == null) return List.of();
        return List.of(new Jinja2IndexedSymbol(
                name.getText(),
                Jinja2SymbolKind.FRAMEWORK_EXPORT,
                Jinja2SymbolVisibility.PUBLIC,
                name.getTextRange(),
                SmartPointerManager.getInstance(file.getProject()).createSmartPsiElementPointer(name),
                context.templateIdentity()
        ));
    }

    private PsiElement findComponentName(PsiFile file) {
        return null; // Locate the framework declaration without I/O or index writes.
    }
}
