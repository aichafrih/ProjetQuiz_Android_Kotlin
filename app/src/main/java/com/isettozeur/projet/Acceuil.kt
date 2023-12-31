package com.isettozeur.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.recyclerview.widget.GridLayoutManager
import com.isettozeur.projet.databinding.ActivityAcceuilBinding

class Acceuil : AppCompatActivity() {
    private var binding:ActivityAcceuilBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceuilBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val quizClass = QuizClass(this)
        val rvCategoryList = binding?.rvCategoryList
        rvCategoryList?.layoutManager = GridLayoutManager(this,2)

        quizClass.getQuestionStatsList(object : QuizClass.QuestionStatCallback {
            override fun onQuestionStatFetched(map: Map<String,Category>) {
               val adapter = GridAdapter(Constants.getCategoryItemList(),map)
                rvCategoryList?.adapter = adapter
                adapter.setOnTouchResponse(object :GridAdapter.OnTouchResponse
                {
                    override fun onClick(id:Int){
                        quizClass.getQuizList(10,id,null,null)
                    }
                })

            }
    })
    }
}
