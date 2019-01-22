package net.samuelcampos.concurrency_issue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private Map<String, Integer> configMap;

    public ConfigManager() {
        configMap = Collections.synchronizedMap(new HashMap<>());
    }

    public Integer getConfigPlusOne(String configKey) {
        return configMap.get(configKey) + 1;
    }

    public Integer updateConfig(String configKey, Integer configValue) {
        configMap.remove(configKey);
        configMap.put(configKey, configValue);

        return configValue;
    }
}
