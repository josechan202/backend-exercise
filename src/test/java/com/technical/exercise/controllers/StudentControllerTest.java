package com.technical.exercise.controllers;

import com.technical.exercise.enums.Sexuality;
import com.technical.exercise.models.Student;
import com.technical.exercise.services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testCreateStudent() throws Exception {
        // Mock the service response
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setAge(20);
        mockStudent.setSexuality(Sexuality.MALE);

        when(studentService.createStudent(any(Student.class))).thenReturn(mockStudent);

        // JSON request body
        String studentJson = """
            {
                "id": 1,
                "firstname": "John",
                "lastname": "Doe",
                "age": 20,
                "sexuality": "MALE"
            }
        """;

        // Perform POST request and validate the response
        mockMvc.perform(post("/students/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isCreated())
//                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.firstname").value("John"))
//                .andExpect(jsonPath("$.lastname").value("Doe"))
//                .andExpect(jsonPath("$.age").value(20))
//                .andExpect(jsonPath("$.sexuality").value("MALE"))
                .andReturn();
    }

    @Test
    public void testUpdateStudent() throws Exception {
        // Mock the service response
        Student existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setFirstName("John");
        existingStudent.setLastName("Doe");
        existingStudent.setAge(20);
        existingStudent.setSexuality(Sexuality.MALE);

        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setFirstName("Jane");
        updatedStudent.setLastName("Doe");
        updatedStudent.setAge(22);
        updatedStudent.setSexuality(Sexuality.FEMALE);

        when(studentService.getStudentById(1L)).thenReturn(existingStudent);
        when(studentService.saveStudent(any(Student.class))).thenReturn(updatedStudent);

        // JSON request body
        String updateJson = """
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "age": 22,
                "sexuality": "FEMALE"
            }
        """;

        // Perform POST request to update and validate the response
        mockMvc.perform(post("/api/students/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Jane"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.sexuality").value("FEMALE"));
    }
}
