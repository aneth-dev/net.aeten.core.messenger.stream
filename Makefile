SOURCE_VERSION = 1.7
JFLAGS ?= -g:source,lines,vars -encoding utf8
PROCESSOR_FACTORIES_MODULES ?= net.aeten.core
TOUCH_DIR = .touch


all: compile jar eclipse src

# Sources
SRC = messenger.stream
src: $(SRC)
messenger.stream:: aeten.core messenger stream

# COTS
COTS = aeten.core messenger stream jcip.annotations slf4j
cots: $(COTS)
aeten.core::       jcip.annotations slf4j
messenger::        aeten.core slf4j
stream::           aeten.core
jcip.annotations::
slf4j::

clean:
	$(RM) -rf $(BUILD_DIR) $(DIST_DIR) $(GENERATED_DIR) $(TOUCH_DIR)

SRC_DIRS = src/
MODULES = $(SRC) $(COTS)
include Java-make/java.mk

