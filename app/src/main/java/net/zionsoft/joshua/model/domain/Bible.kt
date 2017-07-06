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

package net.zionsoft.joshua.model.domain

class Bible {
    companion object {
        @JvmField
        val BOOK_COUNT: Int = 66

        @JvmField
        val OLD_TESTAMENT_BOOK_COUNT: Int = 39

        @JvmField
        val NEW_TESTAMENT_BOOK_COUNT: Int = 27

        @JvmField
        val TOTAL_CHAPTER_COUNT: Int = 1189

        @JvmField
        val CHAPTER_COUNT: IntArray = intArrayOf(50, 40, 27, 36, 34, 24, 21, 4, 31, 24, 22, 25, 29, 36,
                10, 13, 10, 42, 150, 31, 12, 8, 66, 52, 5, 48, 12, 14, 3, 9, 1, 4, 7, 3, 3, 3, 2, 14, 4,
                28, 16, 24, 21, 28, 16, 16, 13, 6, 6, 4, 4, 5, 3, 6, 4, 3, 1, 13, 5, 5, 3, 5, 1, 1, 1, 22)
    }
}
