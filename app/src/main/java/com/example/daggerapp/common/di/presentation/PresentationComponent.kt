package com.example.daggerapp.common.di.presentation

import com.example.daggerapp.screens.questiondetail.QuestionDetailsActivity
import com.example.daggerapp.screens.questionlist.QuestionListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent()
interface PresentationComponent {
    fun inject(fragment: QuestionListFragment)
    fun inject(activity: QuestionDetailsActivity)
}