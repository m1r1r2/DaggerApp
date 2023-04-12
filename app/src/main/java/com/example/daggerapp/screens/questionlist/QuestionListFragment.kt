package com.example.daggerapp.screens.questionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggerapp.questions.FetchQuestionUseCase
import com.example.daggerapp.questions.Question
import com.example.daggerapp.screens.commons.ScreenNavigator
import com.example.daggerapp.screens.commons.dialogs.DialogNavigator
import com.example.daggerapp.screens.commons.fragment.BaseFragment
import com.example.daggerapp.screens.commons.viewsmvc.ViewMvcFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionListFragment : BaseFragment(), QuestionListViewMVC.Listener{


    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var isDataLoaded = false

    private  lateinit var viewMvc: QuestionListViewMVC
    @Inject
    lateinit var fetchQuestionUseCase: FetchQuestionUseCase
    @Inject
    lateinit var  dialogsNavigator: DialogNavigator
    @Inject
    lateinit var screenNavigator: ScreenNavigator
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMvc= viewMvcFactory.newQuestionsListViewMvc(container)
        return  viewMvc.rootview
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchQuestionUseCase.fetchLatestQuestion()
                when (result) {
                    is FetchQuestionUseCase.Result.Success -> {
                        viewMvc.bindQuestion(result.questions)
                        isDataLoaded = true
                    }
                    is FetchQuestionUseCase.Result.Failure -> onFetchFailed()

                }
            }finally {
                viewMvc.hideProgressIndication()
            }


        }
    }


    private fun onFetchFailed() {
        dialogsNavigator.ShowServerErrorDialog()
    }



    override fun onRefreshclicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(clickedQuestion: Question) {
        screenNavigator.toQuestionDetails(clickedQuestion.id)
    }
}