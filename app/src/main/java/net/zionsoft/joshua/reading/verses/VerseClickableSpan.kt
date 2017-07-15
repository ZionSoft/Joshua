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

import android.support.annotation.ColorInt
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import net.zionsoft.joshua.model.domain.Verse

internal class VerseClickableSpan(private val verseDetailPresenter: VerseViewPager.VerseDetailPresenter,
                                  @ColorInt private val textColor: Int) : ClickableSpan() {
    private var verse: Verse? = null
    private var wordIndex: Int = -1

    fun setData(verse: Verse, wordIndex: Int) {
        this.verse = verse
        this.wordIndex = wordIndex
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = textColor
        ds.isUnderlineText = false
    }

    override fun onClick(widget: View?) {
        if (verse == null || wordIndex < 0) {
            return
        }

        verseDetailPresenter.showVerse(verse!!, wordIndex)
    }
}
