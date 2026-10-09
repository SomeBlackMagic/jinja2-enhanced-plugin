# Quick Fix Provider Example

`quickFixProvider` appends framework-owned `LocalQuickFix` actions to an existing Jinja
inspection problem. Register `SampleQuickFixProvider`, create a `framework-tag` diagnostic, and
verify that its action appears after built-in fixes. The provider returns no fix for other rule IDs.
