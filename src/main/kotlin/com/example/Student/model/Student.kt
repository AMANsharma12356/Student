package com.example.Student.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Student (
    @Id
    var studentId: String?,
    var studentName: String,
    var studentRoll: String,
    var studentClass: String,
    var studentDiv: String,


    )