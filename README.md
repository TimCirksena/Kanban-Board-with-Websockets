"# VTS-Projektarbeit" 
## anleitung für embedded postgreSQL
1. quarkus projekt mit quarkus-jdbc-postgresql und quarkus-hibernate-orm-panache
2. 
        <dependency>
            <groupId>io.zonky.test</groupId>
            <artifactId>embedded-postgres</artifactId>
            <version>2.0.1</version>
            <scope>compile</scope>
        </dependency>
    zur pom.xml hinzufügen
3. @Startup
    public void startDB(){
        try {
            EmbeddedPostgres pg = EmbeddedPostgres.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    zu einer resource hinzufügen
4. zu classpath hinzufügen
5. run und gucken welcher port es ist in dem run fenster von intellij
6. den port einstellen in diesem application properties
        # configure your datasource
        quarkus.datasource.db-kind = postgresql
        quarkus.datasource.username = postgres
        quarkus.datasource.password = postgres
        quarkus.datasource.jdbc.url = jdbc:postgresql://localhost:60074/postgres
        #port muss man gucken bei INFO  [io.zon.tes.db.pos.emb.EmbeddedPostgres], der ist oft anders
        # drop and create the database at startup (use `update` to only update the schema)
        quarkus.hibernate-orm.database.generation = update
7. panache entity anlegen und testen

info: der saved nicht für immer, wenn man intellij ausmacht und wieder an, isses wech!
man muss jedes bei intellij start ein den unique port angeben