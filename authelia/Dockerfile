FROM ghcr.io/hassio-addons/base:16.2.1
ARG AUTHELIA_VERSION="v4.39.6"

# Set shell
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

WORKDIR /app

# Set environment variables
ENV PATH="/app:${PATH}" \
    PUID=0 \
    PGID=0 \
    X_AUTHELIA_CONFIG="/config/configuration.yml" \
    AUTHELIA_SERVER_DISABLE_HEALTHCHECK=true

RUN \
	apk --no-cache add ca-certificates=20240705-r0 su-exec=0.2-r3 tzdata=2024a-r1

ARG BUILD_ARCH
RUN case ${BUILD_ARCH} in \
         "amd64")  AUTHELIA_ARCH=amd64  ;; \
         "aarch64")  AUTHELIA_ARCH=arm64  ;; \
    esac \
 && echo "Download build for : ${AUTHELIA_ARCH}" \
 && wget -q https://github.com/authelia/authelia/releases/download/${AUTHELIA_VERSION}/authelia-${AUTHELIA_VERSION}-linux-${AUTHELIA_ARCH}-musl.tar.gz \
 && tar -xzf authelia-${AUTHELIA_VERSION}-linux-${AUTHELIA_ARCH}-musl.tar.gz \
 && mv authelia-linux-${AUTHELIA_ARCH}-musl authelia \
 && chmod +x authelia

EXPOSE 9091

# Copy root filesystem
COPY rootfs /

# Build arguments
ARG BUILD_ARCH
ARG BUILD_DATE
ARG BUILD_DESCRIPTION
ARG BUILD_NAME
ARG BUILD_REF
ARG BUILD_REPOSITORY
ARG BUILD_VERSION

# Labels
LABEL \
    io.hass.name="${BUILD_NAME}" \
    io.hass.description="${BUILD_DESCRIPTION}" \
    io.hass.arch="${BUILD_ARCH}" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION} \
    maintainer="Benoit Anastay <benoit@anastay.dev>" \
    org.opencontainers.image.title="${BUILD_NAME}" \
    org.opencontainers.image.description="${BUILD_DESCRIPTION}" \
    org.opencontainers.image.vendor="Benoit Anastay" \
    org.opencontainers.image.authors="Benoit Anastay <benoit@anastay.dev>" \
    org.opencontainers.image.licenses="MIT" \
    org.opencontainers.image.url="https://anastay.dev" \
    org.opencontainers.image.source="https://github.com/${BUILD_REPOSITORY}" \
    org.opencontainers.image.documentation="https://github.com/${BUILD_REPOSITORY}/blob/main/README.md" \
    org.opencontainers.image.created=${BUILD_DATE} \
    org.opencontainers.image.revision=${BUILD_REF} \
    org.opencontainers.image.version=${BUILD_VERSION}
