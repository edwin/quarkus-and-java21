package com.edw.controller;

import com.edw.model.StudentModel;
import com.edw.repository.StudentRepository;
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
    public Response getAllStudents(@DefaultValue("0") @QueryParam("page") Integer page) {
        LOG.debugf("get all students");
        return Response.ok(new HashMap(){{
                    put("result", studentRepository.findAll(page));
                }})
                .build();
    }

    @GET
    @Path("/students/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudentsWithName(@PathParam("name") String name) {
        LOG.debugf("get all students with name %s", name);
        return Response.ok(new HashMap(){{
                    put("result", studentRepository.findByName(name));
                }})
                .build();
    }


    @POST
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(StudentModel studentModel) {
        LOG.debugf("creating student %s", studentModel.getName());
        studentRepository.persist(studentModel);
        return Response.status(Response.Status.CREATED)
                .entity(new HashMap(){{
                    put("result", studentRepository.findById(studentModel.getId()));
                }})
                .build();
    }
}
