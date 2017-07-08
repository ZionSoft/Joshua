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

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import net.zionsoft.joshua.R;
import net.zionsoft.joshua.model.domain.TranslationInfo;
import net.zionsoft.joshua.model.domain.VerseIndex;

public class ChapterListView extends ExpandableListView implements ChapterView,
        ChapterListAdapter.Listener, ExpandableListView.OnGroupClickListener {
    private ChapterPresenter chapterPresenter;

    private int currentBook;
    private int currentChapter;

    private ChapterListAdapter chapterListAdapter;
    private int lastExpandedGroup;

    public ChapterListView(Context context) {
        super(context);
        initialize(context);
    }

    public ChapterListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ChapterListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChapterListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        setBackgroundColor(Color.BLACK);
        setDivider(new ColorDrawable(ContextCompat.getColor(context, R.color.dark_gray)));
        setDividerHeight(1);
        setGroupIndicator(null);
        setOnGroupClickListener(this);
        setChildDivider(new ColorDrawable(Color.TRANSPARENT));

        chapterListAdapter = new ChapterListAdapter(context, this);
        setAdapter(chapterListAdapter);
    }

    @Override
    public void onReadingProgressUpdated(@NonNull VerseIndex readingProgress) {
        if (currentBook == readingProgress.getBook() && currentChapter == readingProgress.getChapter()) {
            return;
        }

        currentBook = readingProgress.getBook();
        currentChapter = readingProgress.getChapter();
        refresh();
    }

    private void refresh() {
        chapterListAdapter.setCurrentChapter(currentBook, currentChapter);
        chapterListAdapter.notifyDataSetChanged();

        lastExpandedGroup = currentBook;
        expandGroup(currentBook);
        setSelectedGroup(currentBook);
    }

    @Override
    public void onCurrentTranslationInfoLoaded(@NonNull TranslationInfo currentTranslation) {
        chapterListAdapter.setTranslationInfo(currentTranslation);
        chapterListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCurrentTranslationInfoLoadFailed() {
        // TODO
    }

    @Override
    public void onChapterSelected(int book, int chapter) {
        currentBook = book;
        currentChapter = chapter;
        chapterPresenter.updateReadingProgress(currentBook, currentChapter);

        chapterListAdapter.setCurrentChapter(currentBook, currentChapter);
        chapterListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        parent.smoothScrollToPosition(groupPosition);
        if (parent.isGroupExpanded(groupPosition)) {
            parent.collapseGroup(groupPosition);
        } else {
            parent.expandGroup(groupPosition);
            if (lastExpandedGroup != groupPosition) {
                parent.collapseGroup(lastExpandedGroup);
                lastExpandedGroup = groupPosition;
            }
        }
        return true;
    }

    public void setPresenter(ChapterPresenter chapterPresenter) {
        this.chapterPresenter = chapterPresenter;
    }

    public void onStart() {
        chapterPresenter.takeView(this);

        chapterPresenter.loadCurrentTranslation();
        currentBook = chapterPresenter.getCurrentBook();
        currentChapter = chapterPresenter.getCurrentChapter();

        refresh();
    }

    public void onStop() {
        chapterPresenter.dropView();
    }
}
