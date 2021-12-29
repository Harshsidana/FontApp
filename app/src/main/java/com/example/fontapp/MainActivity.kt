package com.example.fontapp

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.fontapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    enum class FONT {
        REGULAR, MEDIUM, BOLD
    }

    enum class TYPE {
        TEXT, BUTTON
    }

    enum class SIZE(val value: Int) {
        N8(8), N9(9), N10(10), N11(11), N12(12), N13(13), N14(14), N15(15), N16(16), N17(17), N18(18),
        N19(19), N20(20), N21(21), N22(22), N23(23), N24(24), N25(25), N26(26), N27(27), N28(28), N29(
            29
        ),
        N30(30),
        N31(31), N32(32)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sizeArray = arrayListOf<String>()
        enumValues<SIZE>().forEach { sizeArray.add(it.value.toString()) }
        val fontArray = arrayListOf<String>()
        enumValues<FONT>().forEach { fontArray.add(it.name) }
        val typeArray = arrayListOf<String>()
        enumValues<TYPE>().forEach { typeArray.add(it.name) }
        val sizeAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_item, sizeArray)
        val fontAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_item, fontArray)
        val typeAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_item, typeArray)
        sizeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        fontAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        typeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.sizeSpinner.adapter = sizeAdapter
        binding.fontSpinner.adapter = fontAdapter
        binding.typeSpinner.adapter = typeAdapter
        binding.btnFont.setOnClickListener {
            binding.fontSpinner.selectedItem.toString()
            binding.fontSpinner.selectedItemPosition
            getFontCode(FONT.values()[binding.fontSpinner.selectedItemPosition],SIZE.values()[binding.sizeSpinner.selectedItemPosition],TYPE.values()[binding.typeSpinner.selectedItemPosition])
        }

    }

    private fun getFontCode(font: FONT, size: SIZE, type: TYPE = TYPE.TEXT) {
        val aliasToken = "type" + getSize(size).value + getFontWeight(font).toString()
        binding.tvResult.text = getGlobalToken(aliasToken,type)
    }

    private fun getFontWeight(font: FONT): Int {
        return when (font) {
            FONT.REGULAR -> 0
            FONT.MEDIUM -> 1
            FONT.BOLD -> 2
        }
    }

    private fun getSize(size: SIZE): SIZE {
        return when (size) {
            SIZE.N8, SIZE.N9, SIZE.N10, SIZE.N11 -> {
                SIZE.N10
            }
            SIZE.N12, SIZE.N13 -> {
                SIZE.N12
            }
            SIZE.N14, SIZE.N15 -> {
                SIZE.N14
            }
            SIZE.N16, SIZE.N17, SIZE.N18 -> {
                SIZE.N16
            }
            SIZE.N19, SIZE.N20, SIZE.N21, SIZE.N22 -> {
                SIZE.N20
            }
            SIZE.N23, SIZE.N24, SIZE.N25, SIZE.N26, SIZE.N27, SIZE.N28 -> {
                SIZE.N24
            }
            SIZE.N29, SIZE.N30, SIZE.N31, SIZE.N32 -> {
                SIZE.N32
            }

        }
    }

    private fun getGlobalToken(aliasToken: String, type: TYPE = TYPE.TEXT): String {
        return when (aliasToken) {
            "type322" -> "titleXLarge"
            "type242" -> "titleLarge"
            "type202" -> "titleMedium"
            "type162" -> {
                return if (type == TYPE.TEXT) {
                    "titleSmall"
                } else {
                    "buttonLarge"
                }
            }
            "type142" -> {
                return if (type == TYPE.TEXT) {
                    "titleXSmall"
                } else {
                    "buttonMedium"
                }
            }
            "type161" -> "subtitleLarge"
            "type141" -> "subtitleMedium"
            "type121" -> "subtitleSmall"
            "type160" -> "bodyLarge"
            "type140" -> "bodyMedium"
            "type120" -> "bodySmall"
            "type100" -> "bodyXSmall"
            "type122" -> {
                return if (type == TYPE.TEXT) {
                    "labelMedium"
                } else {
                    "buttonSmall"
                }
            }
            "type102" -> "labelSmall"


            else -> ""
        }

    }


}