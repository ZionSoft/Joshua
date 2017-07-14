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
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.zionsoft.joshua.model.domain.Verse

internal class VerseListAdapter(context: Context, private val verseDetailPresenter: VerseViewPager.VerseDetailPresenter)
    : RecyclerView.Adapter<VerseViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    private var verses: List<Verse>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VerseViewHolder {
        return VerseViewHolder(inflater, parent, verseDetailPresenter)
    }

    override fun onBindViewHolder(holder: VerseViewHolder?, position: Int) {
        holder?.bind(verses?.get(position), itemCount)
    }

    override fun getItemCount(): Int {
        return verses?.size ?: 0
    }

    fun setVerses(verses: List<Verse>) {
        this.verses = verses
        notifyDataSetChanged()
    }
}
