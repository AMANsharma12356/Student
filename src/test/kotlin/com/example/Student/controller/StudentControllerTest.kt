package com.example.Student.controller

import com.example.Student.model.Student
import com.example.Student.repository.StudentRepository
import com.example.Student.service.StudentService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Mono

@WebFluxTest(StudentController::class)
@AutoConfigureWebTestClient
 class StudentControllerTest {

    @Autowired
    lateinit var client: WebTestClient


    @Autowired
    lateinit var studentService: StudentService

    @Autowired
    lateinit var studentRepository: StudentRepository

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun studentService() = mockk<StudentService>()

        @Bean
        fun studentRepository() = mockk<StudentRepository>()

    }

    @Test
    fun `should return one student`() {
        val student = Student(
            "1", "pratik", "361992", "12", "A"
        )
        val expectedResult = mapOf(
            "studentId" to "1",
            "studentName" to "pratik",
            "studentRoll" to "361992",
            "studentClass" to "12",
            "studentDiv" to "A"
        )
        every {
            studentService.findById("1")
        } returns Mono.just(student)

        val response = client.get()
            .uri("/students/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            studentService.findById("1")
        }
    }

    @Test
    fun `should create student when api is being called `() {
        val student = Student(
            "1", "pratik", "361992", "12", "A"
        )
        val expectedResponse = mapOf(
            "studentId" to "1",
            "studentName" to "pratik",
            "studentRoll" to "361992",
            "studentClass" to "12",
            "studentDiv" to "A"
        )
        every {
            studentService.addStudent(student)
        } returns Mono.just(student)
        val response = client.post()
            .uri("/students/add")
            .bodyValue(student)
            .exchange()
            .expectStatus().is2xxSuccessful
            .returnResult<Any>().responseBody

        response.blockFirst() shouldBe expectedResponse

        verify(exactly = 1) {
            studentService.addStudent(student)
        }

    }

    @Test
    fun `should able to delete student`() {
        val student = Student(
            "1", "pratik", "361992", "12", "A"
        )
        val expectedResponse = mapOf(
            "studentId" to "1",
            "studentName" to "pratik",
            "studentRoll" to "361992",
            "studentClass" to "12",
            "studentDiv" to "A"
        )
        every {
            studentService.deleteById("1")
        } returns Mono.empty()

        val response = client.delete()
            .uri("/students/1")
            .exchange()
            .expectStatus().is2xxSuccessful
        verify(exactly = 1) {
            studentService.deleteById("1")
        }
    }

    @Test
    fun `should able to update student`() {
        val student = Student(
            "1", "pratik", "361992", "12", "A"
        )
        val expectedResponse = mapOf(
            "studentId" to "1",
            "studentName" to "pratik",
            "studentRoll" to "361992",
            "studentClass" to "12",
            "studentDiv" to "A"
        )
        every {
            studentService.updateStudentById("1", student)
        } returns Mono.just(student)

        val response = client.put()
            .uri("/updateStudent/1")
            .bodyValue(student)
            .exchange()
            .expectStatus().is2xxSuccessful

        verify(exactly = 1) {
            studentService.updateStudentById("1", student)
        }

    }
}
