package com.example.daggerapp.screens.commons

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerapp.common.di.activity.ActivityScope
import com.example.daggerapp.screens.questiondetail.QuestionDetailsActivity

import javax.inject.Inject

@ActivityScope
class ScreenNavigatorImpl @Inject constructor(private val activity: AppCompatActivity):ScreenNavigator {
   override fun navigateBack(){
       activity.onBackPressed()
    }

   override fun toQuestionDetails(questionId:String) {
        QuestionDetailsActivity.start(activity, questionId)
    }


}