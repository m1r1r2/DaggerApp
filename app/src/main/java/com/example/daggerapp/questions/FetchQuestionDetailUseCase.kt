package com.example.daggerapp.questions

import com.example.daggerapp.networking.StackOverFlowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchQuestionDetailUseCase @Inject constructor(private val stackOverFlowApi: StackOverFlowApi) {

    sealed class  Result{
        data class Success(val question: QuestionWithBody):Result()
        object Failure:Result()
    }

    suspend fun fetchQuestion(questionId:String): Result {
        return withContext(Dispatchers.IO)
        {
            try {
                val response = stackOverFlowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.question)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw  t
                }
            }
        }
    }
}