package net.zionsoft.joshua.reading

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import net.zionsoft.joshua.model.BibleReadingModel
import net.zionsoft.joshua.model.domain.TranslationInfo
import net.zionsoft.joshua.model.domain.VerseIndex
import net.zionsoft.joshua.mvp.MVPPresenter

abstract class BaseReadingPresenter<V : BaseReadingView>(protected val bibleReadingModel: BibleReadingModel) : MVPPresenter<V>() {
    private var observeReadingProgress: Disposable? = null
    private var observeCurrentTranslation: Disposable? = null

    override fun onViewTaken() {
        super.onViewTaken()
        observeCurrentTranslation()
        observeReadingProgress()
    }

    private fun observeCurrentTranslation() {
        disposeObserveCurrentTranslation()
        observeCurrentTranslation = bibleReadingModel.observeCurrentTranslation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TranslationInfo>() {
                    override fun onComplete() {
                        // won't reach here
                    }

                    override fun onError(e: Throwable) {
                        // won't reach here
                    }

                    override fun onNext(currentTranslation: TranslationInfo) {
                        view?.onCurrentTranslationUpdated(currentTranslation)
                    }
                })
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
        disposeObserveCurrentTranslation()
        disposeObserveReadingProgress()
        super.onViewDropped()
    }

    private fun disposeObserveCurrentTranslation() {
        observeCurrentTranslation?.dispose()
        observeCurrentTranslation = null
    }

    private fun disposeObserveReadingProgress() {
        observeReadingProgress?.dispose()
        observeReadingProgress = null
    }

    fun updateCurrentTranslation(translation: TranslationInfo) {
        bibleReadingModel.updateCurrentTranslation(translation)
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
