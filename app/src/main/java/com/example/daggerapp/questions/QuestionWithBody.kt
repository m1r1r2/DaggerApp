package com.example.daggerapp.questions

import com.example.daggerapp.user.User
import com.google.gson.annotations.SerializedName

data class QuestionWithBody(@SerializedName("title") val title: String,
                            @SerializedName("question_id") val id: String,
                            @SerializedName("body") val body: String,
                            @SerializedName("owner")val owner: User
)