package hsos.vts;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@QuarkusMain
public class Main {
    public static EmbeddedPostgres pg;
    public static void main(String ... args) {
        System.out.println("Running main method");
        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        //Path databaseFiles = Paths.get(System.getProperty("user.dir")+ "\\databaseFiles");
        try {
            pg = EmbeddedPostgres.builder().setPort(6973).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            Quarkus.waitForExit();
            pg.close();
            System.out.println("datenbank erfolgreich beendet");
            return 0;
        }
    }
}
