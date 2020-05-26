package com.jd.platform.hotkey.worker.netty.pusher;

import com.jd.platform.hotkey.common.configcenter.IConfigCenter;
import com.jd.platform.hotkey.common.model.HotKeyModel;
import com.jd.platform.hotkey.common.tool.HotKeyPathTool;
import com.jd.platform.hotkey.worker.rule.KeyRuleHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author wuweifeng wrote on 2020-02-24
 * @version 1.0
 */
@Component
public class EtcdPusher implements IPusher {
    @Resource
    private IConfigCenter iConfigCenter;

    private static final String DEFAULT_VALUE = "1";

    @Override
    public void push(HotKeyModel model) {
        //推送到etcd，供dashboard监听入库
        iConfigCenter.putAndGrant(HotKeyPathTool.keyRecordPath(model), DEFAULT_VALUE,
                KeyRuleHolder.getRuleByAppAndKey(model).getDuration());
    }

    @Override
    @Deprecated
    public void remove(HotKeyModel model) {
        //推送etcd删除
        iConfigCenter.delete(HotKeyPathTool.keyPath(model));
    }


}
