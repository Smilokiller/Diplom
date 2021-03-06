package com.example.diplom.controllers;

import com.example.diplom.dal.api.MarksDao;
import com.example.diplom.entity.MarksEntity;
import com.example.diplom.exception.MarksException;
import com.example.diplom.service.MarksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class MarksController {

    @Autowired
    MarksDao marksDao;

    @Autowired
    MarksService marksService;

    final static Logger LOG = LoggerFactory.getLogger(TeacherController.class);

    @RequestMapping(value = "/marks", method = RequestMethod.GET)
    public List<MarksEntity> getAll() {
        LOG.info("Getting all marks");

        return marksDao.findAll();
    }


    @RequestMapping(value = "/marks/{id}", method = RequestMethod.GET)
    public Optional<MarksEntity> getById(@PathVariable("id") String id) {
        final Optional<MarksEntity> marksEntity = this.marksDao.findById(Integer.valueOf(id));
        if (marksEntity != null) {
            LOG.info("Getting marks {}", id);
            return marksDao.findById(Integer.valueOf(id));
        } else
            LOG.warn("no marks", id);
        throw new MarksException(id);
    }

    @RequestMapping(value = "/marks/", method = RequestMethod.DELETE)
    public void deleteAll() {
        LOG.info("Deleting all marks");
        marksDao.deleteAll();
    }

    @RequestMapping(value = "/marks/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        LOG.info("Deleting mark {}", id);
        marksDao.deleteById(Integer.decode(id));
    }

    @RequestMapping(value = "/marks", method = RequestMethod.POST)
    public MarksEntity create(@RequestBody MarksEntity gets) {
        LOG.info("Creating marks {}", gets);
        marksService.generateMarksForAllStudentsInGroup(gets);
        return marksDao.save(gets);
    }

    @RequestMapping(value = "/marks/students", method = RequestMethod.GET)
    public List<MarksEntity> getGetsByStudentId(@RequestParam(value = "id") int studentId) {
        LOG.info("Getting all marks for student {}", studentId);
        return marksDao.getByStudentId(studentId);
    }

    @RequestMapping(value = "/marks/{id}/students", method = RequestMethod.GET)
    public List<MarksEntity> getGetsBySubjectIdAndStudentId(@PathVariable("id") int subjectId,
                                                            @RequestParam(value = "id") int studentId) {
        LOG.info("Getting all marks for student {} and subject {}", studentId, subjectId);
        return marksDao.getBySubjectIdAndStudentId(studentId, subjectId);
    }

    @RequestMapping(value = "/marks/{id}/role/{id}/student", method = RequestMethod.GET)
    public List<MarksEntity> getBySubjectIdAndRoleId(@PathVariable("id") int subjectId,
                                                     @PathVariable(value = "id") int role,
                                                     @RequestParam(value = "id") int studentId) {
        LOG.info("Getting all marks for role {} and subject {} and student {}", role, subjectId);
        return marksDao.getBySubjectIdAndRoleId(role, subjectId, studentId);
    }
}
