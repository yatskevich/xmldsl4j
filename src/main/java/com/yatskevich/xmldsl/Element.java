package com.yatskevich.xmldsl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Ivan Yatskevich
 */
public abstract class Element {

    protected String prefix;
    protected String name;

    protected Element(String prefix, String name) {
        this.prefix = prefix;
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name should be filled");
        }
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
