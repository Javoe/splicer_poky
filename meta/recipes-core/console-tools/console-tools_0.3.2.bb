SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.kbd;md5=9b2d91511d3d80d4d20ac6e6b0137fe9"
SUMMARY = "Allows you to set-up and manipulate the Linux console."
DESCRIPTION = "Provides tools that enable the set-up and manipulation of the linux console and console-font files."
PR = "r8"

SRC_URI = "${SOURCEFORGE_MIRROR}/lct/console-tools-${PV}.tar.gz \
           file://codepage.patch \
           file://configure.patch \
           file://compile.patch \
           file://kbdrate.patch \
           file://uclibc-fileno.patch \
           file://nodocs.patch \
           file://fix-libconsole-linking.patch \
           file://lcmessage.m4 \
           file://Makevars"

SRC_URI[md5sum] = "bf21564fc38b3af853ef724babddbacd"
SRC_URI[sha256sum] = "eea6b441672dacd251079fc85ed322e196282e0e66c16303ec64c3a2b1c126c2"

do_configure_prepend () {
	mkdir -p ${S}/m4
	cp ${WORKDIR}/lcmessage.m4 ${S}/m4/
	rm -f ${S}/acinclude.m4
	cp ${WORKDIR}/Makevars ${S}/po/
}

inherit autotools gettext update-alternatives

ALTERNATIVE_PRIORITY = "100"

bindir_progs = "chvt deallocvt fgconsole openvt"
ALTERNATIVE_${PN} = "${bindir_progs}"

RDEPENDS_${PN} = "bash"
