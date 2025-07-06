package com.muhend.backend.repository;

import com.muhend.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    /**
     * Find all students with a grade greater than or equal to 10
     * @return List of students with grade >= 10
     */
    @Query("SELECT s FROM Student s WHERE s.note >= 10")
    List<Student> findStudentsWithGradeGreaterThanOrEqualToTen();
    
    // Alternative method using method naming convention
    List<Student> findByNoteGreaterThanEqual(double note);
}