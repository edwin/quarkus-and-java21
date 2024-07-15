package com.edw.repository;

import com.edw.model.StudentModel;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * <pre>
 *  com.edw.repository.StudentRepository
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 05 Jul 2024 16:19
 */
@Transactional
@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<StudentModel, Long> {

    public List<StudentModel> findAll(Integer page) {
        return StudentModel.findAll(Sort.by("id", Sort.Direction.Ascending))
                .page(Page.of(page, 5))
                .list();
    }

    public List<StudentModel> findByName(String name) {
        PanacheQuery<StudentModel> query = StudentModel.find("select sm  from StudentModel sm where name like CONCAT('%', ?1, '%') ", name);
        return query.stream().toList();
    }

}
