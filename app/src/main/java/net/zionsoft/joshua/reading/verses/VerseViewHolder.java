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

package net.zionsoft.joshua.reading.verses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import net.zionsoft.joshua.R;
import net.zionsoft.joshua.model.domain.Verse;

import butterknife.BindView;
import butterknife.ButterKnife;

final class VerseViewHolder extends RecyclerView.ViewHolder {
    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    @BindView(R.id.index)
    TextView index;

    @BindView(R.id.text)
    TextView text;

    VerseViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_verse, parent, false));
        ButterKnife.bind(this, itemView);
    }

    void bind(Verse verse, int totalVerses) {
        synchronized (STRING_BUILDER) {
            STRING_BUILDER.setLength(0);
            final int verseIndex = verse.getIndex().getVerse();
            if (totalVerses >= 100) {
                if (verseIndex < 100) {
                    STRING_BUILDER.append("  ");
                } else if (verseIndex < 10) {
                    STRING_BUILDER.append(' ');
                }
            } else if (totalVerses >= 10) {
                if (verseIndex < 10) {
                    STRING_BUILDER.append(' ');
                }
            }
            STRING_BUILDER.append(verseIndex + 1);
            index.setText(STRING_BUILDER.toString());
        }

        text.setText(verse.getText());
    }
}
