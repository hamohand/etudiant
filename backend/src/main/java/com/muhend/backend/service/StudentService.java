package com.muhend.backend.service;

import com.muhend.backend.model.Student;
import com.muhend.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Get all students
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Get a student by ID
     * @param id Student ID
     * @return Student if found, null otherwise
     */
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    /**
     * Save a student
     * @param student Student to save
     * @return Saved student
     */
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Delete a student by ID
     * @param id Student ID
     */
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    /**
     * Get all students with a grade greater than or equal to 10
     * @return List of students with grade >= 10
     */
    public List<Student> getStudentsWithGradeGreaterThanOrEqualToTen() {
        return studentRepository.findStudentsWithGradeGreaterThanOrEqualToTen();
    }
    
    /**
     * Get all students with a grade greater than or equal to the specified value
     * @param grade Minimum grade
     * @return List of students with grade >= specified value
     */
    public List<Student> getStudentsWithGradeGreaterThanOrEqual(double grade) {
        return studentRepository.findByNoteGreaterThanEqual(grade);
    }
}