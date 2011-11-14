package com.yatskevich.xmldsl;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.join;

/**
 * @author Ivan Yatskevich
 */
public class Tag extends Element {

    private List<Tag> children = new ArrayList<Tag>();
    private List<Attribute> attributes = new ArrayList<Attribute>();

    private Tag(String prefix, String name) {
        super(prefix, name);
    }

    public static Tag tag(String name) {
        return tag("", name);
    }

    public static Tag tag(String prefix, String name) {
        return new Tag(prefix, name);
    }

    public Tag nest(Tag child) {
        children.add(child);
        return this;
    }

    public Tag attr(String name, String value) {
        return add(new Attribute("", name, value));
    }

    public Tag attr(String prefix, String name, String value) {
        return add(new Attribute(prefix, name, value));
    }

    private Tag add(Attribute attribute) {
        attributes.add(attribute);
        return this;
    }

    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append("<");

        String qName = getQName();
        builder.append(qName);
        if (!attributes.isEmpty()) {
            builder.append(" ").append(renderAttributes());
        }

        if (children.isEmpty()) {
            builder.append("/>");
        } else {
            builder.append(">");
            for (Tag child : children) {
                builder.append(child.render());
            }
            builder.append("</").append(qName).append(">");
        }
        return builder.toString();
    }

    private String renderAttributes() {
        List<String> renderedAttributes = new ArrayList<String>();
        for (Attribute attribute : attributes) {
            renderedAttributes.add(attribute.render());
        }
        return join(renderedAttributes, " ");
    }

    private static class Attribute extends Element {

        private String value;

        private Attribute(String prefix, String name, String value) {
            super(prefix, name);
            this.value = value;
        }

        public String render() {
            String qName = getQName();
            return qName + "=\"" + value + "\"";
        }
    }
}