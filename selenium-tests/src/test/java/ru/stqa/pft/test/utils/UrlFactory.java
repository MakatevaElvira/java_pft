package ru.stqa.pft.test.utils;

import okhttp3.HttpUrl;

import static ru.stqa.pft.test.config.EnvProperties.env;

public class UrlFactory {
    private UrlFactory() {
    }

    public static HttpUrl.Builder baseUrl() {
        return env().liteCartFrontendUrl().newBuilder();
    }

    public static HttpUrl.Builder mainUrl() {
        return baseUrl().addPathSegment("en/");
    }
}
