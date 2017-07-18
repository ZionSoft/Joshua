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
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import net.zionsoft.joshua.R
import net.zionsoft.joshua.model.domain.Verse

class VerseDetailView : LinearLayout {
    private class VerseDetailPagerAdapter(context: Context) : PagerAdapter() {
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var verse: Verse? = null

        override fun getCount(): Int {
            return verse?.text?.words?.size ?: 0
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = inflater.inflate(R.layout.page_verse_detail, container, false)
            (view.findViewById(R.id.word_detail) as TextView).text =
                    verse?.text?.words?.get(position)?.strongWord?.detail
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }

        override fun isViewFromObject(view: View?, obj: Any?): Boolean {
            return view == obj
        }

        override fun getPageTitle(position: Int): CharSequence {
            val text = verse?.text ?: return ""
            val word = text.words.get(position)
            return text.text.substring(word.position, word.position + word.length)
        }

        fun setVerse(verse: Verse) {
            this.verse = verse
            notifyDataSetChanged()
        }
    }

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: VerseDetailPagerAdapter

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
        viewPager = findViewById(R.id.view_pager) as ViewPager

        adapter = VerseDetailPagerAdapter(context)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun setData(verse: Verse) {
        adapter.setVerse(verse)
    }
}
