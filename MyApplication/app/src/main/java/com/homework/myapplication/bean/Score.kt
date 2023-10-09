package com.homework.myapplication.bean

import java.io.Serializable

class Score : Serializable {
    var isShow = false
    var examId: String? = null
    var userName: String? = null
    var score: String? = null
    var examDate: String? = null
    var examList: List<Exam?>? = null
}