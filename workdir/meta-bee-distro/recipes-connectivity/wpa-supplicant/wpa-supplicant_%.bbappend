# Install wpa_supplicant configuration file

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit systemd

SRC_URI += "file://wpa_supplicant.conf"

do_install:append() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/wpa_supplicant.conf.skeleton
	rm -f ${D}${sysconfdir}/wpa_supplicant.conf
}

SYSTEMD_AUTO_ENABLE:${PN} = "enable"
