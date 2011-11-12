package com.yatskevich.xmldsl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Ivan Yatskevich
 */
public abstract class Element {

    private String prefix;
    private String name;

    protected Element(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    protected String getQName() {
        if (isNotBlank(prefix)) {
            return prefix + ":" + name;
        } else {
            return name;
        }
    }

}
