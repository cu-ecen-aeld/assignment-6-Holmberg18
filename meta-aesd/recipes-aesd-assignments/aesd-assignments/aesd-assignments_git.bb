# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: Set this with the path to your assignments repo. Use ssh protocol
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-Holmberg18.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "db541864829e832670bdc3b3a81653e288ed0ed9"

# This sets your staging directory based on WORKDIR
S = "${WORKDIR}/git/server"

# Add threading and real-time libraries
TARGET_LDFLAGS += "-pthread -lrt"

# Add the required library package
RDEPENDS:${PN} += "libgcc"

# Init script configuration
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"
INITSCRIPT_PARAMS:${PN} = "defaults 99"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	# Install the binary to /usr/bin
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/

	# Install the init script to /etc/init.d with correct name
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket-start-stop
}

# Specify the files to include in the package
FILES:${PN} += "${bindir}/aesdsocket"
FILES:${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"