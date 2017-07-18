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

package net.zionsoft.joshua.reading.toolbar

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.zionsoft.joshua.model.BibleReadingModel
import net.zionsoft.joshua.model.domain.TranslationInfo
import net.zionsoft.joshua.reading.BaseReadingPresenter

class ToolbarPresenter(bibleReadingModel: BibleReadingModel) : BaseReadingPresenter<ToolbarView>(bibleReadingModel) {
    private var loadTranslations: Disposable? = null

    override fun onViewDropped() {
        disposeLoadTranslations()
        super.onViewDropped()
    }

    private fun disposeLoadTranslations() {
        loadTranslations?.dispose()
        loadTranslations = null
    }

    fun loadTranslations() {
        disposeLoadTranslations()
        loadTranslations = bibleReadingModel.loadTranslations()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<TranslationInfo>>() {
                    override fun onSuccess(t: List<TranslationInfo>) {
                        loadTranslations = null
                        view?.onTranslationsLoaded(t)
                    }

                    override fun onError(e: Throwable) {
                        loadTranslations = null
                        view?.onTranslationsLoadFailed()
                    }
                })
    }
}
