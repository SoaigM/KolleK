#!/bin/bash

python -m pipreqs.pipreqs --force
export FLASK_APP=server.py
python -m flask run

