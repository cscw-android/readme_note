package com.readme.data;




import java.util.Date;

import com.readme.client.ReadMeConstants;
import com.readme.json.JSONObject;



/**
 * note 笔记实体类
 *
 * @author haiwen
 */
public class Note {
    public static final String PATH = "path";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String SOURCE = "source";
    public static final String SIZE = "size";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
    public static final String CONTENT = "content";

    private String path;
    private String title;
    private String author;
    private String source;
    private long size;

    private long createTime = -1;
    private long modifyTime = -1;

    private String content;

    public Note() {
    }

    public Note(String json) {
        JSONObject jsonObj = new JSONObject(json);
        // this.path = jsonObj.getString(PATH);
        this.title = jsonObj.getString(TITLE);
        this.author = jsonObj.getString(AUTHOR);
        this.source = jsonObj.getString(SOURCE);
        this.size = jsonObj.getInt(SIZE);
        this.createTime = jsonObj.getLong(CREATE_TIME);
        this.modifyTime = jsonObj.getLong(MODIFY_TIME);
        this.content = jsonObj.getString(CONTENT);
    }

  
    public String getPath() {
        return path;
    }

   
    public void setPath(String path) {
        this.path = path;
    }

   
    public String getTitle() {
        return title;
    }

   
    public void setTitle(String title) {
        this.title = title;
    }

   
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    
    public String getSource() {
        return source;
    }

   
    public void setSource(String source) {
        this.source = source;
    }

   
    public long getSize() {
        return size;
    }

    
    public void setSize(long size) {
        this.size = size;
    }

  
    public long getCreateTime() {
        return createTime;
    }

   
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

   
    public long getModifyTime() {
        return modifyTime;
    }

    
    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    
    public String getContent() {
        return content;
    }

   
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[Notebook path=" + path
                + ", title=" + title
                + ", author=" + author
                + ", source=" + source
                + ", size=" + size
                + ", createTime=" + ReadMeConstants.DATE_FORMATTER.format(new Date(createTime * 1000))
                + ", modifyTime=" + ReadMeConstants.DATE_FORMATTER.format(new Date(modifyTime * 1000))
                + "]";
    }
}

