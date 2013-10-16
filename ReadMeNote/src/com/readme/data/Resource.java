package com.readme.data;


/**
 * resource 资源类
 * @author haiwen
 */
public class Resource {
    /**
     * JSON field names for resource 
     */
    public static final String SRC = "src";
    public static final String URL = "url";

    public static final String IMAGE_TAG_TEMPLATE = "<img src=\"%resource%\" />";
    public static final String ATTACHMENT_TAG_TEMPLATE = "<img path=\"%resource%\" src=\"%icon%\" />";

    /**
     * url for this resource
     */
    private String url;
    /**
     * url for this resource's icon if this resource is an attachment
     * PS: image resource does not have an icon 
     */
    private String icon;

    public Resource(String url, String icon) {
       this.url = url;
       this.icon = icon;
    }

    public Resource(String url) {
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }
    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the tag this resource
     */
    public String toResourceTag() {
        if (icon == null || icon.isEmpty()) {
            return IMAGE_TAG_TEMPLATE.replace("%resource%", url);
        } else {
            String tag = ATTACHMENT_TAG_TEMPLATE.replace("%resource%", url);
            return tag.replace("%icon%", icon);
        }
    }
}

