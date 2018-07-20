#!/bin/bash

source "${BASH_SOURCE%/*}/flow-env.sh"

set -e -x

flow github --no-publish --output ../version/version-number -rnop ../version/notes version $ENVIRONMENT
