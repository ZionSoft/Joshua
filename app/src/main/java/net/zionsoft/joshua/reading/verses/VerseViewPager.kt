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
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import io.reactivex.Single
import net.zionsoft.joshua.model.domain.TranslationInfo
import net.zionsoft.joshua.model.domain.Verse
import net.zionsoft.joshua.model.domain.VerseIndex

class VerseViewPager : ViewPager, VerseView, VersePage.VerseProvider {
    interface VerseDetailPresenter {
        fun showVerse(verse: Verse, wordIndex: Int)
    }

    private var presenter: VersePresenter? = null
    private var verseDetailPresenter: VerseDetailPresenter? = null
    private var pagerAdapter: VersePagerAdapter? = null

    private var currentBook: Int = 0
    private var currentChapter: Int = 0
    private val onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            val book = positionToBookIndex(position)
            val chapter = positionToChapterIndex(position)
            if (currentBook == book && currentChapter == chapter) {
                return
            }

            currentBook = book
            currentChapter = chapter
            presenter?.updateReadingProgress(book, chapter)
        }
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        pagerAdapter = VersePagerAdapter(context, this)
        adapter = pagerAdapter

        addOnPageChangeListener(onPageChangeListener)
    }

    fun setPresenter(presenter: VersePresenter) {
        this.presenter = presenter
    }

    fun setVerseDetailPresenter(verseDetailPresenter: VerseDetailPresenter) {
        this.verseDetailPresenter = verseDetailPresenter;
        pagerAdapter?.setVerseDetailPresenter(verseDetailPresenter)
    }

    override fun onReadingProgressUpdated(readingProgress: VerseIndex) {
        if (currentBook == readingProgress.book && currentChapter == readingProgress.chapter) {
            return
        }

        currentBook = readingProgress.book
        currentChapter = readingProgress.chapter
        setCurrentItem(indexToPosition(currentBook, currentChapter), true)
    }

    override fun onCurrentTranslationInfoLoaded(currentTranslation: TranslationInfo) {
        // TODO
    }

    override fun onCurrentTranslationInfoLoadFailed() {
        TODO("not implemented")
    }

    override fun loadVerses(book: Int, chapter: Int): Single<List<Verse>> {
        return presenter?.loadVerses(book, chapter) ?: Single.error(IllegalStateException(""))
    }

    fun onStart() {
        presenter?.takeView(this)

        currentBook = presenter?.getCurrentBook() ?: 0
        currentChapter = presenter?.getCurrentChapter() ?: 0
        presenter?.loadCurrentTranslation()
    }

    fun onStop() {
        presenter?.dropView()
    }
}
