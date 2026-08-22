package cn.nubia.gamelauncher.gamecontrolpanel.superresolution;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AppMapConfig {
    private List<Config> config;

    public static class Config {
        private List<Item> item;
        private String resolution_config;

        public List<Item> getItem() {
            return this.item;
        }

        public String getResolution_config() {
            return this.resolution_config;
        }

        public void setItem(List<Item> list) {
            this.item = list;
        }

        public void setResolution_config(String str) {
            this.resolution_config = str;
        }
    }

    public static class Item {
        private String package_name;

        public String getPackage_name() {
            return this.package_name;
        }

        public void setPackage_name(String str) {
            this.package_name = str;
        }
    }

    public List<Config> getConfig() {
        return this.config;
    }

    public void setConfig(List<Config> list) {
        this.config = list;
    }

    public String toString() {
        StringBuilder sb;
        if (getConfig() != null) {
            sb = new StringBuilder();
            sb.append("<app_map_config>\n");
            for (Config config : getConfig()) {
                sb.append("    <config resolution_config=\"").append(config.getResolution_config()).append("\">\n");
                if (config != null && config.getItem() != null) {
                    Iterator<Item> it = config.getItem().iterator();
                    while (it.hasNext()) {
                        sb.append("        <item package_name=\"").append(it.next().getPackage_name()).append("\"/>\n");
                    }
                }
                sb.append("    </config>\n");
            }
            sb.append("</app_map_config>");
        } else {
            sb = null;
        }
        return sb.toString();
    }
}
