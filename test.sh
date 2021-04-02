#!/bin/bash

set -eu

curl http://localhost:8080/intake --data-raw '{"type": "mega-action", "name": "oli"}' -H 'Content-Type: application/json'

curl http://localhost:8080/intake --data-raw '{"type": "giga-action", "age": "18"}' -H 'Content-Type: application/json'

curl http://localhost:8080/intake --data-raw '{"type": "peta-action", "street": "wall st.", "zip": 3489}' -H 'Content-Type: application/json'
