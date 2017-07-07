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

package net.zionsoft.joshua.reading.chapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.zionsoft.joshua.R;
import net.zionsoft.joshua.model.domain.Bible;
import net.zionsoft.joshua.model.domain.TranslationInfo;

class ChapterListAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    interface Listener {
        void onChapterSelected(int book, int chapter);
    }

    private static class ViewTag {
        TextView[] textViews;

        ViewTag() {
        }
    }

    private static class ChapterTag {
        int book;
        int chapter;

        ChapterTag() {
        }
    }

    private static final int ROW_CHILD_COUNT = 5;

    private final LayoutInflater inflater;
    private final Listener listener;

    private TranslationInfo translationInfo;
    private int currentBook;
    private int currentChapter;

    ChapterListAdapter(Context context, Listener listener) {
        super();

        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public int getGroupCount() {
        return translationInfo == null ? 0 : Bible.BOOK_COUNT;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (translationInfo == null) {
            return 0;
        }

        final int chapterCount = Bible.CHAPTER_COUNT[groupPosition];
        return chapterCount / ROW_CHILD_COUNT + (chapterCount % ROW_CHILD_COUNT == 0 ? 0 : 1);
    }

    @Override
    public TranslationInfo.BookInfo getGroup(int groupPosition) {
        return translationInfo.getBooks().get(groupPosition);
    }

    @Override
    public Integer getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition * 1000 + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final TextView textView = (TextView) (convertView == null
                ? inflater.inflate(R.layout.item_book_name, parent, false) : convertView);
        textView.setText(getGroup(groupPosition).getName());
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final LinearLayout linearLayout;
        final ViewTag viewTag;
        if (convertView == null) {
            linearLayout = (LinearLayout) inflater.inflate(R.layout.item_chapter_row, parent, false);

            viewTag = new ViewTag();
            viewTag.textViews = new TextView[ROW_CHILD_COUNT];
            for (int i = 0; i < ROW_CHILD_COUNT; ++i) {
                viewTag.textViews[i] = (TextView) linearLayout.getChildAt(i);
                viewTag.textViews[i].setOnClickListener(this);
            }
            linearLayout.setTag(viewTag);
        } else {
            linearLayout = (LinearLayout) convertView;
            viewTag = (ViewTag) linearLayout.getTag();
        }

        final int chapterCount = Bible.CHAPTER_COUNT[groupPosition];
        for (int i = 0; i < ROW_CHILD_COUNT; ++i) {
            final int chapter = childPosition * ROW_CHILD_COUNT + i;
            final TextView textView = viewTag.textViews[i];
            if (chapter >= chapterCount) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setSelected(groupPosition == currentBook && chapter == currentChapter);
                textView.setText(Integer.toString(chapter + 1));

                ChapterTag chapterTag = (ChapterTag) textView.getTag();
                if (chapterTag == null) {
                    chapterTag = new ChapterTag();
                    textView.setTag(chapterTag);
                }
                chapterTag.book = groupPosition;
                chapterTag.chapter = chapter;
            }
        }

        return linearLayout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    void setTranslationInfo(TranslationInfo translationInfo) {
        this.translationInfo = translationInfo;
    }

    void setCurrentChapter(int currentBook, int currentChapter) {
        this.currentBook = currentBook;
        this.currentChapter = currentChapter;
    }

    @Override
    public void onClick(View v) {
        final ChapterTag chapterTag = (ChapterTag) v.getTag();
        if (currentBook != chapterTag.book || currentChapter != chapterTag.chapter) {
            listener.onChapterSelected(chapterTag.book, chapterTag.chapter);
        }
    }
}

