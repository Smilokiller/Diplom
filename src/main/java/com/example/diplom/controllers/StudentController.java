package com.example.diplom.controllers;

import com.example.diplom.dal.api.StudentDao;
import com.example.diplom.entity.StudentEntity;
import com.example.diplom.exception.StudentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class StudentController {

     @Autowired
    StudentDao studentDao;

    final static Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentEntity> getAll() {
        LOG.info("Getting all students");
        return studentDao.findAll();
    }


    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public Optional<StudentEntity> getById(@PathVariable("id") String id) {
        final Optional<StudentEntity> studentEntity = this.studentDao.findById(Integer.valueOf(id));
        if (studentEntity != null) {
            LOG.info("Getting student {}", id);
            return studentDao.findById(Integer.valueOf(id));
        } else
            LOG.warn("no student", id);
        throw new StudentException(id);


    }


    @RequestMapping(value = "/students/", method = RequestMethod.DELETE)
    public void deleteAll() {
        LOG.info("Deleting all students");
        studentDao.deleteAll();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        LOG.info("Deleting student {}", id);
        studentDao.deleteById(Integer.decode(id));
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public StudentEntity create(@RequestBody StudentEntity student) {
        LOG.info("Creating student {}", student);
        return studentDao.save(student);
    }

    @RequestMapping(value = "/students/groups", method = RequestMethod.GET)
    public List<StudentEntity> getGetsByStudentId(@RequestParam(value = "id") int group_id) {
        LOG.info("Getting all students of group {}", group_id);
        return studentDao.getStudentsGroup(group_id);
    }
}
