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

import net.zionsoft.joshua.model.domain.Bible

fun positionToBookIndex(position: Int): Int {
    if (position < 0 || position >= Bible.TOTAL_CHAPTER_COUNT) {
        throw IllegalArgumentException("Invalid position: " + position)
    }

    var pos = position
    for (bookIndex in 0..Bible.BOOK_COUNT - 1) {
        val chapterCount = Bible.CHAPTER_COUNT[bookIndex]
        pos -= chapterCount
        if (pos < 0) {
            return bookIndex
        }
    }

    throw IllegalArgumentException("Invalid position: " + position)
}

fun positionToChapterIndex(position: Int): Int {
    if (position < 0 || position >= Bible.TOTAL_CHAPTER_COUNT) {
        throw IllegalArgumentException("Invalid position: " + position)
    }

    var pos = position
    for (bookIndex in 0..Bible.BOOK_COUNT - 1) {
        val chapterCount = Bible.CHAPTER_COUNT[bookIndex]
        if (pos < chapterCount) {
            return pos
        }
        pos -= chapterCount
    }

    throw IllegalArgumentException("Invalid position: " + position)
}

fun indexToPosition(book: Int, chapter: Int): Int {
    if (book < 0 || book >= Bible.BOOK_COUNT) {
        throw IllegalArgumentException("Invalid book: " + book)
    }
    if (chapter < 0 || chapter >= Bible.CHAPTER_COUNT[book]) {
        throw IllegalArgumentException("Invalid chapter: " + chapter)
    }

    var pos: Int = 0
    for (i in 0..book - 1) {
        pos += Bible.CHAPTER_COUNT[i]
    }
    return pos + chapter
}
