# Custom annotation example

## Use-case

A REST endpoint should be able to process different, non-compatible schemas, though the Java application should use
specific Java (instead of JsonNode). 

Thus, the conversion for JSON to Java DTOs needs to consider a custom logic.

## Implementation

A custom annotation defines a JsonPath string which identifies if an incoming JSON should be converted into a Java type.

## Example

Incoming JSON with just the attribute "age" should be converted into `TypeBDto`:

```
{
    "age": "18"
}
```

An incoming JSON with "type" equals "mega-action" should be convert into `TypeADto`:

```
{
    "type": "mega-action",
    "name": "oli"
}
```

...any other "type" into `TypeCDto`.

```
{
    "type": "peta-action",
    "street": "wall st.",
    "zip": 3489
}
```

The `de.oglimmer.annotationTest.logic.JsonPathFilter` defines a JsonPath string where 
a some logic in `de.oglimmer.annotationTest.rest.GenericJsonController.inferDtoType` 
and `de.oglimmer.annotationTest.rest.GenericJsonController.isJsonValidForFilter` evaluate this JsonPath.
