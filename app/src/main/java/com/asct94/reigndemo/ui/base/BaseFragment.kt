package com.asct94.reigndemo.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVariables()
        setupViews()
    }

    open fun setupVariables() {

    }

    open fun setupViews() {

    }

    open fun onFragmentBackPressed(): Boolean = true

}
