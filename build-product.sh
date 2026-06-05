#!/bin/sh
set -eu

SCRIPT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
BSP_WORKDIR="${SCRIPT_DIR}/workdir"

docker run --rm -v "${BSP_WORKDIR}:/workdir" bspbuilder --workdir=/workdir ./build.sh
