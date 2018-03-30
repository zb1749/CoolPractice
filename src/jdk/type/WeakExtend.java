package jdk.type;

import java.lang.ref.WeakReference;

public class WeakExtend<K> extends WeakReference<K> {

    public WeakExtend(K referent) {
        super(referent);
    }
}
