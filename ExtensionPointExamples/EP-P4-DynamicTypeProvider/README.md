# Dynamic Type Provider Example

`dynamicTypeProvider` supplies members whose shape depends on framework metadata or the current
expression. Register `SampleDynamicTypeProvider`, then invoke completion after `catalog.` in
`dynamic_type_demo.jinja2`; `lookup` should be offered with its callable signature.
