package com.ext.customappbar.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.ext.customappbar.R

class PrimaryAppBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val toolbar: Toolbar

    private var backClick: (() -> Unit)? = null
    private var actionClick: (() -> Unit)? = null

    init {
        // Inflate toolbar layout
        LayoutInflater.from(context).inflate(R.layout.appbar_primary, this, true)

        toolbar = findViewById(R.id.primaryToolbar)

        // Read XML attributes
        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.PrimaryAppBarView)

            // Title
            val titleText = typedArray.getString(R.styleable.PrimaryAppBarView_title)
            toolbar.title = titleText ?: ""

            // Background Color
            val bgColor = typedArray.getColor(
                R.styleable.PrimaryAppBarView_backgroundColor,
                toolbar.solidColor
            )
            toolbar.setBackgroundColor(bgColor)

            // Title Color
            val titleColor = typedArray.getColor(
                R.styleable.PrimaryAppBarView_titleColor,
                context.getColor(android.R.color.white)
            )
            toolbar.setTitleTextColor(titleColor)

            // Back Button
            val showBack =
                typedArray.getBoolean(R.styleable.PrimaryAppBarView_showBack, false)

            if (showBack) {
                showBackButton()
            }

            // Action Icon
            val actionIcon =
                typedArray.getResourceId(R.styleable.PrimaryAppBarView_actionIcon, 0)

            if (actionIcon != 0) {
                setActionIcon(actionIcon)
            }

            typedArray.recycle()
        }
    }

    // -----------------------------
    // Back Button
    // -----------------------------
    private fun showBackButton() {
        toolbar.setNavigationIcon(
            androidx.appcompat.R.drawable.abc_ic_ab_back_material
        )

        toolbar.setNavigationOnClickListener {
            backClick?.invoke()
        }
    }

    fun setOnBackClick(listener: () -> Unit) {
        backClick = listener
        showBackButton()
    }

    // -----------------------------
    // Action Button
    // -----------------------------
    private fun setActionIcon(iconRes: Int) {
        toolbar.menu.clear()

        toolbar.menu.add("Action").apply {
            setIcon(iconRes)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }

        toolbar.setOnMenuItemClickListener {
            actionClick?.invoke()
            true
        }
    }

    fun setOnActionClick(listener: () -> Unit) {
        actionClick = listener
    }

    // -----------------------------
    // Title Setter
    // -----------------------------
    fun setTitle(title: String) {
        toolbar.title = title
    }
}
