package net.melonbun.melonbun.common;

import java.util.LinkedList;
import java.util.Queue;

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