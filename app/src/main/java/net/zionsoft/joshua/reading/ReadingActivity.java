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

package net.zionsoft.joshua.reading;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.ViewTreeObserver;

import net.zionsoft.joshua.R;
import net.zionsoft.joshua.model.domain.Verse;
import net.zionsoft.joshua.reading.chapters.ChapterListView;
import net.zionsoft.joshua.reading.chapters.ChapterPresenter;
import net.zionsoft.joshua.reading.toolbar.ReadingToolbar;
import net.zionsoft.joshua.reading.toolbar.ToolbarPresenter;
import net.zionsoft.joshua.reading.verses.VersePresenter;
import net.zionsoft.joshua.reading.verses.VerseViewPager;
import net.zionsoft.joshua.utils.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

public final class ReadingActivity extends BaseActivity implements ReadingView,
        VerseViewPager.VerseDetailPresenter, View.OnClickListener {
    public static Intent newStartIntent(Context context) {
        return new Intent(context, ReadingActivity.class);
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    ReadingToolbar toolbar;

    @BindView(R.id.chapters)
    ChapterListView chapters;

    @BindView(R.id.verse_view_pager)
    VerseViewPager verses;

    @BindView(R.id.verse_detail)
    View verseDetail;

    @BindView(R.id.verse_detail_view)
    VerseDetailView verseDetailView;

    @Inject
    ReadingPresenter presenter;

    @Inject
    ToolbarPresenter toolbarPresenter;

    @Inject
    ChapterPresenter chapterPresenter;

    @Inject
    VersePresenter versePresenter;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reading);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);

        toolbar.setPresenter(toolbarPresenter);
        chapters.setPresenter(chapterPresenter);
        verses.setPresenter(versePresenter);
        verses.setVerseDetailPresenter(this);

        verseDetail.setOnClickListener(this);
        verseDetail.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                verseDetail.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                verseDetail.setTranslationY(verseDetail.getHeight());
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.takeView(this);
        toolbar.onStart();
        chapters.onStart();
        verses.onStart();
    }

    @Override
    protected void onStop() {
        presenter.dropView();
        toolbar.onStop();
        chapters.onStop();
        verses.onStop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (verseDetail.getTranslationY() <= 0.0F) {
            verseDetail.animate().translationY(verseDetail.getHeight());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void showVerse(@NonNull Verse verse) {
        if (verse.getText().getWords().size() == 0) {
            return;
        }
        verseDetailView.setData(verse);
        verseDetail.animate().translationY(0.0F);
    }

    @Override
    public void onClick(View v) {
        if (v == verseDetail) {
            verseDetail.animate().translationY(verseDetail.getHeight());
        }
    }
}
