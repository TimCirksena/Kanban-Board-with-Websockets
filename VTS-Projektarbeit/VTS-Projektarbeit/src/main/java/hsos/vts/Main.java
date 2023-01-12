package hsos.vts;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

import java.io.IOException;

@QuarkusMain
public class Main {
    public static EmbeddedPostgres pg;
    public static void main(String ... args) {
        System.out.println("Running main method");
        try {
            pg = EmbeddedPostgres.builder().setPort(6972).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            pg.close();
            return 0;
        }
    }
}
