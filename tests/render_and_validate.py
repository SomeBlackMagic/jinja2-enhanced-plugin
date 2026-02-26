#!/usr/bin/env python3
"""
Jinja 3.1.6 validation runner.

Usage:
  python render_and_validate.py path/to/template.jinja

- Direct template path
- Enables i18n extension
- StrictUndefined
- Fails on any syntax or runtime error
"""

from __future__ import annotations

import sys
import traceback
from pathlib import Path
from typing import Any

from jinja2 import Environment, FileSystemLoader, StrictUndefined
from jinja2.ext import i18n
from datetime import datetime, date
from typing import Any

def datetime_filter(value: Any, fmt: str = "%Y-%m-%d") -> str:
    return ""
    if value is None:
        return ""

    if isinstance(value, datetime):
        return value.strftime(fmt)

    if isinstance(value, date):
        return value.strftime(fmt)

    if isinstance(value, str):
        try:
            dt = datetime.fromisoformat(value)
            return dt.strftime(fmt)
        except ValueError:
            raise ValueError(f"datetime filter: invalid ISO date string: {value}")

    raise TypeError(
        f"datetime filter: unsupported type {type(value).__name__}"
    )

# ----------------------------------------------------------------------
# Dummy gettext implementation (no external gettext required)
# ----------------------------------------------------------------------

def dummy(message: str) -> str:
    return f"[tr]"

def gettext(message: str, **variables: Any) -> str:
    return ""
    try:
        return message % variables if variables else message
    except KeyError as e:
        raise KeyError(f"Missing variable for gettext: {e}") from e


def ngettext(
    singular: str,
    plural: str,
    n: int,
    **variables: Any,
) -> str:
    return ""
    msg = singular if n == 1 else plural
    try:
        return msg % variables if variables else msg
    except KeyError as e:
        raise KeyError(f"Missing variable for ngettext: {e}") from e


def pgettext(
    context: str,
    message: str,
    **variables: Any,
) -> str:
    return ""
    try:
        formatted = message % variables if variables else message
        return f"[tr:{context}]{formatted}"
    except KeyError as e:
        raise KeyError(f"Missing variable for pgettext: {e}") from e


def npgettext(
    context: str,
    singular: str,
    plural: str,
    n: int,
    **variables: Any,
) -> str:
    msg = singular if n == 1 else plural
    return ""
    try:
        formatted = msg % variables if variables else msg
        return f"[tr:{context}]{formatted}"
    except KeyError as e:
        raise KeyError(f"Missing variable for npgettext: {e}") from e
# ----------------------------------------------------------------------
# Build Jinja environment
# ----------------------------------------------------------------------

def build_env(templates_dir: Path) -> Environment:
    env = Environment(
        loader=FileSystemLoader(str(templates_dir)),
        undefined=StrictUndefined,
        autoescape=False,
        extensions=[i18n],
    )

    env.install_gettext_callables(
        gettext=gettext,
        ngettext=ngettext,
        newstyle=True,
    )

    # Context-aware gettext (Jinja 3.1+)
    env.globals["pgettext"] = pgettext
    env.globals["npgettext"] = npgettext

    return env


# ----------------------------------------------------------------------
# Render & validate
# ----------------------------------------------------------------------

def render_template(template_path: Path) -> str:
    env = build_env(template_path.parent)
    env.filters["datetime"] = datetime_filter
    env.filters["currency"] = dummy
    env.filters["date"] = dummy
    env.filters["timeago"] = dummy

    template = env.get_template(template_path.name)

    context: dict[str, Any] = {
        "value": "VALUE",
        "condition": True,
        "empty": [],
        "empty_seq": [],
        "count": 2,
        "items": [],
        "users": [],
        "notifications": [],
        "selected_items": [],
        "messages": [],
        "errors": {
            "email": "",
            "password": ""
        },
        "cart_items": [],
        "files": [],
        "basket": "sda",
        "record_count": 1,
        "album_count": 1,
        "group_count": 1,
        "store_count": 1,
        "user_count": 1,
        "apple_count": 1,
        "item_count": 1,
        "cart_size": 1,
        "username": "asdsd",
        "visitor": "asdsd",
        "message_context": "2wq",

        "current_user": {
            "email": "",
        },
        "author": {
            "email": "",
        },

        "book": {
            "email": "",
        },
        "order": {
            "is_pending": True,
        },
        "user": {
            "name": "",
            "is_active": True,
            "post_count": 1,
            "follower_count": 1
        },
        "is_home": True,
        "product": {
            "name": "",
            "price": 0.0,
        },
        "cart": {
            "size", 0
        },
        "tree": [
            {
                "name": "root",
                "children": [
                    {"name": "a", "children": []},
                    {"name": "b", "children": [{"name": "b1", "children": []}]},
                ],
            }
        ],
    }

    # intentionally NOT providing some variables
    # to validate StrictUndefined + `is undefined`

    return template.render(context)


# ----------------------------------------------------------------------
# Entry point
# ----------------------------------------------------------------------

def main() -> None:
    if len(sys.argv) != 2:
        print("Usage: python render_and_validate.py <template_path>")
        sys.exit(1)

    template_path = Path(sys.argv[1]).resolve()

    if not template_path.exists():
        print(f"Template does not exist: {template_path}")
        sys.exit(1)

    try:
        print(f"Rendering template: \"{template_path}\"")
        output = render_template(template_path)

        print("=== RENDER OK ===")
        print(output)

    except Exception:
        print("=== RENDER FAILED ===")
        traceback.print_exc()
        sys.exit(2)


if __name__ == "__main__":
    main()
