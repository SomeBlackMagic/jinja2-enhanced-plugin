[# This file should be parsed with custom delimiters. #]
[% set user = {'name': 'Alice', 'active': true} %]

[% if user.active %]
Hello, [[ user.name ]]!
[% else %]
Hello, anonymous user!
[% endif %]

<ul>
[% for item in ['one', 'two', 'three'] %]
  <li>[[ item ]]</li>
[% endfor %]
</ul>
