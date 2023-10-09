package com.homework.myapplication.util

import android.app.Application
import com.homework.myapplication.model.DaoManager
import com.homework.myapplication.interfaces.CallBack
import com.homework.myapplication.bean.Score
import android.app.Activity
import android.content.Intent
import com.homework.myapplication.controller.ResultActivity
import com.homework.myapplication.app.MyApplication
import com.homework.myapplication.bean.Exam
import com.homework.myapplication.bean.Answer
import com.homework.myapplication.util.SharePerferenceUtils
import com.homework.myapplication.util.DataUtil
import com.homework.myapplication.bean.Question
import com.homework.myapplication.util.ListUtil
import android.database.sqlite.SQLiteOpenHelper
import com.homework.myapplication.util.DataBaseHelper
import android.database.sqlite.SQLiteDatabase
import android.content.SharedPreferences
import android.content.ContentValues
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.homework.myapplication.R
import android.text.TextUtils
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.TextView
import com.homework.myapplication.adapter.ResultAdapter.ResultHolder
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.myapplication.adapter.QuestionAdapter
import androidx.fragment.app.FragmentPagerAdapter
import android.os.Bundle
import kotlin.jvm.JvmOverloads
import androidx.appcompat.app.AppCompatActivity
import com.homework.myapplication.controller.NoScrollViewPager
import com.homework.myapplication.util.GuidGeneratorUtil
import com.homework.myapplication.controller.ExamFragment
import com.homework.myapplication.adapter.MyFragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import android.widget.Toast
import com.homework.myapplication.util.PecentUtil
import android.os.Build
import android.util.Log
import com.homework.myapplication.adapter.OptionAdapter
import android.widget.EditText
import com.homework.myapplication.controller.ExamActivity
import com.homework.myapplication.adapter.ResultAdapter
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent

object DataUtil {
    fun getData(context: Context, daoManager: DaoManager?) {
        if (SharePerferenceUtils.getInt(context, "isFirst", 0) == 0) {
            getNumberAndPlaceValue(daoManager)
            getAdditionAndSubtraction(daoManager)
            getMultiplicationAndDivision(daoManager)
            getFractions(daoManager)
            getMeasure(daoManager)
            getShapesPro(daoManager)
            getShapesPos(daoManager)
            /*  for (int i = 1; i <=42 ; i++) {
                Question question = new Question();
                question.setId(i);
                question.setTheme("math");
                question.setQuestionName("question"+i);
                for (int j = 1; j <=8 ; j++) {
                    Answer answer = new Answer();
                    answer.setId(j);
                    answer.setQuestionId(i);
                    answer.setAnswerContent(question.getQuestionName()+"answer"+j);
                    answer.setIsCorrect(0);
                    if (j==4){
                        answer.setIsCorrect(1);
                    }
                    daoManager.saveAnswerInfo(answer);
                    Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
                }
                daoManager.saveQuestionInfo(question);
            }*/SharePerferenceUtils.putInt(context, "isFirst", 1)
        }
    }

