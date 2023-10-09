package com.homework.myapplication.util;

import android.content.Context;
import android.util.Log;

import com.homework.myapplication.bean.Answer;
import com.homework.myapplication.bean.Question;
import com.homework.myapplication.model.DaoManager;

public class DataUtil {

    public static void getData(Context context, DaoManager daoManager) {
        if (SharePerferenceUtils.getInt(context,"isFirst",0)==0){
            getNumberAndPlaceValue(daoManager);
            getAdditionAndSubtraction(daoManager);
            getMultiplicationAndDivision(daoManager);
            getFractions(daoManager);
            getMeasure(daoManager);
            getShapesPro(daoManager);
            getShapesPos(daoManager);
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
            }*/
            SharePerferenceUtils.putInt(context,"isFirst",1);
        }

    }
    private static void getNumberAndPlaceValue(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(1);
        question.setTheme("number and place value");
        question.setQuestionName("What is the next number?  66 = □ ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(61+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==67){
                answer.setAnswerContent(String.valueOf(67));
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(2);
        question.setTheme("number and place value");
        question.setQuestionName("What is the next number?  31 = □ ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(26+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==32){
                answer.setAnswerContent(String.valueOf(32));
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(3);
        question.setTheme("number and place value");
        question.setQuestionName("What is the next number?  16 = □ ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(11+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==17){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(4);
        question.setTheme("number and place value");
        question.setQuestionName("What is the next number?  78 = □ ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(73+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==79){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(5);
        question.setTheme("number and place value");
        question.setQuestionName("What is the next number?  54 = □ ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(49+j));
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(6);
        question.setTheme("number and place value");
        question.setQuestionName("What is the next number?  82 = □ ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(79+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==4||Integer.parseInt(answerContent)==83){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);
    }


    private static void getAdditionAndSubtraction(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(7);
        question.setTheme("addition and subtraction");
        question.setQuestionName("wirte the answer  3 + 6 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(6+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (Integer.parseInt(answerContent)==9){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(8);
        question.setTheme("addition and subtraction");
        question.setQuestionName("wirte the answer  2 + 4 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(4+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (Integer.parseInt(answerContent)==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(9);
        question.setTheme("addition and subtraction");
        question.setQuestionName("wirte the answer  6 + 1 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(3+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (Integer.parseInt(answerContent)==7){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(10);
        question.setTheme("addition and subtraction");
        question.setQuestionName("wirte the answer  7 - 2 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(2+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (Integer.parseInt(answerContent)==5){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(11);
        question.setTheme("addition and subtraction");
        question.setQuestionName("wirte the answer  9 - 1 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(3+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==5){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(12);
        question.setTheme("addition and subtraction");
        question.setQuestionName("wirte the answer  10 - 4 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(2+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (Integer.parseInt(answerContent)==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);
    }

    private static void getMultiplicationAndDivision(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(13);
        question.setTheme("multiplication and division");
        question.setQuestionName("wirte the answer  3 x 6 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(12+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==18){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(14);
        question.setTheme("multiplication and division");
        question.setQuestionName("wirte the answer  7 x 6 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(36+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==42){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(15);
        question.setTheme("multiplication and division");
        question.setQuestionName("wirte the answer  9 x 3 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(21+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==27){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(16);
        question.setTheme("multiplication and division");
        question.setQuestionName("wirte the answer  27 ÷ 3 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(3+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==9){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(17);
        question.setTheme("multiplication and division");
        question.setQuestionName("wirte the answer  48 ÷ 6 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(2+j));
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(18);
        question.setTheme("multiplication and division");
        question.setQuestionName("wirte the answer  63 ÷ 3 = ?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(15+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==21){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);
    }

    private static void getFractions(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(19);
        question.setTheme("fractions");
        question.setQuestionName("Priya's pencil is 12 centimeters long. Daniel's pencil is half the length of Priya's pencil. So what's Daniel's pencil length\n" +
                "Less?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(j));
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(20);
        question.setTheme("fractions");
        question.setQuestionName("Dad needs four minutes to tie his shoes. Sam just needs dad's time ¾ 。 So how long does it take Sam to tie his shoes?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(j));
            answer.setIsCorrect(0);
            if (j==3){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(21);
        question.setTheme("fractions");
        question.setQuestionName("Lucy has 12 sweets. She ate a quarter of them. So how many sweets does she have left?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(j+3));
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(22);
        question.setTheme("fractions");
        question.setQuestionName("Aleena keeps 30 bees in a small honeycomb. She wants" +
                "Number of bees ½ Transfer to a new cell. So new" +
                "How many bees will there be in your hive?");
        question.setQuestionSrc("fractions/mifeng.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(4+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==10){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(23);
        question.setTheme("fractions");
        question.setQuestionSrc("");
        question.setQuestionName("Which of the following graphics corresponds to a color part that is  1 / 4?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setIsCorrect(0);
            answer.setAnswerSrc("fractions/"+j+".png");
            if (j==1){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerSrc()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(24);
        question.setTheme("fractions");
        question.setQuestionSrc("");
        question.setQuestionName("There are 20 bees living in a honeycomb. Two quarters of them are bees" +
                "Fly away to collect honey. So how many beehives are left" +
                "Bees?");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            answer.setAnswerContent(String.valueOf(14+j));
            answer.setIsCorrect(0);
            String answerContent = answer.getAnswerContent();
            if (j==6||Integer.parseInt(answerContent)==20){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);
    }

    private static void getMeasure(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(25);
        question.setTheme("measure");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/circle.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==1){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(26);
        question.setTheme("measure");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cone.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==2){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(27);
        question.setTheme("measure");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/oval.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==3){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(28);
        question.setTheme("measure");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cylinder.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==4){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);


        question.setId(29);
        question.setTheme("measure");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/hexagon.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==5){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);


        question.setId(30);
        question.setTheme("measure");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cube.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);
    }

    private static void getShapesPro(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(31);
        question.setTheme("properties of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/circle.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==1){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(32);
        question.setTheme("properties of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cone.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==2){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(33);
        question.setTheme("properties of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/oval.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==3){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(34);
        question.setTheme("properties of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cylinder.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==4){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);


        question.setId(35);
        question.setTheme("properties of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/hexagon.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==5){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);


        question.setId(36);
        question.setTheme("properties of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cube.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

    }

    private static void getShapesPos(DaoManager daoManager) {
        Question question  = new Question();
        question.setId(37);
        question.setTheme("position of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/circle.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==1){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(38);
        question.setTheme("position of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cone.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==2){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(39);
        question.setTheme("position of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/oval.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==3){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

        question.setId(40);
        question.setTheme("position of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cylinder.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==4){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);


        question.setId(41);
        question.setTheme("position of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/hexagon.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==5){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);


        question.setId(42);
        question.setTheme("position of shapes");
        question.setQuestionName("The name of the following drawing is?");
        question.setQuestionSrc("shape/cube.png");
        for (int j = 1; j <=8 ; j++) {
            Answer answer = new Answer();
            answer.setId(j);
            answer.setQuestionId(question.getId());
            String shape = ListUtil.getShape(j-1);
            answer.setAnswerContent(shape);
            answer.setIsCorrect(0);
            if (j==6){
                answer.setIsCorrect(1);
            }
            daoManager.saveAnswerInfo(answer);
            Log.e("answer",question.getId()+"-------"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
        }
        daoManager.saveQuestionInfo(question);

    }


}
