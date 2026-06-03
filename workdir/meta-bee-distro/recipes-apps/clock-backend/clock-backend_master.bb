DESCRIPTION = "Clock backend service"
SUMMARY = "Clock backend"
HOMEPAGE = "https://github.com/beestarbush/clock-backend"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

inherit qt6-cmake qt6-paths systemd

DEFAULT_PREFERENCE = "-1"

PVBASE := "${PV}"

BRANCH_PATH = ""
#BRANCH = "${PVBASE}"
BRANCH = "cpp-migration"
TAG = "${BRANCH}"
#TAG = "${PVBASE}"
SRC_URI = "\
    gitsm://github.com/beestarbush/clock-backend.git;branch=${BRANCH};tag=${TAG};protocol=https \
    file://clock-backend.service \
"

S = "${WORKDIR}/git/src"
BACKEND_DATADIR = "/usr/share/bee/clock-backend"

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release"

DEPENDS += " \
    qtbase \
    qtmultimedia \
    qtwebsockets \
    qtbase-native \
"

RDEPENDS:${PN} = " \
    qtbase \
    qtbase-plugins \
    qtmultimedia \
    qtmultimedia-plugins \
    qtwebsockets \
"

FILES:${PN} += " \
    ${BACKEND_DATADIR} \
    ${systemd_unitdir}/system/clock-backend.service \
"

SYSTEMD_SERVICE:${PN} = "clock-backend.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

do_configure:prepend() {
    OUTFILE="${S}/git_version.h"

    cd ${S}

    GIT_TAG=$(git describe --tags --always --dirty 2>/dev/null || echo "unknown")
    GIT_COMMIT_HASH=$(git rev-parse HEAD 2>/dev/null || echo "unknown")
    GIT_COMMIT_HASH_SHORT=$(git rev-parse --short HEAD 2>/dev/null || echo "unknown")
    GIT_DIRTY=$(git diff --quiet || echo "-dirty")

    cat > "$OUTFILE" <<EOF
#pragma once
#define GIT_TAG "${GIT_TAG}"
#define GIT_COMMIT_HASH "${GIT_COMMIT_HASH}"
#define GIT_COMMIT_HASH_SHORT "${GIT_COMMIT_HASH_SHORT}"
#define GIT_DIRTY "${GIT_DIRTY}"
EOF

    cd -
}

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/clock-backend ${D}${bindir}/clock-backend

    install -d ${D}${BACKEND_DATADIR}
    install -m 0644 ${WORKDIR}/git/default_configuration.json ${D}${BACKEND_DATADIR}/default_configuration.json
    install -d ${D}${BACKEND_DATADIR}/media

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/clock-backend.service ${D}${systemd_unitdir}/system/clock-backend.service

    # ClockCommonLibraries (submodule) installs headers and CMake exports that
    # are only needed at build time. Remove them to avoid installed-vs-shipped
    # QA errors.
    rm -rf ${D}${includedir}
    rm -rf ${D}${libdir}/cmake
}
