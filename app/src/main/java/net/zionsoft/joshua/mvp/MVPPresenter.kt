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

package net.zionsoft.joshua.mvp

import android.support.annotation.CallSuper

abstract class MVPPresenter<V : MVPView> {
    protected var view: V? = null
        private set

    fun takeView(view: V) {
        this.view = view
        onViewTaken()
    }

    @CallSuper
    protected open fun onViewTaken() {
    }

    fun dropView() {
        onViewDropped()
        this.view = null;
    }

    @CallSuper
    protected open fun onViewDropped() {
    }
}
