package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-07-08.
 * function: 内容维表Bean
 */
public class DimContentFeaturesBean {
    private String docid;//文章ID
    private String modifytime;//修改时间
    private String scfeatures;//sc标签
    private String cfeatures;//c标签
    private String otherfeatures;//其他标签
    private String importdate;//导入时间
    private String otherState;//other state value
    private String expiretime;//过期时间
    private String searchpath;//栏目
    private String classv;//图谱分类

    public DimContentFeaturesBean() {
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getScfeatures() {
        return scfeatures;
    }

    public void setScfeatures(String scfeatures) {
        this.scfeatures = scfeatures;
    }

    public String getCfeatures() {
        return cfeatures;
    }

    public void setCfeatures(String cfeatures) {
        this.cfeatures = cfeatures;
    }

    public String getOtherfeatures() {
        return otherfeatures;
    }

    public void setOtherfeatures(String otherfeatures) {
        this.otherfeatures = otherfeatures;
    }

    public String getImportdate() {
        return importdate;
    }

    public void setImportdate(String importdate) {
        this.importdate = importdate;
    }

    public String getOtherState() {
        return otherState;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }

    public String getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(String expiretime) {
        this.expiretime = expiretime;
    }

    public String getSearchpath() {
        return searchpath;
    }

    public void setSearchpath(String searchpath) {
        this.searchpath = searchpath;
    }

    public String getClassv() {
        return classv;
    }

    public void setClassv(String classv) {
        this.classv = classv;
    }

    @Override
    public String toString() {
        return "DimContentFeaturesBean{" +
                "docid='" + docid + '\'' +
                ", modifytime='" + modifytime + '\'' +
                ", scfeatures='" + scfeatures + '\'' +
                ", cfeatures='" + cfeatures + '\'' +
                ", otherfeatures='" + otherfeatures + '\'' +
                ", importdate='" + importdate + '\'' +
                ", otherState='" + otherState + '\'' +
                ", expiretime='" + expiretime + '\'' +
                ", searchpath='" + searchpath + '\'' +
                ", classv='" + classv + '\'' +
                '}';
    }
}
