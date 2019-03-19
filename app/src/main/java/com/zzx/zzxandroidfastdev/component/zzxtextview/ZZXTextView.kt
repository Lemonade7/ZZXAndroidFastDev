package com.zzx.zzxandroidfastdev.component.zzxtextview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zzx.zzxandroidfastdev.R

/**
 * ZZX的自定义TextView
 * <p>version1: 支持文字的更多展开功能</p>
 * @author ZZX
 * @date 2019/3/19
 */
class ZZXTextView : LinearLayout {
    private var mContext: Context = context
    private var mTextView: TextView? = null
    private var mShowMoreImageId: Int = R.drawable.arrow_down
    private var maxLines: Int = 2
    private var mShowMoreImageView: ImageView? = null
    private var defaultTextSize: Int = 14
    private var defaultTextColor: Int = Color.BLACK
    private var defaultMaxLines: Int = 2
    private var defaultShowMoreImageId: Int = R.drawable.arrow_down

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        initAttrs(context, attrs)
        textListener()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
        initAttrs(context, attrs)
        textListener()
    }

    private fun initView() {
        orientation = VERTICAL
        gravity = Gravity.RIGHT
        val padding = dip2px(mContext, 10f)
        mTextView = TextView(mContext)
        mTextView?.setLineSpacing(3f, 1f)
        addView(mTextView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        mShowMoreImageView = ImageView(mContext)
        mShowMoreImageView!!.setPadding(padding, padding, padding, padding)
        mShowMoreImageView!!.setImageResource(mShowMoreImageId)
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        addView(mShowMoreImageView, layoutParams)
    }

    private fun initAttrs(context: Context?, attrs: AttributeSet?) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.ZZXTextViewStyle)
        val textSize = typedArray?.getDimensionPixelSize(R.styleable.ZZXTextViewStyle_textSize, defaultTextSize)
        val textColor = typedArray?.getColor(R.styleable.ZZXTextViewStyle_textColor, defaultTextColor)
        maxLines = typedArray?.getInt(R.styleable.ZZXTextViewStyle_maxLines, defaultMaxLines)!!
        val text = typedArray.getString(R.styleable.ZZXTextViewStyle_text)
        mShowMoreImageId = typedArray.getResourceId(R.styleable.ZZXTextViewStyle_show_more_image, defaultShowMoreImageId)
        setView(text, textSize, textColor, maxLines)
        typedArray.recycle()
    }

    private fun setView(text: String?, textSize: Int?, textColor: Int?, maxLines: Int?) {
        mShowMoreImageView!!.setImageResource(mShowMoreImageId)
        mTextView!!.text = text
        mTextView!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize?.toFloat()!!)
        mTextView!!.setTextColor(textColor!!)
        mTextView!!.height = mTextView!!.lineHeight * maxLines!!
        post {
            Runnable {
                when (mTextView!!.lineCount > maxLines) {
                    true -> {
                        mShowMoreImageView!!.visibility = View.VISIBLE
                    }
                    false -> {
                        mShowMoreImageView!!.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun textListener() {
        var isShown = false
        setOnClickListener {
            isShown = !isShown
            mTextView!!.clearAnimation()
            val deltaValue: Int
            val startValue = mTextView!!.height
            val duration = 1000
            if (isShown) {
                deltaValue = mTextView!!.lineHeight * mTextView!!.lineCount - startValue
                val animation = RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                animation.duration = duration.toLong()
                animation.fillAfter = true
                mShowMoreImageView!!.startAnimation(animation)
            } else {
                deltaValue = mTextView!!.lineHeight * maxLines - startValue
                val animation = RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                animation.duration = duration.toLong()
                animation.fillAfter = true
                mShowMoreImageView!!.startAnimation(animation)
            }
            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    mTextView!!.height = (startValue + deltaValue * interpolatedTime).toInt()
                }
            }
            animation.interpolator = BounceInterpolator()
            animation.duration = duration.toLong()
            mTextView!!.startAnimation(animation)
        }
    }

    fun getTextView(): TextView? {
        return mTextView
    }

    fun setText(charSequence: CharSequence) {
        mTextView!!.text = charSequence
    }

    /**
     * 设置展开更多图标
     * @param gravityPosition 位置
     */
    fun setMoreImageGravity(gravityPosition: Int) {
        gravity = gravityPosition
    }

    companion object {
        fun dip2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }
}