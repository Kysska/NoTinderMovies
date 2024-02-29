package com.example.filmslikes.presentation.customview

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.notindermovies.R

class CustomViewCategory(context: Context, text: String) : AppCompatTextView(context) {

    private val textCategory = text
    init {
        setText(textCategory)

        setBackgroundResource(R.drawable.text_view_category)

        gravity = Gravity.CENTER

        setTextColor(resources.getColor(R.color.white))

        setTypeface(typeface, Typeface.BOLD)

    }
}