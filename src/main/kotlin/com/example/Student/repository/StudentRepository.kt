package com.example.Student.repository

import com.example.Student.model.Student
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : ReactiveMongoRepository<Student, String> {

}