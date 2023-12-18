package ru.arkhipov.MySpringBoot2Dbase.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.arkhipov.MySpringBoot2Dbase.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        Query query = entityManager.createQuery("from Student");
        List<Student> allStudents = query.getResultList();
        log.info("getAllStudents" + allStudents);
        return allStudents;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Query query = entityManager.createQuery("delete from Student where id = :studentId");
        query.setParameter("studentId", id);
        query.executeUpdate();
    }
}