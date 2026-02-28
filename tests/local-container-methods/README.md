# Local Container Methods

This example verifies local built-in container typing and dict key propagation after `{% do %}`.

What is covered:

- local `dict` typing from `{% set all_servers = {} %}`
- local `list` typing from `{% set app = [] %}`
- method completion inside `{% do %}`
- validation of unknown and non-callable members
- bracket-key completion after `dict.update(source_dict)`

Manual verification steps:

1. Open `dict-method-completion.jinja` and invoke completion after `all_servers.`.
Expected result: built-in dict methods such as `update(mapping)`, `items()`, `keys()`, `values()`, `clear()`.
When a method item is selected, the plugin inserts `()` and places the caret inside the parentheses.

2. Open `list-method-completion.jinja` and invoke completion after `app.`.
Expected result: built-in list methods such as `append(value)`, `extend(iterable)`, `insert(index, value)`, `pop(index)`, `remove(value)`, `clear()`.
When a method item is selected, the plugin inserts `()` and places the caret inside the parentheses.

3. Open `dict-update-propagation.jinja` and invoke completion inside `all_servers["<caret>"]`.
Expected result: keys from `item1` are suggested.

4. Open `dict-update-propagation.jinja` and check inspections.
Expected result: `{% do all_servers.update(item1) %}` is valid and has no error.

5. Open `invalid-local-methods.jinja` and check inspections.
Expected result:
- `mutate(...)` reports `Unknown method 'mutate' on type 'dict'`
- `server1()` reports `'server1' is not callable`

6. Place the caret on `update` in `dict-update-propagation.jinja` and open quick documentation.
Expected result: documentation shows `method update(mapping)` and `Returns: None`.
