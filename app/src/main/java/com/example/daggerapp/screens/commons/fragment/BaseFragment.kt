package com.example.daggerapp.screens.commons.fragment

import androidx.fragment.app.Fragment
import com.example.daggerapp.screens.commons.activities.BaseActivity


open class BaseFragment: Fragment() {


   private val presentationComponent by lazy {
      (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
   }

   val injector get() = presentationComponent

}