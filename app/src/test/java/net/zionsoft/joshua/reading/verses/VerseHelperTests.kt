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

package net.zionsoft.joshua.reading.verses

import org.junit.Assert
import org.junit.Test

class VerseHelperTests {
    @Test
    fun testPositionToBookIndex() {
        Assert.assertEquals(0, positionToBookIndex(0));
        Assert.assertEquals(0, positionToBookIndex(49));

        Assert.assertEquals(1, positionToBookIndex(50));
        Assert.assertEquals(1, positionToBookIndex(55));

        Assert.assertEquals(65, positionToBookIndex(1188));
    }

    @Test
    fun testPositionToChapterIndex() {
        Assert.assertEquals(0, positionToChapterIndex(0));
        Assert.assertEquals(49, positionToChapterIndex(49));

        Assert.assertEquals(0, positionToChapterIndex(50));
        Assert.assertEquals(5, positionToChapterIndex(55));

        Assert.assertEquals(21, positionToChapterIndex(1188));
    }

    @Test
    fun testIndexToPosition() {
        Assert.assertEquals(0, indexToPosition(0, 0));
        Assert.assertEquals(49, indexToPosition(0, 49));

        Assert.assertEquals(50, indexToPosition(1, 0));
        Assert.assertEquals(55, indexToPosition(1, 5));

        Assert.assertEquals(1188, indexToPosition(65, 21));
    }
}
