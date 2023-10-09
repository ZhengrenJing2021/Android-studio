package com.homework.myapplication.bean

import java.io.Serializable

class Answer : Serializable {
    var isChecked = false
    var id = 0
    var questionId = 0
    var answerContent: String? = null
    var isCorrect = 0
    var answerSrc: String? = null
}