package com.app.njl.subject.hotel.presenter.base;

import android.text.TextUtils;

import com.app.njl.subject.hotel.view.MvpView;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    /**
     * 校验指定的字符串是否为空,如果为空则弹出指定内容的Toast
     *
     * @param verifData
     * @param view
     * @param showMessage
     * @return
     */
    public boolean isEmpty(String verifData, T view, String showMessage) {
        if (TextUtils.isEmpty(verifData)) {
            view.showToast(showMessage);
            return true;
        }

        return false;
    }
}

