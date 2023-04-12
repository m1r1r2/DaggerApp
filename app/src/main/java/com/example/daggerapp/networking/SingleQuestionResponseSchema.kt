package com.example.daggerapp.networking

import com.example.daggerapp.questions.QuestionWithBody
import com.google.gson.annotations.SerializedName

class SingleQuestionResponseSchema(@SerializedName("items") val questions:List<QuestionWithBody>){
    val question: QuestionWithBody get() = questions[0]
}