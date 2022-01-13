# Fortune Cookie API

This workshop uses Spring Boot to create an API at the `/cookies` endpoint with the following properties:
- returns JSON
- If the optional `count` query param is supplied and the value of count is between 1 and 10, returns the same number of fortune cookie texts as an array.

`/cookies?count=3`

```
{
"cookies": [
  "You have a potential urge and the ability for accomplishment.",
  "Start to look for you faults if you never made mistakes.",
  "Friends long absent are coming back to you."
],
"timestamp": 1642060467983
}
```
- If the value of count is not between 1 and 10, then the API will return 400 Bad Request

`/cookies?count=12`

```
{
"error": "Count must between 1 and 10"
}
```
- if the `count` query param is not supplied, it returns a default of 1 member in the array

`/cookies`

```
{
"cookies": [
  "Three can keep a secret if you get rid of two."
],
"timestamp": 1642059942062
}
```
