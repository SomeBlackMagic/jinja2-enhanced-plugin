package com.example;

import com.jinja2enhanced.extensions.Jinja2DynamicMember;
import com.jinja2enhanced.extensions.Jinja2DynamicTypeOperation;
import com.jinja2enhanced.extensions.Jinja2DynamicTypeProvider;
import com.jinja2enhanced.extensions.Jinja2DynamicTypeQuery;
import com.jinja2enhanced.extensions.Jinja2DynamicTypeResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SampleDynamicTypeProvider implements Jinja2DynamicTypeProvider {
    @Override
    public @NotNull Jinja2DynamicTypeResult describe(@NotNull Jinja2DynamicTypeQuery query) {
        if (query.operation() != Jinja2DynamicTypeOperation.MEMBERS
                || !query.accessChain().getFirst().name().equals("catalog")) {
            return Jinja2DynamicTypeResult.notApplicable();
        }
        return Jinja2DynamicTypeResult.members(List.of(
                new Jinja2DynamicMember("lookup", "CatalogRecord", "Finds a catalog record.", null, null, true)
        ));
    }
}
