package com.yatskevich.xmldsl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Ivan Yatskevich
 */
public class AttributesTest {

    @Test
    public void equality() {
        Attribute attr = new Attribute("p", "a", "b");
        Attribute anotherIdenticalAttr = new Attribute("p", "a", "b");

        assertThat(attr, is(anotherIdenticalAttr));

        Attribute attrWithDifferentName = new Attribute("c", "b");
        assertThat(attr, is(not(attrWithDifferentName)));

        Attribute attrWithDifferentValue = new Attribute("a", "c");
        assertThat(attr, is(not(attrWithDifferentValue)));

        Attribute attrWithDifferentPrefix = new Attribute("double_p", "a", "b");
        assertThat(attr, is(not(attrWithDifferentPrefix)));
    }

    @Test
    public void attributeNameShouldBeFilled() {
        doNotAcceptName(null);
        doNotAcceptName("");
    }

    @Test
    public void valueCanBeNull() {
        Attribute attr = new Attribute("a", null);

        assertThat(attr.render(), is("a=\"\""));
    }

    private void doNotAcceptName(String name) {
        try {
            new Attribute(name, "any_value");
            fail("IllegalArgumentException is expected");
        } catch (IllegalArgumentException e) {
        }
    }

}
