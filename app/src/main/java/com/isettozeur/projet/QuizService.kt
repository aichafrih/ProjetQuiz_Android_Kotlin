package com.isettozeur.projet

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface QuizService {
    @GET("api.php")
    fun getQuiz(
        @Query("amount") amount:Int,
        @Query("category")category:Int?,
        @Query("difficulty")difficulty:String?,
        @Query("type") type:String?
    ):Call<QuizResponse>
}
interface QuestionStatsService{
    @GET("api_count_global.php")
    fun getData():Call<QuestionStats>
}