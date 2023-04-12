package com.example.daggerapp.screens.commons.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.daggerapp.screens.imageloader.ImageLoader
import com.example.daggerapp.screens.questiondetail.QuestionDetailViewMvc
import com.example.daggerapp.screens.questionlist.QuestionListViewMVC
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater, private val ImageLoader:ImageLoader) {
    fun newQuestionsListViewMvc( parent: ViewGroup?):QuestionListViewMVC{
        return QuestionListViewMVC(layoutInflater,parent)

    }

    fun newQuestionDetailViewMvc(parent: ViewGroup?): QuestionDetailViewMvc {
        return QuestionDetailViewMvc(layoutInflater,ImageLoader,parent)
    }
}