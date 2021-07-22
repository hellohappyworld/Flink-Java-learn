package com.scallion.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.scallion.utils.TimeUtil;

/**
 * created by gaowj.
 * created on 2021-07-12.
 * function: 内容维表Bean
 */
public class DimContentBean {
    @JSONField(name = "id")
    private String id;//文章id
    @JSONField(name = "title")
    private String title;//文章标题
    @JSONField(name = "creator")
    private String editor;//编辑
    @JSONField(name = "fhh")
    private String fhh;//文章对应的自媒体文章id
    @JSONField(name = "joinId")
    private String joinId;
    @JSONField(name = "src")
    private String src;//文章稿源名称
    @JSONField(name = "tsrc")
    private String tsrc;
    @JSONField(name = "srcMap")
    private String srcMap;
    @JSONField(name = "type")
    private String pageType;//文章页面类型
    @JSONField(name = "creator")
    private String domain;//编辑域账号
    @JSONField(name = "yc")
    private String yc;//是否原创
    @JSONField(name = "eaccId")
    private String eaccId;//账号自媒体旧数据库对外ID
    @JSONField(name = "accid")
    private String accid;//账号自媒体ID
    @JSONField(name = "accountType")
    private String accountType;//账号类型
    @JSONField(name = "dataSource")
    private String dataSource;//文章来源
    @JSONField(name = "level")
    private String level;//账号等级
    @JSONField(name = "dept")
    private String dept;//编辑部门
    @JSONField(name = "issueType")
    private String issueType;//发布通道
    @JSONField(name = "timestamp")
    private String issueTime;//发文时间
    @JSONField(name = "fhtId")
    private String fhtId;//凤凰通id
    @JSONField(name = "url")
    private String urlPc;
    @JSONField(name = "applyTime")
    private String applyTime;
    @JSONField(name = "wapUrl")
    private String urlIfeng;
    @JSONField(name = "source_link")
    private String sourceLink;
    @JSONField(name = "page")
    private String num;//图集页数/视频长度
    @JSONField(name = "scword")
    private String scword;//二级标签(老)
    @JSONField(name = "scrate")
    private String scrate;//二级标签概率(老)
    @JSONField(name = "cword")
    private String cword;//一级标签(老)
    @JSONField(name = "crate")
    private String crate;//一级标签概率(老)
    @JSONField(name = "word_num")
    private int wordNum;//文章字数
    @JSONField(name = "jppoolCh")
    private String jppoolCh;//精品池推荐节点
    @JSONField(name = "jppoolUserName")
    private String jppoolUserName;//精品池编辑人员
    @JSONField(name = "isJp")
    private String isJp;//是否是精品池文章
    @JSONField(name = "is_original")
    private String original;//原创标识
    @JSONField(name = "rank")
    private String rank;
    @JSONField(name = "field")
    private String field;//专属领域
    @JSONField(name = "c")
    private String c;//一级标签(新)
    @JSONField(name = "sc")
    private String sc;//二级标签(新)
    @JSONField(name = "Is_original_wemedia")
    private String originalWemedia;//自媒体原创标识
    @JSONField(name = "biz")
    private String biz;//是否是地方站发文
    @JSONField(name = "importDate")
    private String importDate;//内容画像导入时间
    @JSONField(name = "otherState")
    private String otherState;//内容画像other state
    @JSONField(name = "dataProvider")
    private String dataProvider;
    @JSONField(name = "ucmsId")
    private String ucmsId;
    @JSONField(name = "timestamp")
    private String tm2;
    @JSONField(name = "updater")
    private String updater;
    @JSONField(name = "spName")
    private String spName;
    @JSONField(name = "expireTime")
    private String expireTime;//过期时间
    @JSONField(name = "searchpath")
    private String searchPath;//栏目
    @JSONField(name = "classV")
    private String classV;//图谱分类
    @JSONField(name = "shortId")
    private String shortId;//短id

