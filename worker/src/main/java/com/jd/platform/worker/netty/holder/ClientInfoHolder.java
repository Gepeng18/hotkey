package com.jd.platform.worker.netty.holder;

import com.jd.platform.worker.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存所有与server连接的客户端信息
 * @author wuweifeng wrote on 2019-12-04
 * @version 1.0
 */
public class ClientInfoHolder {
    public static volatile List<AppInfo> apps = new ArrayList<>();
}