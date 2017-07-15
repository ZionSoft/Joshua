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
import net.zionsoft.joshua.model.domain.StrongWord;
import net.zionsoft.joshua.model.domain.TranslationInfo;
import net.zionsoft.joshua.model.domain.Verse;
import net.zionsoft.joshua.model.domain.VerseIndex;

import java.util.ArrayList;
import java.util.Collections;
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

    public Single<List<Verse>> loadVerses(final int book, final int chapter) {
        return Single.fromCallable(new Callable<List<Verse>>() {
            @Override
            public List<Verse> call() throws Exception {
                // TODO
                if (book != 0 || chapter != 0) {
                    return Collections.emptyList();
                }
                final List<Verse> verses = new ArrayList<>(31);
                final List<Verse.Text.Word> words = new ArrayList<>();
                words.add(new Verse.Text.Word(0, 2, new StrongWord("H07225", "开始、首要", "")));
                words.add(new Verse.Text.Word(3, 1, new StrongWord("H0430", "上帝、神、神明", "")));
                words.add(new Verse.Text.Word(4, 2, new StrongWord("H01254", "创造", "")));
                words.add(new Verse.Text.Word(6, 1, new StrongWord("H08064", "天", "")));
                words.add(new Verse.Text.Word(7, 1, new StrongWord("H0776", "地、邦国、疆界", "")));
                verses.add(new Verse(new VerseIndex(0, 0, 0), new Verse.Text("起初，神创造天地。", words)));
                verses.add(new Verse(new VerseIndex(0, 0, 1), new Verse.Text("地是空虚混沌，渊面黑暗；神的灵运行在水面上。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 2), new Verse.Text("神说：「要有光」，就有了光。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 3), new Verse.Text("神看光是好的，就把光暗分开了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 4), new Verse.Text("神称光为「昼」，称暗为「夜」。有晚上，有早晨，这是头一日。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 5), new Verse.Text("神说：「诸水之间要有空气，将水分为上下。」", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 6), new Verse.Text("神就造出空气，将空气以下的水、空气以上的水分开了。事就这样成了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 7), new Verse.Text("神称空气为「天」。有晚上，有早晨，是第二日。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 8), new Verse.Text("神说：「天下的水要聚在一处，使旱地露出来。」事就这样成了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 9), new Verse.Text("神称旱地为「地」，称水的聚处为「海」。神看着是好的。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 10), new Verse.Text("神说：「地要发生青草和结种子的菜蔬，并结果子的树木，各从其类，果子都包着核。」事就这样成了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 11), new Verse.Text("于是地发生了青草和结种子的菜蔬，各从其类；并结果子的树木，各从其类；果子都包着核。神看着是好的。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 12), new Verse.Text("有晚上，有早晨，是第三日。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 13), new Verse.Text("神说：「天上要有光体，可以分昼夜，作记号，定节令、日子、年岁，", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 14), new Verse.Text("并要发光在天空，普照在地上。」事就这样成了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 15), new Verse.Text("于是神造了两个大光，大的管昼，小的管夜，又造众星，", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 16), new Verse.Text("就把这些光摆列在天空，普照在地上，", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 17), new Verse.Text("管理昼夜，分别明暗。神看着是好的。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 18), new Verse.Text("有晚上，有早晨，是第四日。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 19), new Verse.Text("神说：「水要多多滋生有生命的物；要有雀鸟飞在地面以上，天空之中。」", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 20), new Verse.Text("神就造出大鱼和水中所滋生各样有生命的动物，各从其类；又造出各样飞鸟，各从其类。神看着是好的。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 21), new Verse.Text("神就赐福给这一切，说：「滋生繁多，充满海中的水；雀鸟也要多生在地上。」", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 22), new Verse.Text("有晚上，有早晨，是第五日。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 23), new Verse.Text("神说：「地要生出活物来，各从其类；牲畜、昆虫、野兽，各从其类。」事就这样成了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 24), new Verse.Text("于是神造出野兽，各从其类；牲畜，各从其类；地上一切昆虫，各从其类。神看着是好的。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 25), new Verse.Text("神说：「我们要照着我们的形像、按着我们的样式造人，使他们管理海里的鱼、空中的鸟、地上的牲畜，和全地，并地上所爬的一切昆虫。」", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 26), new Verse.Text("神就照着自己的形像造人，乃是照着他的形像造男造女。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 27), new Verse.Text("神就赐福给他们，又对他们说：「要生养众多，遍满地面，治理这地，也要管理海里的鱼、空中的鸟，和地上各样行动的活物。」", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 28), new Verse.Text("神说：「看哪，我将遍地上一切结种子的菜蔬和一切树上所结有核的果子全赐给你们作食物。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 29), new Verse.Text("至于地上的走兽和空中的飞鸟，并各样爬在地上有生命的物，我将青草赐给它们作食物。」事就这样成了。", Collections.<Verse.Text.Word>emptyList())));
                verses.add(new Verse(new VerseIndex(0, 0, 30), new Verse.Text("神看着一切所造的都甚好。有晚上，有早晨，是第六日。", Collections.<Verse.Text.Word>emptyList())));
                return verses;
            }
        });
    }
}
