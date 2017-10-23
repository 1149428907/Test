package com.test.beetl;

import org.beetl.core.Tag;
import org.beetl.core.TagFactory;

/**
 * Beetl 标签工厂
 * @author td
 *
 */
public class BeetlTagFactory implements TagFactory {
    Tag tagCls;

    public BeetlTagFactory(Tag tagCls) {
        this.tagCls = tagCls;
    }
    public Tag createTag() {
        return tagCls;
    }
}