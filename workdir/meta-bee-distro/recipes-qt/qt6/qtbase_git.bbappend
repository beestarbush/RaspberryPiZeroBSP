PACKAGECONFIG:append = " eglfs"

# Additional configuration options.

PACKAGECONFIG:append = " eglfs gles2 kms gbm fontconfig"

PACKAGECONFIG_DEFAULT:remove = " tests xkbcommon"
PACKAGECONFIG:remove = "xkbcommon xcb gl"

RDEPENDS:${PN} += " \
	fontconfig \
	dbus \
	freetype \
	jpeg \
	libpng \
	zlib \
"

PTEST_ENABLED = "0"

OE_QTBASE_EGLFS_DEVICE_INTEGRATION:rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', 'eglfs_brcm', d)}"

do_configure:prepend:rpi() {
    # Add the appropriate EGLFS_DEVICE_INTEGRATION
    if [ "${@d.getVar('OE_QTBASE_EGLFS_DEVICE_INTEGRATION')}" != "" ]; then
        echo "EGLFS_DEVICE_INTEGRATION = ${OE_QTBASE_EGLFS_DEVICE_INTEGRATION}" >> ${S}/mkspecs/oe-device-extra.pri
    fi
}
