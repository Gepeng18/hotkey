package com.jd.platform.hotkey.worker;

import com.ibm.etcd.api.KeyValue;
import com.ibm.etcd.api.LeaseLeasesResponse;
import com.ibm.etcd.api.LeaseStatus;
import com.jd.platform.hotkey.common.configcenter.ConfigConstant;
import com.jd.platform.hotkey.common.configcenter.IConfigCenter;
import com.jd.platform.hotkey.common.configcenter.etcd.JdEtcdClient;
import com.jd.platform.hotkey.common.rule.DefaultKeyRule;
import com.jd.platform.hotkey.common.rule.KeyRule;
import com.jd.platform.hotkey.common.tool.FastJsonUtils;
import com.jd.platform.hotkey.worker.starters.EtcdStarter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author wuweifeng wrote on 2019-12-09
 * @version 1.0
 */
@RestController
public class TestController {
    @Resource
    private IConfigCenter iConfigCenter;
    @Resource
    private EtcdStarter etcdStarter;

    @RequestMapping("test")
    public Object aa(String key) throws ExecutionException, InterruptedException {
        LeaseLeasesResponse response = ((JdEtcdClient) iConfigCenter).getLeaseClient().list().get();
        for (LeaseStatus status : response.getLeasesList()) {
            System.out.println(status.getID());
        }
        return 1;
    }

    /**
     * 手工注册worker到etcd去
     */
    @RequestMapping("regist")
    public Object regist() {
        return etcdStarter.handUpload();
    }


    @RequestMapping("workersPath")
    public Object workersPath() {
        try {
            List<KeyValue> list = iConfigCenter.getPrefix(ConfigConstant.workersPath);
            Map<String, Object> map = new HashMap<>();
            for (KeyValue keyValue : list) {
                map.put(keyValue.getKey().toStringUtf8(), keyValue.getValue().toStringUtf8());
            }
            return map;
        } catch (Exception e) {
            return "exception";
        }

    }

    @RequestMapping("rulePath")
    public Object rulePath() {
        List<KeyValue> list = iConfigCenter.getPrefix(ConfigConstant.rulePath);
        Map<String, Object> map = new HashMap<>();
        for (KeyValue keyValue : list) {
            map.put(keyValue.getKey().toStringUtf8(), keyValue.getValue().toStringUtf8());
        }
        return map;
    }

    @RequestMapping("addRulePath")
    public Object adRulePath(String appName) {
        KeyRule keyRule = new DefaultKeyRule().getKeyRule();

        iConfigCenter.put(ConfigConstant.rulePath + appName, FastJsonUtils.convertObjectToJSON(Arrays.asList(keyRule)));
        return "success";
    }

    @RequestMapping("deleteRulePath")
    public Object deleteRulePath(String appName) {
        iConfigCenter.delete(ConfigConstant.rulePath + appName);
        return "success";
    }

    @RequestMapping("hotKeyPath")
    public Object hotKeyPath() {
        List<KeyValue> list = iConfigCenter.getPrefix(ConfigConstant.hotKeyPath);
        Map<String, Object> map = new HashMap<>();
        for (KeyValue keyValue : list) {
            map.put(keyValue.getKey().toStringUtf8(), keyValue.getValue().toStringUtf8());
        }
        return map;
    }

}
