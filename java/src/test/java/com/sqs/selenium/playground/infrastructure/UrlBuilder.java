package com.sqs.selenium.playground.infrastructure;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilder {

    private final StringBuilder builder;

    private String protocol;
    private String host;
    private String path;

    private final int DEFAULT_PORT = -1;
    private int port = DEFAULT_PORT;

    private UrlBuilder() {
        builder = new StringBuilder();
    }

    public static UrlBuilder empty() {
        return new UrlBuilder();
    }

    public UrlBuilder withProtocol(String protocol) {
        if (protocol == null || protocol.length() == 0) {
            throw new IllegalArgumentException("Protocol cannot be null or empty!");
        }
        this.protocol = protocol;
        return this;
    }

    public UrlBuilder withHost(String host) {
        if (host != null && host.contains(":")) {
            String[] parts = host.split(":");
            if (parts.length > 2) {
                throw new IllegalArgumentException("Host contains more than one colon: " + host);
            }
            try {
                withPort(Integer.parseInt(parts[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Could not parse port out of host: " + host);
            }
            host = parts[0];
        }
        this.host = host;
        return this;
    }

    public UrlBuilder withPath(String path) {
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        this.path = path;
        return this;
    }

    public UrlBuilder withPort(int port) {
        this.port = port;
        return this;
    }

    public URL build() throws MalformedURLException {
        builder.append(protocol).append("://");

        if (host != null) {
            builder.append(host).append(".com");
        }

        if (port != DEFAULT_PORT) {
            builder.append(":").append(port);
        }

        if (path != null && !"".equals(path)) {
            builder.append("/").append(path);
        }

        return new URL(builder.toString());
    }
}