package com.muhend.backend.controller;

import com.muhend.backend.model.Student;
import com.muhend.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from any origin (for development)
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Get all students
     * @return List of all students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Get a student by ID
     * @param id Student ID
     * @return Student if found, 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new student
     * @param student Student to create
     * @return Created student
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    /**
     * Update an existing student
     * @param id Student ID
     * @param student Student data to update
     * @return Updated student if found, 404 otherwise
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            student.setId(id);
            Student updatedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a student
     * @param id Student ID
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all students with a grade greater than or equal to 10
     * @return List of students with grade >= 10
     */
    @GetMapping("/passing")
    public ResponseEntity<List<Student>> getPassingStudents() {
        List<Student> passingStudents = studentService.getStudentsWithGradeGreaterThanOrEqualToTen();
        return new ResponseEntity<>(passingStudents, HttpStatus.OK);
    }

    /**
     * Get all students with a grade greater than or equal to the specified value
     * @param grade Minimum grade
     * @return List of students with grade >= specified value
     */
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentsWithMinimumGrade(@PathVariable double grade) {
        List<Student> students = studentService.getStudentsWithGradeGreaterThanOrEqual(grade);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}