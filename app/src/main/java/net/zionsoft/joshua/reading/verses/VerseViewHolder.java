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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import net.zionsoft.joshua.R;
import net.zionsoft.joshua.model.domain.Verse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

final class VerseViewHolder extends RecyclerView.ViewHolder {
    private static final SpannableStringBuilder BUILDER = new SpannableStringBuilder();

    @BindView(R.id.index)
    TextView index;

    @BindView(R.id.text)
    TextView text;

    private final VerseViewPager.VerseDetailPresenter verseDetailPresenter;
    private final ArrayList<VerseClickableSpan> clickableSpans = new ArrayList<>();

    VerseViewHolder(LayoutInflater inflater, ViewGroup parent, VerseViewPager.VerseDetailPresenter verseDetailPresenter) {
        super(inflater.inflate(R.layout.item_verse, parent, false));
        ButterKnife.bind(this, itemView);
        this.verseDetailPresenter = verseDetailPresenter;
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    void bind(Verse verse, int totalVerses) {
        synchronized (BUILDER) {
            BUILDER.clear();
            BUILDER.clearSpans();
            final int verseIndex = verse.getIndex().getVerse();
            if (totalVerses >= 100) {
                if (verseIndex < 10) {
                    BUILDER.append("  ");
                } else if (verseIndex < 100) {
                    BUILDER.append(' ');
                }
            } else if (totalVerses >= 10) {
                if (verseIndex < 10) {
                    BUILDER.append(' ');
                }
            }
            BUILDER.append(Integer.toString(verseIndex + 1));
            index.setText(BUILDER.subSequence(0, BUILDER.length()));

            BUILDER.clear();
            BUILDER.clearSpans();
            final Verse.Text verseText = verse.getText();
            BUILDER.append(verseText.getText());
            final List<Verse.Text.Word> verseWords = verseText.getWords();
            final int wordCount = verseWords.size();
            for (int i = 0; i < wordCount; ++i) {
                final VerseClickableSpan clickableSpan;
                if (i >= clickableSpans.size()) {
                    clickableSpan = new VerseClickableSpan(verseDetailPresenter, text.getCurrentTextColor());
                    clickableSpans.add(clickableSpan);
                } else {
                    clickableSpan = clickableSpans.get(i);
                }
                clickableSpan.setData(verse, i);

                final Verse.Text.Word word = verseWords.get(i);
                final int start = word.getPosition();
                final int end = start + word.getLength();
                BUILDER.setSpan(clickableSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            text.setText(BUILDER.subSequence(0, BUILDER.length()));
        }
    }
}
