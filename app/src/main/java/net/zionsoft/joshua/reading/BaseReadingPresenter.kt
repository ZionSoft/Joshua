package net.zionsoft.joshua.reading

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.zionsoft.joshua.model.BibleReadingModel
import net.zionsoft.joshua.model.domain.TranslationInfo
import net.zionsoft.joshua.model.domain.VerseIndex
import net.zionsoft.joshua.mvp.MVPPresenter

abstract class BaseReadingPresenter<V : BaseReadingView>(protected val bibleReadingModel: BibleReadingModel) : MVPPresenter<V>() {
    private var observeReadingProgress: Disposable? = null
    private var loadCurrentTranslation: Disposable? = null

    override fun onViewTaken() {
        super.onViewTaken()
        observeReadingProgress()
    }

    private fun observeReadingProgress() {
        disposeObserveReadingProgress()
        observeReadingProgress = bibleReadingModel.observeReadingProgress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<VerseIndex>() {
                    override fun onComplete() {
                        // won't reach here
                    }

                    override fun onError(e: Throwable) {
                        // won't reach here
                    }

                    override fun onNext(readingProgress: VerseIndex) {
                        view?.onReadingProgressUpdated(readingProgress)
                    }
                })
    }

    override fun onViewDropped() {
        disposeObserveReadingProgress()
        disposeLoadCurrentTranslation()
        super.onViewDropped()
    }

    private fun disposeObserveReadingProgress() {
        observeReadingProgress?.dispose()
        observeReadingProgress = null
    }

    private fun disposeLoadCurrentTranslation() {
        loadCurrentTranslation?.dispose()
        loadCurrentTranslation = null
    }

    fun loadCurrentTranslation() {
        disposeLoadCurrentTranslation()
        loadCurrentTranslation = bibleReadingModel.loadCurrentTranslation()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TranslationInfo>() {
                    override fun onSuccess(t: TranslationInfo) {
                        loadCurrentTranslation = null
                        view?.onCurrentTranslationInfoLoaded(t)
                    }

                    override fun onError(e: Throwable) {
                        loadCurrentTranslation = null
                        view?.onCurrentTranslationInfoLoadFailed()
                    }
                })
    }

    fun getCurrentBook(): Int {
        return bibleReadingModel.currentBook
    }

    fun getCurrentChapter(): Int {
        return bibleReadingModel.currentChapter
    }

    fun updateReadingProgress(book: Int, chapter: Int) {
        bibleReadingModel.updateReadingProgress(VerseIndex(book, chapter, 0))
    }
}
