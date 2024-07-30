package com.meng.tools.functional;

import com.meng.messtool.*;

public interface BiFunction<T, U, V> {
    T apply(U v1, V v2);
}
