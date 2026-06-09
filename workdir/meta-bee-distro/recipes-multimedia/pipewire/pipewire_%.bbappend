PACKAGECONFIG:append = " systemd systemd-system-service"

SYSTEMD_AUTO_ENABLE:${PN} = "enable"

# Keep ALSA PipeWire conf symlinks in the pipewire-alsa package.
FILES:${PN}-alsa:append = " \
    ${sysconfdir}/alsa/conf.d/50-pipewire.conf \
    ${sysconfdir}/alsa/conf.d/99-pipewire-default.conf \
"

# Ensure ALSA picks up PipeWire plugin config from /etc/alsa/conf.d.
do_install:append() {
    install -d ${D}${sysconfdir}/alsa/conf.d
    ln -sf ${datadir}/alsa/alsa.conf.d/50-pipewire.conf ${D}${sysconfdir}/alsa/conf.d/50-pipewire.conf
    ln -sf ${datadir}/alsa/alsa.conf.d/99-pipewire-default.conf ${D}${sysconfdir}/alsa/conf.d/99-pipewire-default.conf
}
