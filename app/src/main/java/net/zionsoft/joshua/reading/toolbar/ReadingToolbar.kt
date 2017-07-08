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

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import net.zionsoft.joshua.R
import net.zionsoft.joshua.model.domain.TranslationInfo

class ReadingToolbar : Toolbar, ToolbarView {
    private val builder = StringBuilder()
    private var presenter: ToolbarPresenter? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setTitle(R.string.app_name)
    }

    override fun onCurrentTranslationInfoLoaded(translationInfo: TranslationInfo) {
        synchronized(builder, {
            builder.setLength(0)

            val currentBook = translationInfo.books.get(presenter?.getCurrentBook() ?: 0)
            builder.append(currentBook.shortName).append(", ").append((presenter?.getCurrentChapter() ?: 0) + 1)
            title = builder.toString()
        })
    }

    override fun onCurrentTranslationInfoLoadFailed() {
        TODO("not implemented")
    }

    fun setPresenter(presenter: ToolbarPresenter) {
        this.presenter = presenter
    }

    fun onStart() {
        presenter?.takeView(this)
        presenter?.loadCurrentTranslation()
    }

    fun onStop() {
        presenter?.dropView()
    }
}
