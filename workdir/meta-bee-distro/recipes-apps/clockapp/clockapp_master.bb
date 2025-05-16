DESCRIPTION = "Clock application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

inherit qt6-cmake qt6-paths

DEFAULT_PREFERENCE="-1"

PVBASE := "${PV}"

BRANCH_PATH = ""
BRANCH = "${PVBASE}"
TAG = "${PVBASE}"
SRC_URI = "git://github.com/beestarbush/ClockApp.git;branch=${BRANCH};tag=${TAG};protocol=https"

S = "${WORKDIR}/git/src"

# This is a Release build
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release"

DEPENDS += " \
    qtquick3d \ 
    libgpiod \
    qtbase \
    qtmultimedia \
    qttools \
    openssl \
    qtbase-native \
    qttools-native \
    qtquick3d \
    qtnetworkauth \ 
"

RDEPENDS:${PN} = " \
    fontconfig \
    libgpiod-tools \
    qtbase \
    qtbase-plugins \
    qtdeclarative-qmlplugins \
    qtserialport \
    qtsvg \
    qtsvg-plugins \
    openssl \
    qtmultimedia \
    qtmultimedia-plugins \
    qtquick3d-plugins \
    qtquick3d-qmlplugins \
    qtdeviceutilities \
"

SOLIBS = ".so"
SOLIBSDEV = ".so.*"
FILES:${PN} += " \
    /usr/lib/systemd \
    /etc/systemd/system \
    /usr/share/bee \
    ${libdir}/lib*${SOLIBS} \
"
FILES_SOLIBSDEV = ""
FILES:${PN}-dev = " \
    ${FILES_SOLIBSDEV} \
"

# Ability to find GIT and PkgConfig of host instead of target only.
OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

