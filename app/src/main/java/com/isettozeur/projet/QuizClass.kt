package com.isettozeur.projet

import android.content.Intent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.util.Log
import retrofit2.Response
import retrofit2.Callback

class QuizClass(private val context: Acceuil) {
    fun getQuizList(amount: Int, category: Int?, difficulty: String?, type: String?)
    {
        if (Constants.isNetworkAvailable(context)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val service: QuizService = retrofit.create(QuizService::class.java)
            val dataCall: Call<QuizResponse> = service.getQuiz(
                amount,
                category,
                difficulty,
                type
            )
            dataCall.enqueue(object : Callback<QuizResponse> {
                override fun onResponse(
                    call: Call<QuizResponse>,
                    response: Response<QuizResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseData: QuizResponse = response.body()!!
                        val questionList = ArrayList(responseData.results)
                        if (questionList.isNotEmpty()){
                            val intent = Intent(context,quiz::class.java)
                            intent.putExtra("questionList",questionList)
                            context.startActivity(intent)
                        }

                        Log.e("Debug", questionList.toString())
                    } else {
                        Utils.showToast(context, "Response Failed")


                    }
                }

                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {
                    Utils.showToast(context, "Failure in Response")
                }
            })
        } else {

            Utils.showToast(context, "Network is not available")
        }

        }




    fun getQuestionStatsList(callBack:QuestionStatCallback){
        if (Constants.isNetworkAvailable(context))
        {
            val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()


            val service:QuestionStatsService = retrofit.create(QuestionStatsService::class.java)
            val dataCall:Call<QuestionStats> = service.getData()
            dataCall.enqueue(object : Callback<QuestionStats>{
                override fun onResponse(
                    call: Call<QuestionStats>,
                    response: Response<QuestionStats>
                ) {
                    if (response.isSuccessful)
                    {
                        val questionStats: QuestionStats = response.body()!!
                        val categoryMap = questionStats.categories
                        callBack.onQuestionStatFetched(categoryMap)
                    }
                    else {
                        Utils.showToast(context,"error code: ${response.code()}")

                    }
                }

                override fun onFailure(
                    call: Call<QuestionStats>, t: Throwable) {
                    Utils.showToast(context,"API CALL FAILURE)}")
                }
            })
        }
        else{
            Utils.showToast(context,"NETWORK IS NOT available")
        }
    }
    interface QuestionStatCallback{
        fun onQuestionStatFetched(map:Map<String, Category>)
    }

}