package com.example.Student.service

import com.example.Student.model.Student
import com.example.Student.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class StudentService (
    @Autowired
    val studentRepository: StudentRepository
        ){
    fun findAllstudents() : Flux<Student>{
        return studentRepository.findAll()
    }

    fun findById(id: String): Mono<Student> {
        return studentRepository.findById(id)
    }
    fun addStudent(student: Student): Mono<Student> {
        return studentRepository.save(student)
    }

    fun deleteById(id: String): Mono<Void> {
        return studentRepository.deleteById(id)
    }
    fun updateStudentById(id:String, student: Student): Mono<Student> {
        return studentRepository.save(student)
    }

}