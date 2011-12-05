package com.yatskevich.xmldsl;

/**
 * @author Ivan Yatskevich
 */
public class TextNode implements Renderable {

    private String text;

    public TextNode(String text) {
        this.text = text;
    }

    public String render() {
        return text;
    }

}
