
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeaturedImage {

    private Integer iD;
    private String title;
    private String status;
    private String type;
    private Author_ author;
    private String content;
    private Integer parent;
    private String link;
    private String date;
    private String modified;
    private String format;
    private String slug;
    private String guid;
    private Object excerpt;
    private Integer menuOrder;
    private String commentStatus;
    private String pingStatus;
    private Boolean sticky;
    private String dateTz;
    private String dateGmt;
    private String modifiedTz;
    private String modifiedGmt;
    private Meta___ meta;
    private List<Object> terms = null;
    private String source;
    private Boolean isImage;
    private AttachmentMeta attachmentMeta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Author_ getAuthor() {
        return author;
    }

    public void setAuthor(Author_ author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Object getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(Object excerpt) {
        this.excerpt = excerpt;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public String getDateTz() {
        return dateTz;
    }

    public void setDateTz(String dateTz) {
        this.dateTz = dateTz;
    }

    public String getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public String getModifiedTz() {
        return modifiedTz;
    }

    public void setModifiedTz(String modifiedTz) {
        this.modifiedTz = modifiedTz;
    }

    public String getModifiedGmt() {
        return modifiedGmt;
    }

    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public Meta___ getMeta() {
        return meta;
    }

    public void setMeta(Meta___ meta) {
        this.meta = meta;
    }

    public List<Object> getTerms() {
        return terms;
    }

    public void setTerms(List<Object> terms) {
        this.terms = terms;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getIsImage() {
        return isImage;
    }

    public void setIsImage(Boolean isImage) {
        this.isImage = isImage;
    }

    public AttachmentMeta getAttachmentMeta() {
        return attachmentMeta;
    }

    public void setAttachmentMeta(AttachmentMeta attachmentMeta) {
        this.attachmentMeta = attachmentMeta;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
