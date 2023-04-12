package com.example.daggerapp.networking

import com.example.daggerapp.questions.Question
import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@SerializedName("items") val questions:List<Question>) {
}