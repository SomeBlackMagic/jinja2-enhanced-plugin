# Jinja2 Enhanced Support

[![Version](https://img.shields.io/badge/version-0.0.1-blue.svg)](https://github.com/someBlackMagic/jinja2-enhanced-plugin)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

Professional-grade Jinja2 template support for JetBrains IDEs with comprehensive syntax analysis, intelligent error detection, and advanced language features.

## Features

### 🎨 Advanced Syntax Highlighting
- Full lexical analysis with **120+ token types**
- Whitespace control syntax support (`{{-`, `-%}`, `{%-`, `-}}`)
- Context-aware identifier classification (filters, tests, macros, blocks)
- Custom color schemes for Default and Darcula themes

### 💡 Intelligent Code Completion
- **Context-aware autocomplete**:
  - Filters after `|` operator
  - Tests after `is` keyword
  - Macro names after `call` keyword
  - Block names in `block`/`endblock`
- Smart insert handlers with automatic `()` insertion
- Template expansion for control structures
- 50+ built-in filters and 20+ tests

### 📐 AST-Based Code Folding
- Multiline folding for all block structures:
  - `for`/`endfor`, `if`/`elif`/`else`/`endif`
  - `macro`/`endmacro`, `block`/`endblock`
  - `call`/`endcall`, `filter`/`endfilter`
  - `with`/`endwith`, `set`/`endset`
  - `autoescape`/`endautoescape`, `raw`/`endraw`
  - Comments `{# ... #}`
- Smart placeholder text generation

### 🔍 Error Detection & Code Quality

#### Syntax Error Detection
- Advanced delimiter validation (`{{`, `}}`, `{%`, `%}`, `{#`, `#}`)
- Unclosed block detection (missing `endif`, `endfor`, etc.)
- Unclosed comment detection
- Whitespace control syntax validation
- Mismatched opening/closing tags
- Proper nesting validation

#### Semantic Analysis
- **Undefined variable detection** - warns about undeclared variables
- **Undefined macro detection** - identifies calls to non-existent macros
- **Duplicate macro names** - prevents naming conflicts
- Recognizes common context variables (`loop`, `self`, `super`, `varargs`, `kwargs`)

#### Logical Error Inspections
- **Unknown filter warnings** - validates against 56+ known filters
- **Iteration over non-iterable values** - detects `for` loops over numbers
- Type mismatch detection

#### Code Quality Inspections
- **Unused macro detection** - identifies macros that are never called
- **Unused variable warnings** - finds variables that are declared but not used
- **Unused macro parameter detection** - detects parameters not referenced in macro body

### 🎯 Full Formatting Support
- Configurable code style settings:
  - Indentation (spaces/tabs, size)
  - Spacing around operators
  - Blank lines configuration
- `Ctrl+Alt+L` (Windows/Linux) or `Cmd+Alt+L` (macOS) reformatting
- Preserves template structure

### 📚 Quick Documentation
- Press `Ctrl+Q` (Windows/Linux) or `F1` (macOS)
- Comprehensive documentation with:
  - Parameter signatures
  - Return types
  - Usage examples
- Coverage for 50+ filters and 20+ tests

### ⚡ Live Templates
Rapid development with predefined snippets:
- `for` → `{% for item in items %}...{% endfor %}`
- `if` → `{% if condition %}...{% endif %}`
- `macro` → `{% macro name(params) %}...{% endmacro %}`
- `block` → `{% block name %}...{% endblock %}`
- And more...

## Jinja2 3.1.x Coverage

### Built-in Filters (50+)
`abs`, `attr`, `batch`, `capitalize`, `center`, `default`, `dictsort`, `escape`, `filesizeformat`, `first`, `float`, `format`, `groupby`, `indent`, `int`, `join`, `last`, `length`, `list`, `lower`, `map`, `max`, `min`, `pprint`, `random`, `reject`, `rejectattr`, `replace`, `reverse`, `round`, `safe`, `select`, `selectattr`, `slice`, `sort`, `string`, `striptags`, `sum`, `title`, `tojson`, `trim`, `truncate`, `unique`, `upper`, `urlencode`, `urlize`, `wordcount`, `wordwrap`, `xmlattr`, and more

### Built-in Tests (20+)
`callable`, `defined`, `divisibleby`, `equalto`, `escaped`, `even`, `false`, `filter`, `float`, `ge`, `gt`, `iterable`, `le`, `lower`, `lt`, `mapping`, `ne`, `none`, `number`, `odd`, `sameas`, `sequence`, `string`, `test`, `true`, `undefined`, `upper`

### Global Functions
`range`, `lipsum`, `dict`, `cycler`, `joiner`, `namespace`

### Control Structures
- `for`/`endfor` - Loops with else support
- `if`/`elif`/`else`/`endif` - Conditionals
- `macro`/`endmacro` - Reusable template functions
- `block`/`endblock` - Template inheritance blocks
- `call`/`endcall` - Macro calls with caller support
- `filter`/`endfilter` - Apply filter to block content
- `set`/`endset` - Variable assignment (inline and block)
- `with`/`endwith` - Scoped context
- `autoescape`/`endautoescape` - Escape control
- `raw`/`endraw` - Raw output without processing

### Template Directives
- `extends` - Template inheritance
- `include` - Include other templates
- `import` - Import macros from other templates
- `from ... import` - Selective macro imports

### Loop Variables
`loop.index`, `loop.index0`, `loop.revindex`, `loop.revindex0`, `loop.first`, `loop.last`, `loop.length`, `loop.cycle`, `loop.depth`, `loop.depth0`, `loop.previtem`, `loop.nextitem`, `loop.changed`

## Advanced Parser Features

### Stack-Based Block Validation
- Proper nesting detection for all block types
- Ensures correct pairing of opening/closing tags
- Validates `elif`/`else` within proper `if`/`for` context

### Smart SET Detection
Automatically distinguishes between:
```jinja2
{# Inline set #}
{% set x = value %}

{# Block set #}
{% set content %}
  ...multi-line content...
{% endset %}
```

### Context-Aware Keyword Distinction
Correctly handles `if` in different contexts:
```jinja2
{# Block-level if #}
{% if condition %}...{% endif %}

{# Inline if expression #}
{% set x = value if condition else other %}
{{ item if condition else default }}
```

### Full Operator Support
- Arithmetic: `+`, `-`, `*`, `/`, `//`, `%`, `**`
- Comparison: `==`, `!=`, `<`, `>`, `<=`, `>=`
- Logical: `and`, `or`, `not`
- String concatenation: `~`
- Membership: `in`, `not in`
- Identity: `is`, `is not`

### Split Jinja2 Blocks
Handles Jinja2 in structured formats:
```yaml
# YAML with Jinja2
key: |
  {% for item in items %}
    - {{ item }}
  {% endfor %}
```

## Installation

### From JetBrains Marketplace
1. Open IDE Settings → Plugins
2. Search for "Jinja2 Enhanced Support"
3. Click Install
4. Restart IDE

## Configuration

### Language Injection Settings
**Settings → Tools → Jinja2 Language Injection**

Configure automatic Jinja2 detection:
- Enable/disable global injection
- Add file extensions (`.html`, `.yml`, `.yaml`, `.xml`, `.txt`)
- Per-file injection control

### Code Style Settings
**Settings → Editor → Code Style → Jinja2**

Customize formatting:
- **Tabs and Indents**: Indent size, continuation indent
- **Spaces**: Around operators, after commas
- **Blank Lines**: Between sections

### Context Actions
Right-click in editor:
- **Enable Jinja2 Highlighting** - Enable for current file
- **Associate with Jinja2 Enhanced** - Change file type association

## Compatibility

- **IDE Version**: 2022.3+ (build 223.0 - 253.*)
- **Supported IDEs**:
  - IntelliJ IDEA (Community, Ultimate)
  - PyCharm (Community, Professional)
  - WebStorm
  - PhpStorm
  - RubyMine
  - GoLand
  - CLion
  - All other JetBrains IDEs with Python support

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

- **Issues**: [GitHub Issues](https://github.com/someBlackMagic/jinja2-enhanced-plugin/issues)
- **Discussions**: [GitHub Discussions](https://github.com/someBlackMagic/jinja2-enhanced-plugin/discussions)

## Changelog

### Version 0.0.1 - Initial Release
- ✅ Complete Jinja2 3.1.x language support
- ✅ Stack-based parser with AST generation
- ✅ Syntax error detection with delimiter validation
- ✅ Semantic analysis (undefined variables/macros)
- ✅ Logical error inspections (unknown filters, non-iterable iteration)
- ✅ Code quality inspections (unused constructs)
- ✅ Context-aware code completion
- ✅ AST-based code folding
- ✅ Multi-host language injection
- ✅ Code formatting & style
- ✅ Quick documentation
- ✅ Dynamic extension API
- ✅ Whitespace control support
- ✅ Smart parsing (inline vs block constructs)
- ✅ Context menu actions

---

**Made with ❤️ by [SomeBlackMagic](https://github.com/someBlackMagic)**
