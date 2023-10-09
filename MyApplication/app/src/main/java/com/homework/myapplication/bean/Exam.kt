package com.homework.myapplication.bean

import java.io.Serializable

class Exam : Serializable {
    var examId: String? = null
    var userName: String? = null
    var questionId = 0
    var questionContent: String? = null
    var isCorrect = 0
    var examDate: String? = null
}