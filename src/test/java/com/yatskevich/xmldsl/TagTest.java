package com.yatskevich.xmldsl;

import org.junit.Test;

import static com.yatskevich.xmldsl.Tag.tag;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivan Yatskevich
 */
public class TagTest {

    @Test
    public void simpleTag() {
        assertTrue(tag("t").render().equals("<t/>"));
    }

    @Test
    public void simpleTagWithPrefix() {
        assertTrue(tag("y", "t").render().equals("<y:t/>"));
    }

    @Test
    public void simpleTagWithPrefixAndAttributes() {
        Tag tag = tag("y", "t").attr("attr", "val").attr("y", "a", "v");

        assertTrue(tag.render().equals("<y:t attr=\"val\" y:a=\"v\"/>"));
    }

    @Test
    public void tagWithAttributeAsObject() {
        Tag tag = tag("t").attr(new Attribute("attr", "val"));

        assertTrue(tag.render().equals("<t attr=\"val\"/>"));
    }

    @Test
    public void nestedTags() {
        Tag tag =
                tag("a").nest(
                        tag("b").nest(
                                tag("c")));
        assertTrue(tag.render().equals("<a><b><c/></b></a>"));
    }

    @Test
    public void nestSeveralTagsOnOneLevel() {
        Tag tag = tag("a").nest(tag("b"), tag("c"));
        assertTrue(tag.render().equals("<a><b/><c/></a>"));
    }

    @Test
    public void justTextInTag() {
        Tag tag = tag("a").text("test");
        assertTrue(tag.render().equals("<a>test</a>"));
    }

    @Test
    public void textWithChildTag() {
        Tag tag = tag("a").text("test").nest(tag("b"));
        assertTrue(tag.render().equals("<a>test<b/></a>"));
    }


}
