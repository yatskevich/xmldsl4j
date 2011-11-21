package com.yatskevich.xmldsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.join;

/**
 * @author Ivan Yatskevich
 */
public class Tag extends Element {

    private List<Tag> children = new ArrayList<Tag>();
    private List<Attribute> attributes = new ArrayList<Attribute>();
    private String text;

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

    public Tag attr(Attribute attribute) {
        return add(attribute);
    }

    public Tag nest(Tag... tags) {
        children.addAll(Arrays.asList(tags));
        return this;
    }

    private Tag add(Attribute attribute) {
        attributes.add(attribute);
        return this;
    }

    public Tag text(String text) {
        this.text = text;
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

        if (children.isEmpty() && text == null) {
            builder.append("/>");
        } else {
            builder.append(">");
            if (text != null) {
                builder.append(text);
            }
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

}
