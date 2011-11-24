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
        return qName + "=\"" + sanitizedValue() + "\"";
    }

    private String sanitizedValue() {
        return value == null ? "" : value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (prefix != null ? !prefix.equals(attribute.prefix) : attribute.prefix != null) return false;

        if (!name.equals(attribute.name)) return false;

        if (value != null ? !value.equals(attribute.value) : attribute.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prefix != null ? prefix.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}
