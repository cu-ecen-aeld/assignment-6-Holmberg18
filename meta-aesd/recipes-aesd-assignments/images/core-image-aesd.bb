inherit core-image

# Add  aesd-assignments package
IMAGE_INSTALL += "aesd-assignments"

# Add SSH support
CORE_IMAGE_EXTRA_INSTALL += "openssh"

# Add base libraries
IMAGE_INSTALL += "libgcc"

# Set root password (optional but matches buildroot)
inherit extrausers
PASSWD = "\$5\$2WoxjAdaC2\$l4aj6Is.EWkD72Vt.byhM5qRtF9HcCM/5YpbxpmvNB5"
EXTRA_USERS_PARAMS = "usermod -p '${PASSWD}' root;"