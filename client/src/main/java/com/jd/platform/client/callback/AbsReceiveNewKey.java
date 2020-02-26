package com.jd.platform.client.callback;

import com.jd.platform.client.log.JdLogger;
import com.jd.platform.common.model.HotKeyModel;
import com.jd.platform.common.model.typeenum.KeyType;

/**
 * @author wuweifeng wrote on 2020-02-24
 * @version 1.0
 */
public abstract class AbsReceiveNewKey implements ReceiveNewKeyListener {


    @Override
    public void newKey(HotKeyModel hotKeyModel) {
        long now = System.currentTimeMillis();
        //如果key到达时已经过去5秒了，记录一下
        if (Math.abs(now - hotKeyModel.getCreateTime()) > 5000) {
            JdLogger.warn(getClass(), "the key comes too last : " + hotKeyModel);
        }
        if (hotKeyModel.isRemove()) {
            deleteKey(hotKeyModel.getKey(), hotKeyModel.getKeyType(), hotKeyModel.getCreateTime());
        } else {
            addKey(hotKeyModel.getKey(), hotKeyModel.getKeyType(), hotKeyModel.getCreateTime());
        }

    }

    abstract void addKey(String key, KeyType keyType, long createTime);

    abstract void deleteKey(String key, KeyType keyType, long createTime);
}
