package com.example.daggerapp.screens.questiondetail

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.daggerapp.R
import com.example.daggerapp.questions.QuestionWithBody
import com.example.daggerapp.screens.commons.toolbar.MyToolbar
import com.example.daggerapp.screens.commons.viewsmvc.BaseViewMvc
import com.example.daggerapp.screens.imageloader.ImageLoader


class QuestionDetailViewMvc(private val layoutInflater: LayoutInflater, private val imageLoader: ImageLoader,
                            private val parent:ViewGroup? ):BaseViewMvc<QuestionDetailViewMvc.Listener>(layoutInflater,parent,R.layout.layout_question_details) {

    interface Listener{
        fun onBackClicked()
    }
    private val toolbar: MyToolbar
    private val swipeRefresh: SwipeRefreshLayout
    private val txtQuestionBody: TextView
    private val imgUser : ImageView
    private val txtUserName: TextView


    init{

        txtQuestionBody = findViewById(R.id.txt_question_body)

        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            for(listener in listeners) {
                listener.onBackClicked()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false

        imgUser=findViewById(R.id.img_user)
        txtUserName=findViewById(R.id.txt_user_name)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    fun bindQuestionBody(question: QuestionWithBody)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(question.body)
        }
        imageLoader.loadImageUrl(question.owner.imageUrl,imgUser)
        txtUserName.text=question.owner.name
    }




}