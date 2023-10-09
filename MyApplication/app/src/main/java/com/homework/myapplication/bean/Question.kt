package com.homework.myapplication.bean

import java.io.Serializable

class Question : Serializable {
    var id = 0
    var theme: String? = null
    var questionName: String? = null
    var questionSrc: String? = null
    var answerList: List<Answer?>? = null
}