package org.marker.weixin.api.domain;
/**
 * Created by marker on 2019/11/18.
 */

import lombok.Data;

/**
 * @author marker
 *   2019-11-18 22:09
 **/

@Data
public class WxMaTemplateData {
    private String name;
    private String value;
    private String color = "#173177";

    public WxMaTemplateData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public WxMaTemplateData(String name, String value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }


}
