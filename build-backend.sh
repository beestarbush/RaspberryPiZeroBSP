#!/bin/sh
set -eu

SCRIPT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
BSP_WORKDIR="${SCRIPT_DIR}/workdir"

sudo docker run -it --rm -v "${BSP_WORKDIR}:/workdir" bspbuilder --workdir=/workdir ./build-backend.sh
