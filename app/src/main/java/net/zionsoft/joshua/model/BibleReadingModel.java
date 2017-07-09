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
                verses.add(new Verse(new VerseIndex(0, 0, 0), "起初<WAH09002><WH07225>，神<WH0430>创造<WH01254><WTH8804>{<WH0853>}天<WH08064>{<WH0853>}地<WH0776>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 1), "地<WH0776>是<WH01961><WTH8804>空虚<WH08414>混沌<WH0922>，渊<WH08415>面{<WAH05921>}<WH06440>黑暗<WH02822>；神<WH0430>的灵<WH07307>运行<WH07363><WTH8764>在<WH05921>水<WH04325>面<WH06440>上。"));
                verses.add(new Verse(new VerseIndex(0, 0, 2), "神<WH0430>说<WH0559><WTH8799>：「要有<WH01961><WTH8799>光<WH0216>」，就有了<WAH01961><WTH8799>光<WH0216>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 3), "神<WH0430>看<WH07200><WTH8799>{<WH0853>}光<WH0216>{<WH03588>}是好的<WH02896>，{<WH0430>}就把{<WH0996>}光<WH0216>{<WH0996>}暗<WH02822>分开了<WH0914><WTH8686>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 4), "神<WH0430>称<WH07121><WTH8799>光<WAH09001><WH0216>为「昼<WH03117>」，称<WH07121><WTH8804>暗<WAH09001><WH02822>为「夜<WH03915>」。有<WAH01961><WTH8799>晚上<WH06153>，有<WAH01961><WTH8799>早晨<WH01242>，这是头一<WH0259>日<WH03117>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 5), "神<WH0430>说<WH0559><WTH8799>：「诸水<WH04325>之间<WAH09002><WH08432>要有<WAH01961><WTH8799>空气<WH07549>，将{<WAH01961>}{<WTH8799>}水<WAH0996><WH04325>分<WH0914><WTH8688>为上下{<WAH09001>}{<WH04325>}。」"));
                verses.add(new Verse(new VerseIndex(0, 0, 6), "神<WH0430>就造出<WH06213><WTH8799>{<WAH0853>}空气<WH07549>，将<WAH0834>空气<WAH09001><WH07549>以下<WAH04480><WH08478>的水<WAH0996><WH04325>、空气<WAH09001><WH07549>{<WH0834>}以上<WAH04480><WH05921>的水<WAH0996><WH04325>分开了<WH0914><WTH8686>。事就这样<WH03651>成了<WAH01961><WTH8799>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 7), "神<WH0430>称<WH07121><WTH8799>空气<WAH09001><WH07549>为「天<WH08064>」。有<WAH01961><WTH8799>晚上<WH06153>，有<WAH01961><WTH8799>早晨<WH01242>，是第二<WH08145>日<WH03117>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 8), "神<WH0430>说<WH0559><WTH8799>：「天<WH08064>下<WAH04480><WAH08478>的水<WH04325>要聚<WH06960><WTH8735>在<WH0413>一<WH0259>处<WH04725>，使旱地<WH03004>露出来<WH07200><WTH8735>。」事就这样<WAH03651>成了<WAH01961><WTH8799>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 9), "神<WH0430>称<WH07121><WTH8799>旱地<WAH09001><WH03004>为「地<WH0776>」，称<WH07121><WTH8804>水<WH04325>的聚处<WAH09001><WH04723>为「海<WH03220>」。神<WH0430>看着<WH07200><WTH8799>{<WAH03588>}是好的<WH02896>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 10), "神<WH0430>说<WH0559><WTH8799>：「地<WH0776>要发生<WH01876><WTH8686>青草<WH01877>和结<WH02232><WTH8688>种子<WH02233>的菜蔬<WH06212>，并结<WH06213><WTH8802>果子<WH06529>的树木<WH06529><WH06086>，各从其类<WAH09001><WH04327>，果子{<WH0834>}都包着核<WH02233>{<WAH09002>}。」{<WAH05921>}{<WH0776>}事就这样<WAH03651>成了<WAH01961><WTH8799>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 11), "于是地<WH0776>发生了<WH03318><WTH8686>青草<WH01877>和结<WH02232><WTH8688>种子<WH02233>的菜蔬<WH06212>，各从其类<WAH09001><WH04327>；并结<WH06213><WTH8802>果子<WH06529>的树木<WH06086>，各从其类<WAH09001><WH04327>；果子{<WAH0834>}都包着核<WH02233>{<WAH09002>}。神<WH0430>看着<WH07200><WTH8799>{<WAH03588>}是好的<WH02896>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 12), "有<WAH01961><WTH8799>晚上<WH06153>，有<WAH01961><WTH8799>早晨<WH01242>，是第三<WH07992>日<WH03117>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 13), "神<WH0430>说<WH0559><WTH8799>：「天上<WAH09002><WH07549><WH08064>要有<WAH01961><WTH8799>光体<WH03974>，可以分<WAH09001><WH0914><WTH8687>{<WAH0996>}昼<WH03117>{<WAH0996>}夜<WH03915>，{<WAH01961>}{<WTH8804>}作记号<WAH09001><WH0226>，定节令<WAH09001><WH04150>、日子<WAH09001><WH03117>、年岁<WH08141>，"));
                verses.add(new Verse(new VerseIndex(0, 0, 14), "并要<WAH01961><WTH8804>发光<WAH09001><WH03974>在天<WH08064>空<WAH09002><WH07549>，普照<WAH09001><WH0215><WTH8687>在<WAH05921>地上<WH0776>。」事就这样<WAH03651>成了<WAH01961><WTH8799>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 15), "于是神<WH0430>造了<WH06213><WTH8799>{<WAH0853>}两个<WH08147>大<WH01419>光<WH03974>，{<WAH0853>}大的<WH01419>{<WH03974>}管<WAH09001><WH04475>昼<WH03117>，{<WAH0853>}小的<WH06996>{<WH03974>}管<WAH09001><WH04475>夜<WH03915>，又造{<WAH0853>}众星<WH03556>，"));
                verses.add(new Verse(new VerseIndex(0, 0, 16), "{<WAH0430>}就把这些光<WAH0853>摆列<WH05414><WTH8799>在天<WH08064>空<WAH09002><WH07549>，普照<WAH09001><WH0215><WTH8687>在<WAH05921>地上<WH0776>，"));
                verses.add(new Verse(new VerseIndex(0, 0, 17), "管理<WAH09001><WH04910><WTH8800>昼<WAH09002><WH03117>夜<WAH09002><WH03915>，分别<WAH09001><WH0914><WTH8687>{<WAH0996>}明<WH0216>{<WAH0996>}暗<WH02822>。神<WH0430>看着<WH07200><WTH8799>{<WAH03588>}是好的<WH02896>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 18), "有<WAH01961><WTH8799>晚上<WH06153>，有<WAH01961><WTH8799>早晨<WH01242>，是第四<WH07243>日<WH03117>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 19), "神<WH0430>说<WH0559><WTH8799>：「水<WH04325>要多多滋生<WH08317><WTH8799>有生命<WH05315><WH02416>的物<WH08318>；要有雀鸟<WH05775>飞<WH05774><WTH8787>在<WH05921>地面<WH0776>以上，天<WH08064>空<WH07549>之中<WAH05921><WH06440>。」"));
                verses.add(new Verse(new VerseIndex(0, 0, 20), "神<WH0430>就造出<WH01254><WTH8799>{<WAH0853>}大<WH01419>鱼<WH08577>和<WAH0853>水<WH04325>中所<WAH0834>滋生<WH08317><WTH8804>各样<WAH03605>有生命<WH02416>的动<WH07430><WTH8802>物<WH05315>，各从其类<WAH09001><WH04327>；又造出{<WAH0853>}各样<WAH03605>飞<WH03671>鸟<WH05775>，各从其类<WAH09001><WH04327>。神<WH0430>看着<WH07200><WTH8799>{<WAH03588>}是好的<WH02896>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 21), "神<WH0430>就赐福<WH01288><WTH8762>给这一切{<WAH0853>}，说<WAH09001><WH0559><WTH8800>：「滋生<WH06509><WTH8798>繁多<WH07235><WTH8798>，充满<WH04390><WTH8798>{<WAH0853>}海中<WAH09002><WH03220>的水<WH04325>；雀鸟<WH05775>也要多生<WH07235><WTH8799>在地上<WAH09002><WH0776>。」"));
                verses.add(new Verse(new VerseIndex(0, 0, 22), "有<WAH01961><WTH8799>晚上<WH06153>，有<WAH01961><WTH8799>早晨<WH01242>，是第五<WH02549>日<WH03117>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 23), "神<WH0430>说<WH0559><WTH8799>：「地<WH0776>要生出<WH03318><WTH8686>活<WH02416>物<WH05315>来，各从其类<WAH09001><WH04327>；牲畜<WH0929>、昆虫<WH07431>、野兽<WH02416><WH0776>，各从其类<WAH09001><WH04327>。」事就这样<WAH03651>成了<WAH01961><WTH8799>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 24), "于是神<WH0430>造出<WH06213><WTH8799>{<WAH0853>}野兽<WH02416><WH0776>，各从其类<WAH09001><WH04327>；{<WAH0853>}牲畜<WH0929>，各从其类<WAH09001><WH04327>；{<WAH0853>}地上<WH0127>一切<WAH03605>昆虫<WH07431>，各从其类<WAH09001><WH04327>。神<WH0430>看着<WH07200><WTH8799>{<WAH03588>}是好的<WH02896>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 25), "神<WH0430>说<WH0559><WTH8799>：「我们要照着我们的形像<WAH09002><WH06754>、按着我们的样式<WAH09003><WH01823>造<WH06213><WTH8799>人<WH0120>，使他们管理<WH07287><WTH8799>海<WH03220>里的鱼<WAH09002><WH01710>、空<WH08064>中的鸟<WAH09002><WH05775>、地上的牲畜<WAH09002><WH0929>，和全<WAH09002><WAH03605>地<WH0776>，并地上<WAH05921><WH0776>所爬的<WH07430><WTH8802>一切<WAH09002><WAH03605>昆虫<WH07431>。」"));
                verses.add(new Verse(new VerseIndex(0, 0, 26), "神<WH0430>就照着自己的形像<WAH09002><WH06754>造<WH01254><WTH8799>{<WAH0853>}人<WH0120>，乃是照着他<WH0430>的形像<WAH09002><WH06754>造<WH01254><WTH8804>{<WAH0853>}男<WH02145>造<WH01254><WTH8804>{<WAH0853>}女<WH05347>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 27), "神<WH0430>就赐福<WH01288><WTH8762>给他们<WAH0853>，{<WH0430>}又对他们<WAH09001>说<WH0559><WTH8799>：「要生养<WH06509><WTH8798>众多<WH07235><WTH8798>，遍满<WH04390><WTH8798>{<WAH0853>}地面<WH0776>，治理<WH03533><WTH8798>这地，也要管理<WH07287><WTH8798>海<WH03220>里的鱼<WAH09002><WH01710>、空<WH08064>中的鸟<WAH09002><WH05775>，和地上<WAH05921><WH0776>各样<WAH09002><WAH03605>行动的<WH07430><WTH8802>活物<WH02416>。」"));
                verses.add(new Verse(new VerseIndex(0, 0, 28), "神<WH0430>说<WH0559><WTH8799>：「看哪<WH02009>，我将<WAH0853>{<WAH0834>}遍<WAH03605>地<WH0776>上<WAH05921><WH06440>一切<WAH03605>结<WH02232><WTH8802>种子<WH02233>的菜蔬<WH06212>和<WAH0853>一切<WAH03605>树<WH06086>上<WAH09002>所<WAH0834>结<WH02232><WTH8802>有核<WH02233>的果子<WH06529>全赐<WH05414><WTH8804>给你们<WAH09001>作<WH01961><WTH8799>食物<WAH09001><WH0402>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 29), "至于{<WAH09001>}{<WAH03605>}地上<WH0776>的走兽<WH02416>和{<WAH09001>}{<WAH03605>}空中<WH08064>的飞鸟<WH05775>，并各样<WAH09001><WAH03605>爬<WH07430><WTH8802>在<WAH05921>地上<WH0776>有{<WAH0834>}{<WAH09002>}生命<WH02416>的物<WH05315>，我将<WAH0853>{<WAH03605>}青<WH03418>草<WH06212>赐给它们作食物<WAH09001><WH0402>。」事就这样<WAH03651>成了<WAH01961><WTH8799>。"));
                verses.add(new Verse(new VerseIndex(0, 0, 30), "神<WH0430>看着<WH07200><WTH8799>{<WAH0853>}一切<WAH03605>所<WAH0834>造<WH06213><WTH8804>的{<WAH02009>}都甚<WH03966>好<WH02896>。有<WAH01961><WTH8799>晚上<WH06153>，有<WAH01961><WTH8799>早晨<WH01242>，是第六<WH08345>日<WH03117>。"));
                return verses;
            }
        });
    }
}