    private fun getNumberAndPlaceValue(daoManager: DaoManager?) {
        val question = Question()
        question.id = 1
        question.theme = "number and place value"
        question.questionName = "What is the next number?  66 = □ ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (61 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 67) {
                answer.answerContent = 67.toString()
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 2
        question.theme = "number and place value"
        question.questionName = "What is the next number?  31 = □ ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (26 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 32) {
                answer.answerContent = 32.toString()
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 3
        question.theme = "number and place value"
        question.questionName = "What is the next number?  16 = □ ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (11 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 17) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 4
        question.theme = "number and place value"
        question.questionName = "What is the next number?  78 = □ ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (73 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 79) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 5
        question.theme = "number and place value"
        question.questionName = "What is the next number?  54 = □ ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (49 + j).toString()
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 6
        question.theme = "number and place value"
        question.questionName = "What is the next number?  82 = □ ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (79 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 4 || answerContent!!.toInt() == 83) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }

    private fun getAdditionAndSubtraction(daoManager: DaoManager?) {
        val question = Question()
        question.id = 7
        question.theme = "addition and subtraction"
        question.questionName = "wirte the answer  3 + 6 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (6 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (answerContent!!.toInt() == 9) {
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 8
        question.theme = "addition and subtraction"
        question.questionName = "wirte the answer  2 + 4 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (4 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (answerContent!!.toInt() == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 9
        question.theme = "addition and subtraction"
        question.questionName = "wirte the answer  6 + 1 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (3 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (answerContent!!.toInt() == 7) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 10
        question.theme = "addition and subtraction"
        question.questionName = "wirte the answer  7 - 2 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (2 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (answerContent!!.toInt() == 5) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 11
        question.theme = "addition and subtraction"
        question.questionName = "wirte the answer  9 - 1 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (3 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 5) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 12
        question.theme = "addition and subtraction"
        question.questionName = "wirte the answer  10 - 4 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (2 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (answerContent!!.toInt() == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }

    private fun getMultiplicationAndDivision(daoManager: DaoManager?) {
        val question = Question()
        question.id = 13
        question.theme = "multiplication and division"
        question.questionName = "wirte the answer  3 x 6 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (12 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 18) {
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 14
        question.theme = "multiplication and division"
        question.questionName = "wirte the answer  7 x 6 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (36 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 42) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 15
        question.theme = "multiplication and division"
        question.questionName = "wirte the answer  9 x 3 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (21 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 27) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 16
        question.theme = "multiplication and division"
        question.questionName = "wirte the answer  27 ÷ 3 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (3 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 9) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 17
        question.theme = "multiplication and division"
        question.questionName = "wirte the answer  48 ÷ 6 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (2 + j).toString()
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 18
        question.theme = "multiplication and division"
        question.questionName = "wirte the answer  63 ÷ 3 = ?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (15 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 21) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }

    private fun getFractions(daoManager: DaoManager?) {
        val question = Question()
        question.id = 19
        question.theme = "fractions"
        question.questionName =
            """
            Priya's pencil is 12 centimeters long. Daniel's pencil is half the length of Priya's pencil. So what's Daniel's pencil length
            Less?
            """.trimIndent()
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = j.toString()
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 20
        question.theme = "fractions"
        question.questionName =
            "Dad needs four minutes to tie his shoes. Sam just needs dad's time ¾ 。 So how long does it take Sam to tie his shoes?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = j.toString()
            answer.isCorrect = 0
            if (j == 3) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 21
        question.theme = "fractions"
        question.questionName =
            "Lucy has 12 sweets. She ate a quarter of them. So how many sweets does she have left?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (j + 3).toString()
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 22
        question.theme = "fractions"
        question.questionName = "Aleena keeps 30 bees in a small honeycomb. She wants" +
                "Number of bees ½ Transfer to a new cell. So new" +
                "How many bees will there be in your hive?"
        question.questionSrc = "fractions/mifeng.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (4 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 10) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 23
        question.theme = "fractions"
        question.questionSrc = ""
        question.questionName =
            "Which of the following graphics corresponds to a color part that is  1 / 4?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.isCorrect = 0
            answer.answerSrc = "fractions/$j.png"
            if (j == 1) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerSrc + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 24
        question.theme = "fractions"
        question.questionSrc = ""
        question.questionName =
            "There are 20 bees living in a honeycomb. Two quarters of them are bees" +
                    "Fly away to collect honey. So how many beehives are left" +
                    "Bees?"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            answer.answerContent = (14 + j).toString()
            answer.isCorrect = 0
            val answerContent = answer.answerContent
            if (j == 6 || answerContent!!.toInt() == 20) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }

    private fun getMeasure(daoManager: DaoManager?) {
        val question = Question()
        question.id = 25
        question.theme = "measure"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/circle.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 1) {
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 26
        question.theme = "measure"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cone.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 2) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 27
        question.theme = "measure"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/oval.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 3) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 28
        question.theme = "measure"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cylinder.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 4) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 29
        question.theme = "measure"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/hexagon.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 5) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 30
        question.theme = "measure"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cube.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }

    private fun getShapesPro(daoManager: DaoManager?) {
        val question = Question()
        question.id = 31
        question.theme = "properties of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/circle.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 1) {
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 32
        question.theme = "properties of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cone.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 2) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 33
        question.theme = "properties of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/oval.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 3) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 34
        question.theme = "properties of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cylinder.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 4) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 35
        question.theme = "properties of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/hexagon.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 5) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 36
        question.theme = "properties of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cube.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }

    private fun getShapesPos(daoManager: DaoManager?) {
        val question = Question()
        question.id = 37
        question.theme = "position of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/circle.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 1) {
                answer.isCorrect = 1
            }
            daoManager!!.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager!!.saveQuestionInfo(question)
        question.id = 38
        question.theme = "position of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cone.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 2) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 39
        question.theme = "position of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/oval.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 3) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 40
        question.theme = "position of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cylinder.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 4) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 41
        question.theme = "position of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/hexagon.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 5) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
        question.id = 42
        question.theme = "position of shapes"
        question.questionName = "The name of the following drawing is?"
        question.questionSrc = "shape/cube.png"
        for (j in 1..8) {
            val answer = Answer()
            answer.id = j
            answer.questionId = question.id
            val shape = ListUtil.getShape(j - 1)
            answer.answerContent = shape
            answer.isCorrect = 0
            if (j == 6) {
                answer.isCorrect = 1
            }
            daoManager.saveAnswerInfo(answer)
            Log.e(
                "answer",
                question.id.toString() + "-------" + answer.answerContent + "----" + answer.isCorrect
            )
        }
        daoManager.saveQuestionInfo(question)
    }
}