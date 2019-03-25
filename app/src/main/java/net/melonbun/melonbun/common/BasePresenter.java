package net.melonbun.melonbun.common;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BasePresenter that is extended by all Presenter classes
 * Handles unbind, bind of the view to the presenter
 * Handles queueing of ViewOperations when view is not yet bind to the presenter
 * Handles release and execution of queued ViewOperations when view is bind to the presenter
 *
 * @param <T> View interface that extends from BaseView
 */
public abstract class BasePresenter<T extends BaseView> {

    @FunctionalInterface
    protected interface ViewOperation {
        void execute();
    }

    protected T view;

    private Queue<ViewOperation> viewOperationQueue = new LinkedList<>();

    public void bindView(T view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }

    public void updateState() {
        while (!viewOperationQueue.isEmpty()) {
            if (view == null) {
                return;
            }
            viewOperationQueue.remove().execute();
        }
    }

    protected void executeViewOperation(ViewOperation viewOperation) {
        if (view != null) {
            viewOperation.execute();
        } else {
            viewOperationQueue.add(viewOperation);
        }
    }

}
