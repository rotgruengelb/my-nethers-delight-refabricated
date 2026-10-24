#!/usr/bin/env python3

import json
import pathlib

ROOT = pathlib.Path(__file__).resolve().parent.parent
SOURCE = ROOT / "src/main/resources/data/mynethersdelight/loot_table"
TARGET = ROOT / "versions/26.3-fabric/src/main/resources/data/mynethersdelight/loot_table"


def convert_condition(condition):
    kind = condition["condition"]
    if kind == "minecraft:block_state_property":
        return {
            "type": "minecraft:match_block",
            "blocks": condition["block"],
            "state": condition["properties"],
        }
    return {"type": kind, **convert_fields(condition, skip=("condition",))}


def convert_function(function):
    return {"type": function["function"], **convert_fields(function, skip=("function",))}


def convert_fields(node, skip=()):
    result = {}
    for key, value in node.items():
        if key in skip:
            continue
        if key == "conditions":
            result["condition"] = combine_conditions([convert_condition(c) for c in value])
        elif key == "functions":
            result["modifier"] = combine_functions([convert_function(f) for f in value])
        elif key in ("rolls", "count"):
            result[key] = to_int(value)
        else:
            result[key] = convert_value(value)
    return result


def to_int(value):
    if isinstance(value, bool):
        return value
    if isinstance(value, float) and value.is_integer():
        return int(value)
    if isinstance(value, dict) and "min" in value and "max" in value:
        # The uniform provider needs its type since 26.3.
        return {
            "type": value.get("type", "minecraft:uniform"),
            "min": to_int(value["min"]),
            "max": to_int(value["max"]),
        }
    return value


def convert_value(value):
    if isinstance(value, list):
        return [convert_value(entry) for entry in value]
    if isinstance(value, dict):
        if isinstance(value.get("condition"), str):
            return convert_condition(value)
        if isinstance(value.get("function"), str):
            return convert_function(value)
        return convert_fields(value)
    return value


def combine_conditions(conditions):
    if len(conditions) == 1:
        return conditions[0]
    return {"type": "minecraft:all_of", "terms": conditions}


def combine_functions(functions):
    if len(functions) == 1:
        return functions[0]
    return {"type": "minecraft:sequence", "functions": functions}


def main():
    count = 0
    for path in sorted(SOURCE.rglob("*.json")):
        table = json.loads(path.read_text())
        target = TARGET / path.relative_to(SOURCE)
        target.parent.mkdir(parents=True, exist_ok=True)
        target.write_text(json.dumps(convert_value(table), indent=2) + "\n")
        count += 1
    print(f"converted {count} loot tables to {TARGET}")


if __name__ == "__main__":
    main()
