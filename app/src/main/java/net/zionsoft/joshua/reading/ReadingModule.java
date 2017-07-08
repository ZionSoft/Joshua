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

import net.zionsoft.joshua.model.BibleReadingModel;
import net.zionsoft.joshua.reading.chapters.ChapterPresenter;
import net.zionsoft.joshua.reading.toolbar.ToolbarPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ReadingModule {
    @Provides
    ReadingPresenter provideReadingPresenter() {
        return new ReadingPresenter();
    }

    @Provides
    ToolbarPresenter provideToolbarPresenter(BibleReadingModel bibleReadingModel) {
        return new ToolbarPresenter(bibleReadingModel);
    }

    @Provides
    ChapterPresenter provideChapterPresenter(BibleReadingModel bibleReadingModel) {
        return new ChapterPresenter(bibleReadingModel);
    }
}
