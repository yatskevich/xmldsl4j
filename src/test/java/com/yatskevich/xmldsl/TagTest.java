package com.yatskevich.xmldsl;

import org.junit.Test;

import static com.yatskevich.xmldsl.Tag.tag;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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

        assertThat(tag.render(), is("<y:t attr=\"val\" y:a=\"v\"/>"));
    }

    @Test
    public void tagWithAttributeAsObject() {
        Tag tag = tag("t").attr(new Attribute("attr", "val"));

        assertThat(tag.render(), is("<t attr=\"val\"/>"));
    }

    @Test
    public void nestedTags() {
        Tag tag =
                tag("a").nest(
                        tag("b").nest(
                                tag("c")));
        assertThat(tag.render(), is("<a><b><c/></b></a>"));
    }

    @Test
    public void nestSeveralTagsOnOneLevel() {
        Tag tag = tag("a").nest(tag("b"), tag("c"));
        assertThat(tag.render(), is("<a><b/><c/></a>"));
    }

    @Test
    public void justTextInTag() {
        Tag tag = tag("a").text("test");
        assertThat(tag.render(), is("<a>test</a>"));
    }

    @Test
    public void textWithChildTag() {
        Tag tag = tag("a").text("test").nest(tag("b"));
        assertThat(tag.render(), is("<a>test<b/></a>"));
    }

    @Test
    public void textAlternatesWithTags() {
        Tag tag = tag("a").text("b").text("c").nest(tag("d")).text("e");
        assertThat(tag.render(), is("<a>bc<d/>e</a>"));
    }

    @Test
    public void identicalAttributesAreAddedOnce() {
        Tag tag = tag("t").attr("a", "val").attr("a", "val");
        assertThat(tag.render(), is("<t a=\"val\"/>"));
    }

    @Test
    public void inAttributeQNameClashLastEntryWins() {
        Tag tag = tag("t").attr("a", "val_first").attr("a", "val_last");
        assertThat(tag.render(), is("<t a=\"val_last\"/>"));

        tag = tag("t").attr("p", "a", "val_first").attr("p", "a", "val_last");
        assertThat(tag.render(), is("<t p:a=\"val_last\"/>"));
    }

}