    public DimContentBean() {
        this.id = "#";
        this.title = "#";
        this.applyTime = "#";
        this.editor = "#";
        this.fhh = "#";
        this.joinId = "#";
        this.src = "#";
        this.tsrc = "#";
        this.srcMap = "#";
        this.pageType = "#";
        this.domain = "#";
        this.yc = "#";
        this.eaccId = "#";
        this.accid = "#";
        this.accountType = "#";
        this.dataSource = "#";
        this.level = "#";
        this.dept = "#";
        this.issueType = "#";
        this.issueTime = "#";
        this.fhtId = "#";
        this.urlPc = "#";
        this.urlIfeng = "#";
        this.sourceLink = "#";
        this.num = "#";
        this.scword = "#";
        this.scrate = "#";
        this.cword = "#";
        this.crate = "#";
        this.wordNum = 0;
        this.jppoolCh = "#";
        this.jppoolUserName = "#";
        this.isJp = "0";
        this.original = "#";
        this.rank = "#";
        this.field = "#";
        this.c = "#";
        this.sc = "#";
        this.originalWemedia = "#";
        this.biz = "#";
        this.importDate = "#";
        this.otherState = "#";
        this.dataProvider = "#";
        this.ucmsId = "#";
        this.tm2 = "#";
        this.updater = "#";
        this.spName = "#";
        this.expireTime = "#";
        this.searchPath = "#";
        this.classV = "#";
        this.shortId = "#";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replaceAll("\\t|\\n", "");
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getFhh() {
        return fhh;
    }

    public void setFhh(String fhh) {
        this.fhh = fhh;
    }

    public String getJoinId() {
        return joinId;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public String getTsrc() {
        return tsrc;
    }

    public void setTsrc(String tsrc) {
        this.tsrc = tsrc;
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcMap() {
        return srcMap;
    }

    public void setSrcMap(String srcMap) {
        this.srcMap = srcMap;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getYc() {
        return yc;
    }

    public void setYc(String yc) {
        this.yc = yc;
    }

    public String getEaccId() {
        return eaccId;
    }

    public void setEaccId(String eaccId) {
        this.eaccId = eaccId;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        try {
            this.issueTime = TimeUtil.getTimestampToDateStr(Long.parseLong(issueTime));
        } catch (Exception ex) {
            this.issueTime = issueTime;
            ex.printStackTrace();
        }
    }

    public String getFhtId() {
        return fhtId;
    }

    public void setFhtId(String fhtId) {
        this.fhtId = fhtId;
    }

    public String getUrlPc() {
        return urlPc;
    }

    public void setUrlPc(String urlPc) {
        this.urlPc = urlPc.replaceAll("https", "http");
    }

    public String getUrlIfeng() {
        return urlIfeng;
    }

    public void setUrlIfeng(String urlIfeng) {
        this.urlIfeng = urlIfeng.replaceAll("https", "http");
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getScword() {
        return scword;
    }

    public void setScword(String scword) {
        this.scword = scword;
    }

    public String getScrate() {
        return scrate;
    }

    public void setScrate(String scrate) {
        this.scrate = scrate;
    }

    public String getCword() {
        return cword;
    }

    public void setCword(String cword) {
        this.cword = cword;
    }

    public String getCrate() {
        return crate;
    }

    public void setCrate(String crate) {
        this.crate = crate;
    }

    public int getWordNum() {
        return wordNum;
    }

    public void setWordNum(String wordNum) {
        this.wordNum = Integer.parseInt(wordNum);
    }

    public String getJppoolCh() {
        return jppoolCh;
    }

    public void setJppoolCh(String jppoolCh) {
        this.jppoolCh = jppoolCh;
    }

    public String getJppoolUserName() {
        return jppoolUserName;
    }

    public void setJppoolUserName(String jppoolUserName) {
        this.jppoolUserName = jppoolUserName;
    }

    public String getIsJp() {
        return isJp;
    }

    public void setIsJp(String isJp) {
        this.isJp = isJp;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getOriginalWemedia() {
        return originalWemedia;
    }

    public void setOriginalWemedia(String originalWemedia) {
        this.originalWemedia = originalWemedia;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getOtherState() {
        return otherState;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }

    public String getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(String dataProvider) {
        this.dataProvider = dataProvider;
    }

    public String getUcmsId() {
        return ucmsId;
    }

    public void setUcmsId(String ucmsId) {
        this.ucmsId = ucmsId;
    }

    public String getTm2() {
        return tm2;
    }

    public void setTm2(String tm2) {
        try {
            this.tm2 = TimeUtil.getTimestampToDateStr(Long.parseLong(tm2));
        } catch (Exception ex) {
            this.tm2 = tm2;
            ex.printStackTrace();
        }
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    public String getClassV() {
        return classV;
    }

    public void setClassV(String classV) {
        this.classV = classV;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    @Override
    public String toString() {
        return "DimContentBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", editor='" + editor + '\'' +
                ", fhh='" + fhh + '\'' +
                ", joinId='" + joinId + '\'' +
                ", src='" + src + '\'' +
                ", srcMap='" + srcMap + '\'' +
                ", pageType='" + pageType + '\'' +
                ", domain='" + domain + '\'' +
                ", yc='" + yc + '\'' +
                ", eaccId='" + eaccId + '\'' +
                ", accid='" + accid + '\'' +
                ", accountType='" + accountType + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", level='" + level + '\'' +
                ", dept='" + dept + '\'' +
                ", issueType='" + issueType + '\'' +
                ", issueTime='" + issueTime + '\'' +
                ", fhtId='" + fhtId + '\'' +
                ", urlPc='" + urlPc + '\'' +
                ", urlIfeng='" + urlIfeng + '\'' +
                ", sourceLink='" + sourceLink + '\'' +
                ", num='" + num + '\'' +
                ", scword='" + scword + '\'' +
                ", scrate='" + scrate + '\'' +
                ", cword='" + cword + '\'' +
                ", crate='" + crate + '\'' +
                ", wordNum='" + wordNum + '\'' +
                ", jppoolCh='" + jppoolCh + '\'' +
                ", jppoolUserName='" + jppoolUserName + '\'' +
                ", isJp='" + isJp + '\'' +
                ", original='" + original + '\'' +
                ", rank='" + rank + '\'' +
                ", field='" + field + '\'' +
                ", c='" + c + '\'' +
                ", sc='" + sc + '\'' +
                ", originalWemedia='" + originalWemedia + '\'' +
                ", biz='" + biz + '\'' +
                ", importDate='" + importDate + '\'' +
                ", otherState='" + otherState + '\'' +
                ", dataProvider='" + dataProvider + '\'' +
                ", ucmsId='" + ucmsId + '\'' +
                ", tm2='" + tm2 + '\'' +
                ", updater='" + updater + '\'' +
                ", spName='" + spName + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", searchPath='" + searchPath + '\'' +
                ", classV='" + classV + '\'' +
                ", shortId='" + shortId + '\'' +
                '}';
    }
}
