PACKAGECONFIG:append = " qml gstreamer"
# Make sure that not needed entries are disabled: spatialaudio spatialaudio_quick3d
# Use gstreamer backend for QMediaPlayer in this profile.
PACKAGECONFIG:remove = "  spatialaudio spatialaudio_quick3d ffmpeg"

PTEST_ENABLED = "0"

