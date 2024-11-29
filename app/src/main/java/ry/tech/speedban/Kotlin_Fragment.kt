package ry.tech.speedban

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ry.tech.speedban.databinding.FragmentKtBinding

class Kotlin_Fragment : Fragment() {

    private var _binding: FragmentKtBinding? = null
    private val binding get() = _binding!!

    private var selectedWord: String? = null
    private var correctMatches: Map<String, String> = emptyMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKtBinding.inflate(inflater, container, false)
        val view = binding.root

        val theme = arguments?.getString("theme") ?: "default"
        setupGame(theme)

        binding.bt1.setOnClickListener { onWordClick(binding.bt1.text.toString()) }
        binding.bt2.setOnClickListener { onWordClick(binding.bt2.text.toString()) }
        binding.bt3.setOnClickListener { onWordClick(binding.bt3.text.toString()) }

        binding.btDefinition1.setOnClickListener { onDefinitionClick(binding.btDefinition1.text.toString()) }
        binding.btDefinition2.setOnClickListener { onDefinitionClick(binding.btDefinition2.text.toString()) }
        binding.btDefinition3.setOnClickListener { onDefinitionClick(binding.btDefinition3.text.toString()) }

        return view
    }

    private fun setupGame(theme: String) {
        when (theme) {
            getString(R.string.FLiteracy) -> {
                setButtons(
                    listOf(
                        getString(R.string.financial_term_1),
                        getString(R.string.financial_term_2),
                        getString(R.string.financial_term_3)
                    ),
                    listOf(
                        getString(R.string.financial_definition_1),
                        getString(R.string.financial_definition_2),
                        getString(R.string.financial_definition_3)
                    )
                )
                correctMatches = mapOf(
                    getString(R.string.financial_term_1) to getString(R.string.financial_definition_1),
                    getString(R.string.financial_term_2) to getString(R.string.financial_definition_2),
                    getString(R.string.financial_term_3) to getString(R.string.financial_definition_3)
                )
            }
            getString(R.string.DLiteracy) -> {
                setButtons(
                    listOf(
                        getString(R.string.digital_term_1),
                        getString(R.string.digital_term_2),
                        getString(R.string.digital_term_3)
                    ),
                    listOf(
                        getString(R.string.digital_definition_1),
                        getString(R.string.digital_definition_2),
                        getString(R.string.digital_definition_3)
                    )
                )
                correctMatches = mapOf(
                    getString(R.string.digital_term_1) to getString(R.string.digital_definition_1),
                    getString(R.string.digital_term_2) to getString(R.string.digital_definition_2),
                    getString(R.string.digital_term_3) to getString(R.string.digital_definition_3)
                )
            }
            getString(R.string.Cybersecurity) -> {
                setButtons(
                    listOf(
                        getString(R.string.cybersecurity_term_1),
                        getString(R.string.cybersecurity_term_2),
                        getString(R.string.cybersecurity_term_3)
                    ),
                    listOf(
                        getString(R.string.cybersecurity_definition_1),
                        getString(R.string.cybersecurity_definition_2),
                        getString(R.string.cybersecurity_definition_3)
                    )
                )
                correctMatches = mapOf(
                    getString(R.string.cybersecurity_term_1) to getString(R.string.cybersecurity_definition_1),
                    getString(R.string.cybersecurity_term_2) to getString(R.string.cybersecurity_definition_2),
                    getString(R.string.cybersecurity_term_3) to getString(R.string.cybersecurity_definition_3)
                )
            }
            else -> {
                Toast.makeText(context, "Неизвестная тема", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setButtons(words: List<String>, definitions: List<String>) {
        binding.bt1.text = words[0]
        binding.bt2.text = words[1]
        binding.bt3.text = words[2]

        binding.btDefinition1.text = definitions[0]
        binding.btDefinition2.text = definitions[1]
        binding.btDefinition3.text = definitions[2]
    }

    private fun onWordClick(word: String) {
        selectedWord = word
        Toast.makeText(context, "Вы выбрали слово: $word", Toast.LENGTH_SHORT).show()
    }

    private fun onDefinitionClick(definition: String) {
        val word = selectedWord
        if (word != null) {
            if (correctMatches[word] == definition) {
                Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Неправильно!", Toast.LENGTH_SHORT).show()
            }
            selectedWord = null
        } else {
            Toast.makeText(context, "Выберите слово сначала", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(theme: String) = Kotlin_Fragment().apply {
            arguments = bundleOf("theme" to theme)
        }
    }
}
