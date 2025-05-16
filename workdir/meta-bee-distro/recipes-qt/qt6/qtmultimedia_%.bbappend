PACKAGECONFIG:append = "  qml pulseaudio"
# Make sure that not needed entries are disabled: spatialaudio spatialaudio_quick3d
# Remove gstreamer as it contains CVE's and is not needed to just play sounds
PACKAGECONFIG:remove = "  spatialaudio spatialaudio_quick3d ffmpeg gstreamer"

PTEST_ENABLED = "0"

