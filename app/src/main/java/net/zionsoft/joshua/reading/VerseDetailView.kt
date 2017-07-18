/*
 * Joshua - Read and Study Bible
 * Copyright (C) 2017 ZionSoft
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.zionsoft.joshua.reading

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.design.widget.TabLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import net.zionsoft.joshua.R
import net.zionsoft.joshua.model.domain.Verse

class VerseDetailView : LinearLayout, TabLayout.OnTabSelectedListener {
    private val h = Handler(Looper.getMainLooper())
    private val runnable = Runnable { -> wordDetailContainer.scrollTo(0, 0) }

    private lateinit var tabLayout: TabLayout
    private lateinit var wordDetailContainer: NestedScrollView
    private lateinit var wordDetail: TextView

    private var verse: Verse? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context) {
        orientation = VERTICAL

        View.inflate(context, R.layout.inner_verse_detail_view, this)
        tabLayout = findViewById(R.id.tab_layout) as TabLayout
        tabLayout.addOnTabSelectedListener(this)
        wordDetailContainer = findViewById(R.id.word_detail_container) as NestedScrollView
        wordDetail = findViewById(R.id.word_detail) as TextView
    }

    fun setData(verse: Verse) {
        this.verse = verse

        tabLayout.removeAllTabs()
        wordDetail.text = null

        val text = verse.text.text
        val words = verse.text.words
        val count = words.size
        for (i in 0 until count) {
            val word = words[i]
            tabLayout.addTab(tabLayout.newTab()
                    .setText(text.substring(word.position, word.position + word.length)))
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // do nothing
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // do nothing
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (verse != null && tab != null) {
            wordDetail.text = verse!!.text.words[tab.position].strongWord.detail

            h.removeCallbacks(runnable)
            h.post(runnable)
        } else {
            wordDetail.text = null
        }
    }
}
