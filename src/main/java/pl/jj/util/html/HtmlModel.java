package pl.jj.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Html model is a class which maps a html tag.
 * Inside html tags could be another tags, which is map by children elements.
 * Classes, styles - extracted html attributes
 * Attributes - additional html attributes
 * @author JNartowicz
 */
public class HtmlModel {

    private String tag;

    private String id;

    private String text;

    private List<String> classes;

    private Map<String, String> styles;

    private Map<String, String> attributes;

    private List<HtmlModel> children;

    public void addClass(String className){
        if(classes == null){
            classes = new ArrayList<>();
        }
        classes.add(className);
    }

    public void addStyle(String style, String value){
        if(styles == null){
            styles = new HashMap<>();
        }
        styles.put(style, value);
    }

    public void addAttribute(String attribute, String value){
        if(attributes == null){
            attributes = new HashMap<>();
        }
        attributes.put(attribute, value);
    }

    public void addChild(HtmlModel child){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public Map<String, String> getStyles() {
        return styles;
    }

    public void setStyles(Map<String, String> styles) {
        this.styles = styles;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<HtmlModel> getChildren() {
        return children;
    }

    public void setChildren(List<HtmlModel> children) {
        this.children = children;
    }

}
