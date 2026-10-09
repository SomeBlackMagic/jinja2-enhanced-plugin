# Reference Resolver Example

`referenceResolver` resolves framework metadata without duplicating Jinja PSI traversal. Register
`SampleReferenceResolver`, open `reference_demo.jinja2`, and use Ctrl+B on `runtime_value`.
The actual provider must return a valid navigable PSI target from framework metadata.
