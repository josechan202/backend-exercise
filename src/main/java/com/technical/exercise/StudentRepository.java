package com.technical.exercise;
import com.technical.exercise.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>{



}
