package hsos.vts;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
public class Adresse extends PanacheEntity {
    public String plz;
}