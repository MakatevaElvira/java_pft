package ru.stqa.pft.test.tests;

import org.junit.Before;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.test.appManager.Application;
import ru.stqa.pft.test.pages.MainPage;

public class TestBasePgOb {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @BeforeSuite(alwaysRun = true)
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }
}
