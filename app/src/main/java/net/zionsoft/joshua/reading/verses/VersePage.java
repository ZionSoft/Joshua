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

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.zionsoft.joshua.R;
import net.zionsoft.joshua.model.domain.Verse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

final class VersePage {
    interface VerseProvider {
        Single<List<Verse>> loadVerses(int book, int chapter);
    }

    @BindView(R.id.verses)
    RecyclerView verses;

    @BindView(R.id.loading_spinner)
    View loadingSpinner;

    final View root;

    private final VerseProvider verseProvider;
    @SuppressWarnings("WeakerAccess")
    final VerseListAdapter adapter;

    @SuppressWarnings("WeakerAccess")
    @Nullable
    Disposable loadVerses;

    VersePage(LayoutInflater inflater, ViewGroup parent, VerseProvider verseProvider,
              VerseViewPager.VerseDetailPresenter verseDetailPresenter) {
        this.verseProvider = verseProvider;

        root = inflater.inflate(R.layout.page_verse_list, parent, false);
        ButterKnife.bind(this, root);

        final Context context = root.getContext();
        verses.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new VerseListAdapter(context, verseDetailPresenter);
        verses.setAdapter(adapter);
    }

    void bind(int book, int chapter) {
        disposeLoadVerses();
        loadVerses = verseProvider.loadVerses(book, chapter)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Verse>>() {
                    @Override
                    public void onSuccess(List<Verse> verses) {
                        loadVerses = null;
                        loadingSpinner.setVisibility(View.GONE);
                        adapter.setVerses(verses);
                        VersePage.this.verses.scrollToPosition(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadVerses = null;
                        // TODO
                    }
                });
    }

    void unbind() {
        disposeLoadVerses();
    }

    private void disposeLoadVerses() {
        if (loadVerses != null) {
            loadVerses.dispose();
            loadVerses = null;
        }
    }
}
