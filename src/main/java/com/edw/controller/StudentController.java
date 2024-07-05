package com.edw.controller;

import com.edw.model.StudentModel;
import com.edw.repository.StudentRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.logging.Logger;

import java.util.HashMap;

/**
 * <pre>
 *  com.edw.controller.StudentController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 05 Jul 2024 15:40
 */
@Path("/api")
public class StudentController {

    private static final Logger LOG = Logger.getLogger(StudentController.class);

    @Inject
    StudentRepository studentRepository;

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents(@QueryParam("page") Integer page) {
        LOG.debug(String.format("get all students"));
        return Response.ok(new HashMap(){{
                    put("result", studentRepository.findAll(page));
                }})
                .build();
    }


    @POST
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(StudentModel studentModel) {
        LOG.debug(String.format("creating student"));
        studentRepository.persist(studentModel);
        return Response.status(Response.Status.CREATED)
                .entity(new HashMap(){{
                    put("result", studentRepository.findById(studentModel.getId()));
                }})
                .build();
    }
}
