package com.edw.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

/**
 * <pre>
 *  com.edw.model.StudentModel
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 05 Jul 2024 15:40
 */
@Entity(name="T_STUDENT")
public class StudentModel extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    public StudentModel() {
    }

    public StudentModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
