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
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import net.zionsoft.joshua.R
import net.zionsoft.joshua.model.domain.TranslationInfo
import net.zionsoft.joshua.model.domain.VerseIndex


class ReadingToolbar : Toolbar, ToolbarView, AdapterView.OnItemSelectedListener {
    private val builder = StringBuilder()

    private var presenter: ToolbarPresenter? = null
    private var translationAdapter: TranslationSpinnerAdapter? = null
    private var currentTranslation: TranslationInfo? = null
    private var translations: List<TranslationInfo>? = null

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
        inflateMenu(R.menu.activity_reading)
    }

    override fun onReadingProgressUpdated(readingProgress: VerseIndex) {
        updateTitle()
    }

    private fun updateTitle() {
        if (currentTranslation == null) {
            return
        }

        synchronized(builder, {
            builder.setLength(0)

            val currentBook = currentTranslation!!.books[presenter?.getCurrentBook() ?: 0]
            builder.append(currentBook.shortName).append(", ").append((presenter?.getCurrentChapter() ?: 0) + 1)
            title = builder.toString()
        })
    }

    override fun onCurrentTranslationUpdated(currentTranslation: TranslationInfo) {
        this.currentTranslation = currentTranslation
        translationAdapter?.setCurrentTranslation(currentTranslation)
        updateTitle()
    }

    override fun onTranslationsLoaded(translations: List<TranslationInfo>) {
        this.translations = translations

        val translationsSpinner = MenuItemCompat.getActionView(menu.findItem(R.id.translations)) as Spinner
        translationAdapter = TranslationSpinnerAdapter(context, translations)
        if (currentTranslation != null) {
            translationAdapter?.setCurrentTranslation(currentTranslation!!)
        }
        translationsSpinner.adapter = translationAdapter
        translationsSpinner.setSelection(0)
        translationsSpinner.onItemSelectedListener = this
    }

    override fun onTranslationsLoadFailed() {
        TODO("not implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // do nothing
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currentTranslation = translations!![position]
        translationAdapter?.setCurrentTranslation(currentTranslation!!)
        presenter?.updateCurrentTranslation(currentTranslation!!)
    }

    fun setPresenter(presenter: ToolbarPresenter) {
        this.presenter = presenter
    }

    fun onStart() {
        presenter?.takeView(this)
        presenter?.loadTranslations()
    }

    fun onStop() {
        presenter?.dropView()
    }
}
