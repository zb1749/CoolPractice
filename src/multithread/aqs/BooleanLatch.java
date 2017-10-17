package multithread.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class BooleanLatch {
    //内部同步器，state=0表示未被通知（等待中，不可共享获取），state！=0表示被通知（可共享获取）
    private static class Sync extends AbstractQueuedSynchronizer {
        boolean isSignalled() {
            return getState() != 0;
        }

        /**
         * tryAcquireShared 返回负值 获取失败
         * 0 获取成功其他线程不能获取
         * 正值获取成功，其他线程也可获取成功
         */
        protected int tryAcquireShared(int ignore) {
            return isSignalled() ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignore) {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public boolean isSignalled() {
        return sync.isSignalled();
    }

    public void signal() {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
}
