package ry.tech.speedban

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class Kotlin_Fragment : Fragment() {

    private var selectedWord: String? = null
    private var selectedDefinition: String? = null

    private val correctMatches = mapOf(
        "Слово 1" to "Определение 1",
        "Слово 2" to "Определение 2",
        "Слово 3" to "Определение 3",
        "Слово 4" to "Определение 4"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kotlin_, container, false)

        val wordButtons = listOf(
            view.findViewById<Button>(R.id.bt_1),
            view.findViewById<Button>(R.id.bt_2),
            view.findViewById<Button>(R.id.bt_3),
            view.findViewById<Button>(R.id.bt_4)
        )

        val definitionButtons = listOf(
            view.findViewById<Button>(R.id.btDefinition_1),
            view.findViewById<Button>(R.id.btDefinition_2),
            view.findViewById<Button>(R.id.btDefinition_3),
            view.findViewById<Button>(R.id.btDefinition_4),
            view.findViewById<Button>(R.id.btDefinition_5),
            view.findViewById<Button>(R.id.btDefinition_6)
        )

        wordButtons.forEach { button ->
            button.setOnClickListener {
                selectedWord = button.text.toString()
                checkMatch()
            }
        }

        definitionButtons.forEach { button ->
            button.setOnClickListener {
                selectedDefinition = button.text.toString()
                checkMatch()
            }
        }

        return view
    }

    private fun checkMatch() {
        if (selectedWord != null && selectedDefinition != null) {
            if (correctMatches[selectedWord] == selectedDefinition) {
                Toast.makeText(context, "Правильное соответствие!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Неправильное соответствие!", Toast.LENGTH_SHORT).show()
            }
            selectedWord = null
            selectedDefinition = null
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = Kotlin_Fragment()
    }
}

