package multithread.aqs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Mutex implements Lock, java.io.Serializable {
    // 内部助手同步类Sync
    private static class Sync extends AbstractQueuedSynchronizer {
        // 当state=1表示获取了独占锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 如果state=0，锁是释放状态，尝试获取
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
        // acquires为1表示进行获取操作，其他值无效
            if (compareAndSetState(0, 1)) {
                //CAS操作
                setExclusiveOwnerThread(Thread.currentThread());
                //设置锁的持有者为当前线程
                return true;
            }
            return false;
        }

        //尝试释放
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            // 传入的值为1表示进行释放，其他值无效
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            //设置状态为0，表示锁已释放
            return true;
        }

        // 提供一个条件谓词
        Condition newCondition() {
            return new ConditionObject();
        }

        // 反序列化属性
        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0);
            //设置初始状态为释放
        }
    }

    // 所有同步操作 委托给Sync，下面我们实现必要的锁需要的操作
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}


