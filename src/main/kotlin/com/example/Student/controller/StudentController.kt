package com.example.Student.controller

import com.example.Student.model.Student
import com.example.Student.repository.StudentRepository
import com.example.Student.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin("*")

@RestController
class StudentController (
        @Autowired
        val studentRepository: StudentRepository,
        val studentService: StudentService

        ){
        @GetMapping("/students/list")
        fun getAllStudents(): Flux<Student> {
                return studentService.findAllstudents()
        }

        @GetMapping("/students/{id}")
        fun getSudentById(@PathVariable id: String): Mono<Student> {
                return studentService.findById(id)
        }
        @PostMapping("/students/add")
        fun save(@RequestBody student: Student): Mono<Student> {
                return studentService.addStudent(student)
        }
        @DeleteMapping
        fun delete(): Mono<Void> {
                return studentRepository.deleteAll()
        }

        @DeleteMapping("/students/{id}")
        fun deleteUser(@PathVariable id: String): Mono<Void> {
                return studentService.deleteById(id)
        }

        @PutMapping("/updateStudent/{id}")
        fun update(@PathVariable("id") id: String,@RequestBody student: Student): Mono<Student> {
                return studentService.updateStudentById(id,student)
        }

}