# API Config Provider Example

`apiConfigProvider` is the canonical environment-configuration EP. Register a provider that
applies to files under `roles/`, then open `roles/example.sls`. Its delimiters and enabled
extensions should be resolved through `Jinja2EnvironmentConfigRegistry.getConfig(project, file)`.

The existing `EP-P2-EnvironmentConfigProvider` directory is retained as a legacy smoke scenario;
it is not the canonical EP name.
