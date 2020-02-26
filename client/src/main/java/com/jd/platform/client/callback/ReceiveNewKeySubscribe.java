package com.jd.platform.client.callback;

import com.google.common.eventbus.Subscribe;
import com.jd.platform.common.model.HotKeyModel;


/**
 * 监听有新key推送事件
 * @author wuweifeng wrote on 2020-02-21
 * @version 1.0
 */
public class ReceiveNewKeySubscribe {

    private ReceiveNewKeyListener receiveNewKeyListener = new DefaultNewKeyListener();

    @Subscribe
    public void newKeyComing(ReceiveNewKeyEvent event) {
        HotKeyModel hotKeyModel = event.getModel();
        if (hotKeyModel == null) {
            return;
        }
        //收到新key推送，可能是新增，也可能是删除
        if (receiveNewKeyListener != null) {
            receiveNewKeyListener.newKey(hotKeyModel);
        }
    }

}
