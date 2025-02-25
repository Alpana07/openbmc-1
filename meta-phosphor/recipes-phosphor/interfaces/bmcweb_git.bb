inherit systemd
inherit useradd

USERADD_PACKAGES = "${PN}"

# add a user called httpd for the server to assume
USERADD_PARAM_${PN} = "-r -s /usr/sbin/nologin bmcweb"
GROUPADD_PARAM_${PN} = "web; redfish"
# Add root user to web & redfish group
GROUPMEMS_PARAM_${PN} = "-g web -a root; -g redfish -a root"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=a6a4edad4aed50f39a66d098d74b265b"

SRC_URI = "git://github.com/ibm-openbmc/bmcweb.git;branch=OP940"

PV = "1.0+git${SRCPV}"
SRCREV = "d14a2ecfb4b79875d4b572f03d533a4f17c20057"

S = "${WORKDIR}/git"

DEPENDS = "openssl zlib boost libpam sdbusplus gtest nlohmann-json libtinyxml2 "

RDEPENDS_${PN} += "jsnbd"

FILES_${PN} += "${datadir}/** "

inherit cmake

EXTRA_OECMAKE = "-DBMCWEB_BUILD_UT=OFF -DYOCTO_DEPENDENCIES=ON" 

SYSTEMD_SERVICE_${PN} += "bmcweb.service bmcweb.socket"

FULL_OPTIMIZATION = "-Os -pipe "
