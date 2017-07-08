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

package net.zionsoft.joshua.reading.chapters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.zionsoft.joshua.model.BibleReadingModel
import net.zionsoft.joshua.model.domain.TranslationInfo
import net.zionsoft.joshua.model.domain.VerseIndex
import net.zionsoft.joshua.mvp.MVPPresenter

class ChapterPresenter(private val bibleReadingModel: BibleReadingModel) : MVPPresenter<ChapterView>() {
    private var loadCurrentTranslation: Disposable? = null

    override fun onViewDropped() {
        disposeLoadCurrentTranslation()
        super.onViewDropped()
    }

    private fun disposeLoadCurrentTranslation() {
        loadCurrentTranslation?.dispose()
        loadCurrentTranslation = null
    }

    fun loadCurrentTranslation() {
        disposeLoadCurrentTranslation()
        loadCurrentTranslation = bibleReadingModel.loadCurrentTranslation()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TranslationInfo>() {
                    override fun onSuccess(t: TranslationInfo) {
                        loadCurrentTranslation = null
                        getView()?.onTranslationInfoLoaded(t)
                    }

                    override fun onError(e: Throwable) {
                        loadCurrentTranslation = null
                        getView()?.onTranslationInfoLoadFailed()
                    }
                })
    }

    fun getCurrentBook(): Int {
        return bibleReadingModel.currentBook
    }

    fun getCurrentChapter(): Int {
        return bibleReadingModel.currentChapter
    }

    fun updateReadingProgress(book: Int, chapter: Int) {
        bibleReadingModel.updateReadingProgress(VerseIndex(book, chapter, 0))
    }
}
