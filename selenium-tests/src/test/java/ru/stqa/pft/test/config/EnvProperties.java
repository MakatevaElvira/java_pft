package ru.stqa.pft.test.config;

import okhttp3.HttpUrl;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

import static org.aeonbits.owner.Config.LoadType.MERGE;

public class EnvProperties {
        public static KrEnvironmentConfig env() {
            return ConfigCache.getOrCreate(KrEnvironmentConfig.class, System.getProperties(), System.getenv());
        }

        @Config.LoadPolicy(MERGE)
        @Config.Sources({"classpath:environment.properties.local", "classpath:environment.properties"})
        public interface KrEnvironmentConfig extends Config {
            @ConverterClass(OkHttpUrlConverter.class)
            @Key("litecart.url")
            @DefaultValue("http://localhost:8080/litecart/")
            HttpUrl liteCartFrontendUrl();

        }
    }
