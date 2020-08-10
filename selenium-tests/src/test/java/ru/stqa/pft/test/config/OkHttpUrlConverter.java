package ru.stqa.pft.test.config;

import okhttp3.HttpUrl;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class OkHttpUrlConverter implements Converter<HttpUrl> {
    @Override
    public HttpUrl convert(Method method, String input) {
        return HttpUrl.parse(input);
    }
}
