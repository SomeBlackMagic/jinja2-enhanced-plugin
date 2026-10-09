# Completion Contributor Example

This example covers context-specific variants contributed through `completionContributor`.

Register `SampleCompletionContributor`, open `completion_demo.jinja2`, and invoke completion
after `|`. The `framework_slugify` lookup item should be added without removing built-in filters.
Providers must not retain PSI or perform blocking work from `contribute`.
