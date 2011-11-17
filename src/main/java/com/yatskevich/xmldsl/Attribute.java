package com.yatskevich.xmldsl;

/**
 * @author Ivan Yatskevich
 */
public class Attribute extends Element {

    private String value;

    public Attribute(String name, String value) {
        this(null, name, value);
    }

    public Attribute(String prefix, String name, String value) {
        super(prefix, name);
        this.value = value;
    }

    public String render() {
        String qName = getQName();
        return qName + "=\"" + value + "\"";
    }
}
