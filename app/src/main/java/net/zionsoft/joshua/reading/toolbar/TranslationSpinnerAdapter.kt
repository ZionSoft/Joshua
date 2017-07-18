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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import net.zionsoft.joshua.R
import net.zionsoft.joshua.model.domain.TranslationInfo

class TranslationSpinnerAdapter(context: Context, private val translations: List<TranslationInfo>)
    : BaseAdapter(), AdapterView.OnItemSelectedListener {
    private class DropDownViewHolder(inflater: LayoutInflater, convertView: View?, parent: ViewGroup?) {
        val root: View = convertView ?: inflater.inflate(R.layout.item_translation_spinner_drop_down, parent, false)
        private val checkbox: CheckBox
        private val title: TextView

        init {
            checkbox = root.findViewById(R.id.checkbox) as CheckBox
            title = root.findViewById(R.id.title) as TextView
        }

        fun bind(translationInfo: TranslationInfo) {
            title.text = translationInfo.shortName
        }
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return translations.size
    }

    override fun getItem(position: Int): TranslationInfo {
        return translations[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView: TextView = if (convertView == null) {
            inflater.inflate(R.layout.item_translation_spinner_selected, parent, false) as TextView
        } else {
            convertView as TextView
        }
        textView.text = getItem(position).shortName
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: DropDownViewHolder = DropDownViewHolder(inflater, convertView, parent)
        viewHolder.bind(getItem(position))
        return viewHolder.root
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // do nothing
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO
    }
}
