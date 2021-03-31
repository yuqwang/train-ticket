# ts-avatar-service

Upload an image in base64 format, it will detect human face, 
then cut the face down, then return image also in base64 format.

Uses AliYun oss to store pictures.

### API

| API | Method |
| --- | --- |
| `/api/v1/avatar/` | `POST` |


##### Requests

POST http://0.0.0.0:17001/api/v1/avatar/

POST Body: base64ed image.
```
{
    "img":"......iVBORw0KGgoAAAANSUhEUgAAAYAAAAFoCAYAAABe0CxQAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAP+lSURBVHhe7L0FXJbptv6/z/+cs2vKGVtROpUSFAzEQkSxuxtbShEJsbt12ukF/i04ba1M9p7OsLLg9uWLjzfGB3buKFTW3f4tmuKzl7N0NW7BXp09ETvzm3Qt2s79OvmpTSwhw8G9+qEoX06Y3i/rhjRv5vSqIE9MHZoH4wb1hfjh/fDxJEDMGnUQEz2G4TACUMROGk4giaPxJSA0QgJGoPpUycifNpkpcjQQESFTcHM8KmYFTGNCsWM6VMRNjUQ04ImYor/OARO9MOEMUMwls85hs89cmhvDBnQFf17d0RPvr+uvi3RyacZvNu7ok1rR7Rs3gAezTR5NrdHW3......"
}
```


##### Responses

1. `400 response`

```
{"msg":"error messgae"}
```

your image uploaded is not right.


2. `500 response` 

```
{"msg":"exception info"}
```

something unexpected happen


3. `200 response`

```
{
    "address": "https:www.picturebsd.com/test.png"
}
```

