package dev.pegasus.languagecleanarchitecture.language.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.pegasus.languagecleanarchitecture.R
import dev.pegasus.languagecleanarchitecture.databinding.ActivityMainBinding
import dev.pegasus.languagecleanarchitecture.language.data.repository.LanguageRepository
import dev.pegasus.languagecleanarchitecture.language.domain.usecases.UseCaseLanguage
import dev.pegasus.languagecleanarchitecture.language.presentation.adapter.AdapterLanguage
import dev.pegasus.languagecleanarchitecture.language.presentation.viewmodels.ViewModelLanguage
import dev.pegasus.languagecleanarchitecture.language.presentation.viewmodels.ViewModelLanguageFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapterLanguage by lazy { AdapterLanguage(itemClick) }

    // MVVM
    private val languageRepository = LanguageRepository()
    private val useCaseLanguage = UseCaseLanguage(languageRepository)
    private val viewModel by viewModels<ViewModelLanguage> { ViewModelLanguageFactory(useCaseLanguage) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()
        initObservers()
    }

    private fun fullScreen() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initObservers() {
        binding.rvLanguagesLanguage.adapter = adapterLanguage
        viewModel.languageLiveData.observe(this) {
            adapterLanguage.submitList(it)
        }
    }

    private val itemClick: (selectedCode: String) -> Unit = {
        viewModel.updateLanguage(selectedCode = it)
    }
}