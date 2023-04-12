package com.example.daggerapp.questions

import com.example.daggerapp.networking.StackOverFlowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchQuestionUseCase @Inject constructor(private val stackOverFlowApi: StackOverFlowApi) {
    sealed class  Result{
        data class Success(val questions:List<Question>):Result()
        object Failure:Result()
    }

    suspend fun fetchLatestQuestion():Result
    { return  withContext(Dispatchers.IO)
    {
        try {
            val response = stackOverFlowApi.lastActiveQuestions(20)
            if (response.isSuccessful && response.body() != null) {
                return@withContext Result.Success(response.body()!!.questions)
            } else {
                return@withContext Result.Failure
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                return@withContext Result.Failure
            }
            else
            {
                throw  t
            }
        }
    }
    }

}