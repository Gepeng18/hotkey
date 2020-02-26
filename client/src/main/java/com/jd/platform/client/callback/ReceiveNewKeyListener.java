package com.jd.platform.client.callback;

import com.jd.platform.common.model.HotKeyModel;

/**
 * 客户端监听到有newKey事件
 * @author wuweifeng wrote on 2020-02-21
 * @version 1.0
 */
public interface ReceiveNewKeyListener {

    void newKey(HotKeyModel hotKeyModel);
}
