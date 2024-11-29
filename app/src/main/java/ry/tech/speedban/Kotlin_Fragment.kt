package ry.tech.speedban

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ry.tech.speedban.databinding.FragmentKtBinding

class Kotlin_Fragment : Fragment() {

    private lateinit var bindingClass: FragmentKtBinding

    private var selectedWord: String? = null
    private var selectedWordButton: Button? = null
    private var correctMatches: Map<String, String> = emptyMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingClass = FragmentKtBinding.inflate(inflater, container, false)

        val theme = arguments?.getString("theme") ?: "default"
        setupGame(theme)

        with(bindingClass) {
            bt1.setOnClickListener { onWordClick(bt1.text.toString(), bt1) }
            bt2.setOnClickListener { onWordClick(bt2.text.toString(), bt2) }
            bt3.setOnClickListener { onWordClick(bt3.text.toString(), bt3) }

            btDefinition1.setOnClickListener { onDefinitionClick(btDefinition1.text.toString(), btDefinition1) }
            btDefinition2.setOnClickListener { onDefinitionClick(btDefinition2.text.toString(), btDefinition2) }
            btDefinition3.setOnClickListener { onDefinitionClick(btDefinition3.text.toString(), btDefinition3) }
        }

        return bindingClass.root
    }

    private fun setupGame(theme: String) {
        val (words, definitions, matches) = when (theme) {
            getString(R.string.FLiteracy) -> {
                Triple(
                    listOf(
                        getString(R.string.financial_term_1),
                        getString(R.string.financial_term_2),
                        getString(R.string.financial_term_3)
                    ),
                    listOf(
                        getString(R.string.financial_definition_1),
                        getString(R.string.financial_definition_2),
                        getString(R.string.financial_definition_3)
                    ),
                    mapOf(
                        getString(R.string.financial_term_1) to getString(R.string.financial_definition_1),
                        getString(R.string.financial_term_2) to getString(R.string.financial_definition_2),
                        getString(R.string.financial_term_3) to getString(R.string.financial_definition_3)
                    )
                )
            }
            getString(R.string.DLiteracy) -> {
                Triple(
                    listOf(
                        getString(R.string.digital_term_1),
                        getString(R.string.digital_term_2),
                        getString(R.string.digital_term_3)
                    ),
                    listOf(
                        getString(R.string.digital_definition_1),
                        getString(R.string.digital_definition_2),
                        getString(R.string.digital_definition_3)
                    ),
                    mapOf(
                        getString(R.string.digital_term_1) to getString(R.string.digital_definition_1),
                        getString(R.string.digital_term_2) to getString(R.string.digital_definition_2),
                        getString(R.string.digital_term_3) to getString(R.string.digital_definition_3)
                    )
                )
            }
            getString(R.string.Cybersecurity) -> {
                Triple(
                    listOf(
                        getString(R.string.cybersecurity_term_1),
                        getString(R.string.cybersecurity_term_2),
                        getString(R.string.cybersecurity_term_3)
                    ),
                    listOf(
                        getString(R.string.cybersecurity_definition_1),
                        getString(R.string.cybersecurity_definition_2),
                        getString(R.string.cybersecurity_definition_3)
                    ),
                    mapOf(
                        getString(R.string.cybersecurity_term_1) to getString(R.string.cybersecurity_definition_1),
                        getString(R.string.cybersecurity_term_2) to getString(R.string.cybersecurity_definition_2),
                        getString(R.string.cybersecurity_term_3) to getString(R.string.cybersecurity_definition_3)
                    )
                )
            }
            else -> {
                Toast.makeText(context, "Неизвестная тема", Toast.LENGTH_SHORT).show()
                return
            }
        }

        setButtons(words, definitions)
        correctMatches = matches
    }

    private fun setButtons(words: List<String>, definitions: List<String>) {
        with(bindingClass) {
            bt1.text = words[0]
            bt2.text = words[1]
            bt3.text = words[2]

            btDefinition1.text = definitions[0]
            btDefinition2.text = definitions[1]
            btDefinition3.text = definitions[2]

            // Устанавливаем исходный фон для всех кнопок
            val defaultBackground = ContextCompat.getDrawable(requireContext(), R.drawable.rounded_button)
            bt1.background = defaultBackground
            bt2.background = defaultBackground
            bt3.background = defaultBackground
            btDefinition1.background = defaultBackground
            btDefinition2.background = defaultBackground
            btDefinition3.background = defaultBackground
        }
    }

    private fun onWordClick(word: String, button: Button) {
        if (button.isClickable) {
            selectedWord = word
            selectedWordButton = button
            button.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_pressed)
            showToast("Вы выбрали слово: $word", 1000)
        }
    }

    private fun onDefinitionClick(definition: String, button: Button) {
        val word = selectedWord
        val wordButton = selectedWordButton
        if (word != null && wordButton != null) {
            wordButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_pressed)
            button.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_pressed)

            if (correctMatches[word] == definition) {
                showToast("Правильно!", 1500)
                wordButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_true)
                button.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_true)
                wordButton.isClickable = false
                button.isClickable = false
            } else {
                showToast("Неправильно!", 1500)
                bindingClass.root.postDelayed({
                    wordButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.rounded_button)
                    button.background = ContextCompat.getDrawable(requireContext(), R.drawable.rounded_button)
                }, 1500)
            }
            selectedWord = null
            selectedWordButton = null
        } else {
            showToast("Выберите слово сначала", 1000)
        }
    }
    private fun showToast(message: String, duration: Int) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()

        bindingClass.root.postDelayed({
            toast.cancel()
        }, duration.toLong())
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(theme: String) = Kotlin_Fragment().apply {
            arguments = bundleOf("theme" to theme)
        }
    }
}