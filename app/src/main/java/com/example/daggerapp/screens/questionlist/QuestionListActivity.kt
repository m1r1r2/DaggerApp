package com.example.daggerapp.screens.questionlist

import android.os.Bundle
import com.example.daggerapp.R
import com.example.daggerapp.screens.commons.activities.BaseActivity

class QuestionListActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, QuestionListFragment())
                .commit()
        }

    }

    }