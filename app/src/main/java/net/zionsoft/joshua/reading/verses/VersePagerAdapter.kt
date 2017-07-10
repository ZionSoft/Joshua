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

package net.zionsoft.joshua.reading.verses

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.zionsoft.joshua.model.domain.Bible

internal class VersePagerAdapter(context: Context, private val verseProvider: VersePage.VerseProvider) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val pages: ArrayList<VersePage> = ArrayList()

    override fun getCount(): Int {
        return Bible.TOTAL_CHAPTER_COUNT
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val page = VersePage(inflater, container, verseProvider)
        page.bind(positionToBookIndex(position), positionToChapterIndex(position))
        pages.add(page)
        container.addView(page.root)
        return page
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val page = obj as VersePage
        page.unbind()
        container.removeView(page.root)
        pages.remove(page)
    }

    override fun isViewFromObject(view: View?, obj: Any?): Boolean {
        return view == (obj as VersePage).root
    }
}
