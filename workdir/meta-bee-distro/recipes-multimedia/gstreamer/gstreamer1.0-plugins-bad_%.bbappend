# Headless profile: no X11/Wayland windowing system available.
# Disable the Vulkan plugin to avoid Meson configure failure in vulkansink.
PACKAGECONFIG:remove = " vulkan"
