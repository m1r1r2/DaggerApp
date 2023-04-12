package com.example.daggerapp.screens.questiondetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.daggerapp.questions.FetchQuestionDetailUseCase
import com.example.daggerapp.screens.commons.ScreenNavigator
import com.example.daggerapp.screens.commons.activities.BaseActivity
import com.example.daggerapp.screens.commons.dialogs.DialogNavigator
import com.example.daggerapp.screens.commons.viewsmvc.ViewMvcFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionDetailsActivity :BaseActivity(), QuestionDetailViewMvc.Listener{
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private  lateinit var questionId: String
    private lateinit var viewMvc: QuestionDetailViewMvc
    @Inject
    lateinit var  dialogsNavigator: DialogNavigator
    @Inject
    lateinit var fetchQuestionDetailUseCase: FetchQuestionDetailUseCase
    @Inject
    lateinit var screenNavigator: ScreenNavigator
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory




    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc= viewMvcFactory.newQuestionDetailViewMvc(null)
        setContentView(viewMvc.rootview)

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!

    }



    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when(val result = fetchQuestionDetailUseCase.fetchQuestion(questionId))
                {
                    is FetchQuestionDetailUseCase.Result.Success->
                    {

                        viewMvc.bindQuestionBody(result.question)
                    }
                    is FetchQuestionDetailUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }

            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.ShowServerErrorDialog()
    }


    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onBackClicked() {
        screenNavigator.navigateBack()
    }
}