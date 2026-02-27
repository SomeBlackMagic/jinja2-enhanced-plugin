package com.example;

import com.jinja2enhanced.extensions.Jinja2TagBehaviorProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class SampleTagBehaviorProvider implements Jinja2TagBehaviorProvider {

    @Override
    public @NotNull Collection<String> getBlockTagNames() {
        return List.of(
                "cache",     // {% cache timeout %}...{% endcache %}
                "compress"   // {% compress 'css' %}...{% endcompress %}
        );
    }

    @Override
    public @NotNull Collection<String> getInlineTagNames() {
        return List.of(
                "cache_bust" // {% cache_bust %} — self-contained inline tag
        );
    }

    @Override
    public @NotNull Map<String, String> getCustomClosingTagAliases() {
        // Both "cache" and "compress" follow the standard end+name convention,
        // so no custom aliases are needed here.
        return Collections.emptyMap();
    }
}
