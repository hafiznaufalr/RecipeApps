package net.hafiznaufalr.recipeapps.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import net.hafiznaufalr.recipeapps.R

class CustomEditText: AppCompatEditText {
     lateinit var mClearButtonImage: Drawable

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)

    private fun init(){
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_24dp, null) as Drawable
        setOnTouchListener(OnTouchListener { v, event ->
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float
                val clearButtonEnd: Float
                var isClearButtonClicked = false
                when (layoutDirection) {
                    View.LAYOUT_DIRECTION_RTL -> {
                        clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                        when {
                            event.x < clearButtonEnd -> isClearButtonClicked = true
                        }
                    }
                    else -> {
                        clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                        when {
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }
                when {
                    isClearButtonClicked -> when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_24dp, null) as Drawable
                            showClearButton()
                            return@OnTouchListener true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_24dp, null) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return@OnTouchListener true
                        }
                        else -> return@OnTouchListener false
                    }
                    else -> return@OnTouchListener false
                }
            }
            false
        })

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                when {
                    !s.toString().isEmpty() -> showClearButton()
                }
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, // Top of text.
            mClearButtonImage, null)// Start of text.
    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)// Start of text.
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    constructor(context: Context) : super(context) {
        init()
    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
}
