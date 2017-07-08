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

package net.zionsoft.joshua.model;

import net.zionsoft.joshua.model.domain.Bible;
import net.zionsoft.joshua.model.domain.TranslationInfo;
import net.zionsoft.joshua.model.domain.VerseIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

@Singleton
public final class BibleReadingModel {
    private final BehaviorSubject<VerseIndex> readingProgressSubject = BehaviorSubject.create();
    private VerseIndex currentReadingProgress;

    @Inject
    public BibleReadingModel() {
        // TODO Reads it from local storage.
        currentReadingProgress = new VerseIndex(0, 0, 0);
        readingProgressSubject.onNext(currentReadingProgress);
    }

    public Single<TranslationInfo> loadCurrentTranslation() {
        return Single.fromCallable(new Callable<TranslationInfo>() {
            @Override
            public TranslationInfo call() throws Exception {
                // TODO
                final List<TranslationInfo.BookInfo> books = new ArrayList<>(Bible.BOOK_COUNT);
                books.add(new TranslationInfo.BookInfo(0, "创世记", "创", 50));
                books.add(new TranslationInfo.BookInfo(1, "出埃及记", "出", 40));
                books.add(new TranslationInfo.BookInfo(2, "利未记", "利", 27));
                books.add(new TranslationInfo.BookInfo(3, "民数记", "民", 36));
                books.add(new TranslationInfo.BookInfo(4, "申命记", "申", 34));
                books.add(new TranslationInfo.BookInfo(5, "约书亚记", "书", 24));
                books.add(new TranslationInfo.BookInfo(6, "士师记", "士", 21));
                books.add(new TranslationInfo.BookInfo(7, "路得记", "得", 4));
                books.add(new TranslationInfo.BookInfo(8, "撒母耳记上", "撒上", 31));
                books.add(new TranslationInfo.BookInfo(9, "撒母耳记下", "撒下", 24));
                books.add(new TranslationInfo.BookInfo(10, "列王纪上", "王上", 22));
                books.add(new TranslationInfo.BookInfo(11, "列王纪下", "王下", 25));
                books.add(new TranslationInfo.BookInfo(12, "历代志上", "代上", 29));
                books.add(new TranslationInfo.BookInfo(13, "历代志下", "代下", 36));
                books.add(new TranslationInfo.BookInfo(14, "以斯拉记", "拉", 10));
                books.add(new TranslationInfo.BookInfo(15, "尼希米记", "尼", 13));
                books.add(new TranslationInfo.BookInfo(16, "以斯帖记", "斯", 10));
                books.add(new TranslationInfo.BookInfo(17, "约伯记", "伯", 42));
                books.add(new TranslationInfo.BookInfo(18, "诗篇", "诗", 150));
                books.add(new TranslationInfo.BookInfo(19, "箴言", "箴", 31));
                books.add(new TranslationInfo.BookInfo(20, "传道书", "传", 12));
                books.add(new TranslationInfo.BookInfo(21, "雅歌", "歌", 8));
                books.add(new TranslationInfo.BookInfo(22, "以赛亚书", "赛", 66));
                books.add(new TranslationInfo.BookInfo(23, "耶利米书", "耶", 52));
                books.add(new TranslationInfo.BookInfo(24, "耶利米哀歌", "哀", 5));
                books.add(new TranslationInfo.BookInfo(25, "以西结书", "结", 48));
                books.add(new TranslationInfo.BookInfo(26, "但以理书", "但", 12));
                books.add(new TranslationInfo.BookInfo(27, "何西阿书", "何", 14));
                books.add(new TranslationInfo.BookInfo(28, "约珥书", "珥", 3));
                books.add(new TranslationInfo.BookInfo(29, "阿摩司书", "摩", 9));
                books.add(new TranslationInfo.BookInfo(30, "俄巴底亚书", "俄", 1));
                books.add(new TranslationInfo.BookInfo(31, "约拿书", "拿", 4));
                books.add(new TranslationInfo.BookInfo(32, "弥迦书", "弥", 7));
                books.add(new TranslationInfo.BookInfo(33, "那鸿书", "鸿", 3));
                books.add(new TranslationInfo.BookInfo(34, "哈巴谷书", "哈", 3));
                books.add(new TranslationInfo.BookInfo(35, "西番雅书", "番", 3));
                books.add(new TranslationInfo.BookInfo(36, "哈该书", "该", 2));
                books.add(new TranslationInfo.BookInfo(37, "撒迦利亚书", "亚", 14));
                books.add(new TranslationInfo.BookInfo(38, "玛拉基书", "玛", 4));
                books.add(new TranslationInfo.BookInfo(39, "马太福音", "太", 28));
                books.add(new TranslationInfo.BookInfo(40, "马可福音", "可", 16));
                books.add(new TranslationInfo.BookInfo(41, "路加福音", "路", 24));
                books.add(new TranslationInfo.BookInfo(42, "约翰福音", "约", 21));
                books.add(new TranslationInfo.BookInfo(43, "使徒行传", "徒", 28));
                books.add(new TranslationInfo.BookInfo(44, "罗马书", "罗", 16));
                books.add(new TranslationInfo.BookInfo(45, "哥林多前书", "林前", 16));
                books.add(new TranslationInfo.BookInfo(46, "哥林多后书", "林后", 13));
                books.add(new TranslationInfo.BookInfo(47, "加拉太书", "加", 6));
                books.add(new TranslationInfo.BookInfo(48, "以弗所书", "弗", 6));
                books.add(new TranslationInfo.BookInfo(49, "腓利比书", "腓", 4));
                books.add(new TranslationInfo.BookInfo(50, "歌罗西书", "西", 4));
                books.add(new TranslationInfo.BookInfo(51, "帖撒罗尼迦前书", "帖前", 5));
                books.add(new TranslationInfo.BookInfo(52, "帖撒罗尼迦后书", "帖后", 3));
                books.add(new TranslationInfo.BookInfo(53, "提摩太前书", "提前", 6));
                books.add(new TranslationInfo.BookInfo(54, "提摩太后书", "提后", 4));
                books.add(new TranslationInfo.BookInfo(55, "提多书", "多", 3));
                books.add(new TranslationInfo.BookInfo(56, "腓利门书", "门", 1));
                books.add(new TranslationInfo.BookInfo(57, "希伯来书", "来", 13));
                books.add(new TranslationInfo.BookInfo(58, "雅各书", "雅", 5));
                books.add(new TranslationInfo.BookInfo(59, "彼得前书", "彼前", 5));
                books.add(new TranslationInfo.BookInfo(60, "彼得后书", "彼后", 3));
                books.add(new TranslationInfo.BookInfo(61, "约翰一书", "约壹", 5));
                books.add(new TranslationInfo.BookInfo(62, "约翰二书", "约贰", 1));
                books.add(new TranslationInfo.BookInfo(63, "约翰三书", "约叁", 1));
                books.add(new TranslationInfo.BookInfo(64, "犹大书", "犹", 1));
                books.add(new TranslationInfo.BookInfo(65, "启示录", "启", 22));
                return new TranslationInfo("和合本圣经", "和合本", books);
            }
        });
    }

    public int getCurrentBook() {
        return currentReadingProgress.getBook();
    }

    public int getCurrentChapter() {
        return currentReadingProgress.getChapter();
    }

    public void updateReadingProgress(VerseIndex verseIndex) {
        // TODO Saves it to local storage.
        currentReadingProgress = verseIndex;
        readingProgressSubject.onNext(verseIndex);
    }

    public Observable<VerseIndex> observeReadingProgress() {
        return readingProgressSubject;
    }
}
