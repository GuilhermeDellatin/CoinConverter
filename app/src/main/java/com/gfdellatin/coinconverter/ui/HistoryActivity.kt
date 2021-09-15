package com.gfdellatin.coinconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.gfdellatin.coinconverter.core.extensions.createDialog
import com.gfdellatin.coinconverter.core.extensions.createProgressDialog
import com.gfdellatin.coinconverter.databinding.ActivityHistoryBinding
import com.gfdellatin.coinconverter.presentation.HistoryViewModel
import com.gfdellatin.coinconverter.ui.adapter.HistoryListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {

    private val viewModel by viewModel<HistoryViewModel>()
    private val adapter by lazy { HistoryListAdapter() }
    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }
    private val dialog by lazy { createProgressDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvHistory.adapter = adapter
        binding.rvHistory.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        bindListeners()
        bindObserver()

        lifecycle.addObserver(viewModel)
    }

    private fun bindListeners() {
        binding.btnDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun bindObserver() {
        viewModel.state.observe(this) {
            when (it) {
                HistoryViewModel.State.Loading -> dialog.show()
                is HistoryViewModel.State.Error -> {
                    dialog.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is HistoryViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

}