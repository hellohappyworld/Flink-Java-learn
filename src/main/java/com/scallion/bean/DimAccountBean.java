package com.scallion.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.scallion.common.Common;
import com.scallion.utils.TimeUtil;

/**
 * created by gaowj.
 * created on 2021-07-09.
 * function:账号维表Bean
 */
public class DimAccountBean {
    @JSONField(name = "_id")
    private String accId;
    @JSONField(name = "eAccountId")
    private String eAccountId;
    @JSONField(name = "eId")
    private String eId;
    @JSONField(name = "weMediaName")
    private String weMediaName;
    @JSONField(name = "weMediaDesc")
    private String weMediaDesc;
    @JSONField(name = "fhtId")
    private String fhtId;
    @JSONField(name = "copyright")
    private String copyright;
    @JSONField(name = "fhhCopyright")
    private String fhhCopyright;
    @JSONField(name = "online")
    private String online;
    @JSONField(name = "field")
    private String field;
    @JSONField(name = "fieldlevel")
    private String fieldlevel;
    @JSONField(name = "accountType")
    private String accountType;
    @JSONField(name = "accounttypeid")
    private String accounttypeid;
    @JSONField(name = "wemediatypeid")
    private String wemediatypeid;
    @JSONField(name = "weMediaType")
    private String weMediaType;
    @JSONField(name = "applyTime")
    private String applyTime;
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "auditUserName")
    private String auditUserName;
    @JSONField(name = "auditUserId")
    private String auditUserId;
    @JSONField(name = "accountWeight")
    private String accountWeight;
    @JSONField(name = "auditTime")
    private String auditTime;
    @JSONField(name = "level")
    private String level;
    @JSONField(name = "videoChannel")
    private String videoChannel;
    @JSONField(name = "articleChannel")
    private String articleChannel;
    @JSONField(name = "priv")
    private String priv;
    @JSONField(name = "fieldcreatetime")
    private String fieldcreatetime;
    @JSONField(name = "fieldtype")
    private String fieldtype;
    @JSONField(name = "accountQuality")
    private String accountQuality;
    @JSONField(name = "accountQualityName")
    private String accountQualityName;
    @JSONField(name = "updateTime")
    private String updateTime;
    @JSONField(name = "onlineTime")
    private String onlineTime;
    @JSONField(name = "pubnum")
    private String pubnum;
    @JSONField(name = "onlinenum")
    private String onlinenum;
    @JSONField(name = "orignum")
    private String orignum;
    @JSONField(name = "offlinenum")
    private String offlinenum;
    @JSONField(name = "vev")
    private String vev;
    @JSONField(name = "video_score")
    private String video_score;
    @JSONField(name = "video_categor")
    private String video_categor;
    @JSONField(name = "video_timeliness")
    private String video_timeliness;
    @JSONField(name = "video_level")
    private String video_level;
    @JSONField(name = "doc_category")
    private String doc_category;
    @JSONField(name = "doc_level")
    private String doc_level;
    @JSONField(name = "doc_score")
    private String doc_score;
    @JSONField(name = "isratings")
    private String isratings;
    @JSONField(name = "ratingsdate")
    private String ratingsdate;
    @JSONField(name = "oldLevel")
    private String oldLevel;
    @JSONField(name = "isLock")
    private String isLock;
    @JSONField(name = "lockTime")
    private String lockTime;
    @JSONField(name = "lockUserId")
    private String lockUserId;
    @JSONField(name = "sourceLink")
    private String sourceLink;
    @JSONField(name = "subscribeNum")
    private String subscribeNum;
    @JSONField(name = "onlineUserId")
    private String onlineUserId;
    @JSONField(name = "onlineUserName")
    private String onlineUserName;
    @JSONField(name = "onlineReason")
    private String onlineReason;
    @JSONField(name = "auditReason")
    private String auditReason;
    @JSONField(name = "haveVideo")
    private String haveVideo;
    @JSONField(name = "lastArticleTime")
    private String lastArticleTime;
    @JSONField(name = "lastVideoTime")
    private String lastVideoTime;
    @JSONField(name = "isEdit")
    private String isEdit;
    @JSONField(name = "isInternal")
    private String isInternal;
    @JSONField(name = "createSource")
    private String createSource;
    @JSONField(name = "isAuthority")
    private String isAuthority;
    @JSONField(name = "operatorName")
    private String operatorName;
    @JSONField(name = "idCard")
    private String idCard;
    @JSONField(name = "idCardImg")
    private String idCardImg;
    @JSONField(name = "operatorAddress")
    private String operatorAddress;
    @JSONField(name = "operatorMail")
    private String operatorMail;
    @JSONField(name = "operatorTelephone")
    private String operatorTelephone;
    @JSONField(name = "otherContacts")
    private String otherContacts;
    @JSONField(name = "supportMaterials")
    private String supportMaterials;
    @JSONField(name = "materialCertificateImg")
    private String materialCertificateImg;
    @JSONField(name = "organizationName")
    private String organizationName;
    @JSONField(name = "organizationAddress")
    private String organizationAddress;
    @JSONField(name = "mediaCodePic")
    private String mediaCodePic;
    @JSONField(name = "contractPic")
    private String contractPic;
    @JSONField(name = "officialWebsite")
    private String officialWebsite;
    @JSONField(name = "integralStatus")
    private String integralStatus;
    @JSONField(name = "highQuality")
    private String highQuality;
    @JSONField(name = "updateUserId")
    private String updateUserId;
    @JSONField(name = "updateUserName")
    private String updateUserName;
    @JSONField(name = "accountRatingGroup")
    private String accountRatingGroup;

    public DimAccountBean() {
        this.accId = Common.BEANFIELDDEFAULT;
        this.eAccountId = Common.BEANFIELDDEFAULT;
        this.eId = Common.BEANFIELDDEFAULT;
        this.weMediaName = Common.BEANFIELDDEFAULT;
        this.weMediaDesc = Common.BEANFIELDDEFAULT;
        this.fhtId = Common.BEANFIELDDEFAULT;
        this.copyright = Common.BEANFIELDDEFAULT;
        this.fhhCopyright = Common.BEANFIELDDEFAULT;
        this.online = Common.BEANFIELDDEFAULT;
        this.field = Common.BEANFIELDDEFAULT;
        this.fieldlevel = Common.BEANFIELDDEFAULT;
        this.accountType = Common.BEANFIELDDEFAULT;
        this.accounttypeid = Common.BEANFIELDDEFAULT;
        this.wemediatypeid = Common.BEANFIELDDEFAULT;
        this.weMediaType = Common.BEANFIELDDEFAULT;
        this.applyTime = Common.BEANFIELDDEFAULT;
        this.status = Common.BEANFIELDDEFAULT;
        this.auditUserName = Common.BEANFIELDDEFAULT;
        this.auditUserId = Common.BEANFIELDDEFAULT;
        this.accountWeight = Common.BEANFIELDDEFAULT;
        this.auditTime = Common.BEANFIELDDEFAULT;
        this.level = Common.BEANFIELDDEFAULT;
        this.videoChannel = Common.BEANFIELDDEFAULT;
        this.articleChannel = Common.BEANFIELDDEFAULT;
        this.priv = Common.BEANFIELDDEFAULT;
        this.fieldcreatetime = Common.BEANFIELDDEFAULT;
        this.fieldtype = Common.BEANFIELDDEFAULT;
        this.accountQuality = Common.BEANFIELDDEFAULT;
        this.accountQualityName = Common.BEANFIELDDEFAULT;
        this.updateTime = Common.BEANFIELDDEFAULT;
        this.onlineTime = Common.BEANFIELDDEFAULT;
        this.pubnum = Common.BEANFIELDDEFAULT;
        this.onlinenum = Common.BEANFIELDDEFAULT;
        this.orignum = Common.BEANFIELDDEFAULT;
        this.offlinenum = Common.BEANFIELDDEFAULT;
        this.vev = Common.BEANFIELDDEFAULT;
        this.video_score = Common.BEANFIELDDEFAULT;
        this.video_categor = Common.BEANFIELDDEFAULT;
        this.video_timeliness = Common.BEANFIELDDEFAULT;
        this.video_level = Common.BEANFIELDDEFAULT;
        this.doc_category = Common.BEANFIELDDEFAULT;
        this.doc_level = Common.BEANFIELDDEFAULT;
        this.doc_score = Common.BEANFIELDDEFAULT;
        this.isratings = Common.BEANFIELDDEFAULT;
        this.ratingsdate = Common.BEANFIELDDEFAULT;
        this.oldLevel = Common.BEANFIELDDEFAULT;
        this.isLock = Common.BEANFIELDDEFAULT;
        this.lockTime = Common.BEANFIELDDEFAULT;
        this.lockUserId = Common.BEANFIELDDEFAULT;
        this.sourceLink = Common.BEANFIELDDEFAULT;
        this.subscribeNum = Common.BEANFIELDDEFAULT;
        this.onlineUserId = Common.BEANFIELDDEFAULT;
        this.onlineUserName = Common.BEANFIELDDEFAULT;
        this.onlineReason = Common.BEANFIELDDEFAULT;
        this.auditReason = Common.BEANFIELDDEFAULT;
        this.haveVideo = Common.BEANFIELDDEFAULT;
        this.lastArticleTime = Common.BEANFIELDDEFAULT;
        this.lastVideoTime = Common.BEANFIELDDEFAULT;
        this.isEdit = Common.BEANFIELDDEFAULT;
        this.isInternal = Common.BEANFIELDDEFAULT;
        this.createSource = Common.BEANFIELDDEFAULT;
        this.isAuthority = Common.BEANFIELDDEFAULT;
        this.operatorName = Common.BEANFIELDDEFAULT;
        this.idCard = Common.BEANFIELDDEFAULT;
        this.idCardImg = Common.BEANFIELDDEFAULT;
        this.operatorAddress = Common.BEANFIELDDEFAULT;
        this.operatorMail = Common.BEANFIELDDEFAULT;
        this.operatorTelephone = Common.BEANFIELDDEFAULT;
        this.otherContacts = Common.BEANFIELDDEFAULT;
        this.supportMaterials = Common.BEANFIELDDEFAULT;
        this.materialCertificateImg = Common.BEANFIELDDEFAULT;
        this.organizationName = Common.BEANFIELDDEFAULT;
        this.organizationAddress = Common.BEANFIELDDEFAULT;
        this.mediaCodePic = Common.BEANFIELDDEFAULT;
        this.contractPic = Common.BEANFIELDDEFAULT;
        this.officialWebsite = Common.BEANFIELDDEFAULT;
        this.integralStatus = Common.BEANFIELDDEFAULT;
        this.highQuality = Common.BEANFIELDDEFAULT;
        this.updateUserId = Common.BEANFIELDDEFAULT;
        this.updateUserName = Common.BEANFIELDDEFAULT;
        this.accountRatingGroup = Common.BEANFIELDDEFAULT;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(String eAccountId) {
        this.eAccountId = eAccountId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getWeMediaName() {
        return weMediaName;
    }

    public void setWeMediaName(String weMediaName) {
        this.weMediaName = weMediaName;
    }

    public String getWeMediaDesc() {
        return weMediaDesc;
    }

    public void setWeMediaDesc(String weMediaDesc) {
        this.weMediaDesc = weMediaDesc;
    }

    public String getFhtId() {
        return fhtId;
    }

    public void setFhtId(String fhtId) {
        this.fhtId = fhtId;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getFhhCopyright() {
        return fhhCopyright;
    }

    public void setFhhCopyright(String fhhCopyright) {
        this.fhhCopyright = fhhCopyright;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldlevel() {
        return fieldlevel;
    }

    public void setFieldlevel(String fieldlevel) {
        this.fieldlevel = fieldlevel;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccounttypeid() {
        return accounttypeid;
    }

    public void setAccounttypeid(String accounttypeid) {
        this.accounttypeid = accounttypeid;
    }

    public String getWemediatypeid() {
        return wemediatypeid;
    }

    public void setWemediatypeid(String wemediatypeid) {
        this.wemediatypeid = wemediatypeid;
    }

    public String getWeMediaType() {
        return weMediaType;
    }

    public void setWeMediaType(String weMediaType) {
        this.weMediaType = weMediaType;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        try {
            this.applyTime = TimeUtil.getTimestampToDateStr(Long.parseLong(applyTime));
        } catch (Exception ex) {
            this.applyTime = applyTime;
            ex.printStackTrace();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAccountWeight() {
        return accountWeight;
    }

    public void setAccountWeight(String accountWeight) {
        this.accountWeight = accountWeight;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        try {
            this.auditTime = TimeUtil.getTimestampToDateStr(Long.parseLong(auditTime));
        } catch (Exception ex) {
            this.auditTime = auditTime;
            ex.printStackTrace();
        }
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVideoChannel() {
        return videoChannel;
    }

    public void setVideoChannel(String videoChannel) {
        this.videoChannel = videoChannel;
    }

    public String getArticleChannel() {
        return articleChannel;
    }

    public void setArticleChannel(String articleChannel) {
        this.articleChannel = articleChannel;
    }

    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    public String getFieldcreatetime() {
        return fieldcreatetime;
    }

    public void setFieldcreatetime(String fieldcreatetime) {
        this.fieldcreatetime = fieldcreatetime;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getAccountQuality() {
        return accountQuality;
    }

    public void setAccountQuality(String accountQuality) {
        this.accountQuality = accountQuality;
    }

    public String getAccountQualityName() {
        return accountQualityName;
    }

    public void setAccountQualityName(String accountQualityName) {
        this.accountQualityName = accountQualityName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        try {
            this.updateTime = TimeUtil.getTimestampToDateStr(Long.parseLong(updateTime));
        } catch (Exception ex) {
            this.updateTime = updateTime;
            ex.printStackTrace();
        }
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        try {
            this.onlineTime = TimeUtil.getTimestampToDateStr(Long.parseLong(onlineTime));
        } catch (Exception ex) {
            this.onlineTime = onlineTime;
            ex.printStackTrace();
        }
    }

    public String getPubnum() {
        return pubnum;
    }

    public void setPubnum(String pubnum) {
        this.pubnum = pubnum;
    }

    public String getOnlinenum() {
        return onlinenum;
    }

    public void setOnlinenum(String onlinenum) {
        this.onlinenum = onlinenum;
    }

    public String getOrignum() {
        return orignum;
    }

    public void setOrignum(String orignum) {
        this.orignum = orignum;
    }

    public String getOfflinenum() {
        return offlinenum;
    }

    public void setOfflinenum(String offlinenum) {
        this.offlinenum = offlinenum;
    }

    public String getVev() {
        return vev;
    }

    public void setVev(String vev) {
        this.vev = vev;
    }

    public String getVideo_score() {
        return video_score;
    }

    public void setVideo_score(String video_score) {
        this.video_score = video_score;
    }

    public String getVideo_categor() {
        return video_categor;
    }

    public void setVideo_categor(String video_categor) {
        this.video_categor = video_categor;
    }

    public String getVideo_timeliness() {
        return video_timeliness;
    }

    public void setVideo_timeliness(String video_timeliness) {
        this.video_timeliness = video_timeliness;
    }

    public String getVideo_level() {
        return video_level;
    }

    public void setVideo_level(String video_level) {
        this.video_level = video_level;
    }

    public String getDoc_category() {
        return doc_category;
    }

    public void setDoc_category(String doc_category) {
        this.doc_category = doc_category;
    }

    public String getDoc_level() {
        return doc_level;
    }

    public void setDoc_level(String doc_level) {
        this.doc_level = doc_level;
    }

    public String getDoc_score() {
        return doc_score;
    }

    public void setDoc_score(String doc_score) {
        this.doc_score = doc_score;
    }

    public String getIsratings() {
        return isratings;
    }

    public void setIsratings(String isratings) {
        this.isratings = isratings;
    }

    public String getRatingsdate() {
        return ratingsdate;
    }

    public void setRatingsdate(String ratingsdate) {
        this.ratingsdate = ratingsdate;
    }

    public String getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(String oldLevel) {
        this.oldLevel = oldLevel;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(String lockUserId) {
        this.lockUserId = lockUserId;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(String subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public String getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(String onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public String getOnlineUserName() {
        return onlineUserName;
    }

    public void setOnlineUserName(String onlineUserName) {
        this.onlineUserName = onlineUserName;
    }

    public String getOnlineReason() {
        return onlineReason;
    }

    public void setOnlineReason(String onlineReason) {
        this.onlineReason = onlineReason;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getHaveVideo() {
        return haveVideo;
    }

    public void setHaveVideo(String haveVideo) {
        this.haveVideo = haveVideo;
    }

    public String getLastArticleTime() {
        return lastArticleTime;
    }

    public void setLastArticleTime(String lastArticleTime) {
        this.lastArticleTime = lastArticleTime;
    }

    public String getLastVideoTime() {
        return lastVideoTime;
    }

    public void setLastVideoTime(String lastVideoTime) {
        this.lastVideoTime = lastVideoTime;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(String isInternal) {
        this.isInternal = isInternal;
    }

    public String getCreateSource() {
        return createSource;
    }

    public void setCreateSource(String createSource) {
        this.createSource = createSource;
    }

    public String getIsAuthority() {
        return isAuthority;
    }

    public void setIsAuthority(String isAuthority) {
        this.isAuthority = isAuthority;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg;
    }

    public String getOperatorAddress() {
        return operatorAddress;
    }

    public void setOperatorAddress(String operatorAddress) {
        this.operatorAddress = operatorAddress;
    }

    public String getOperatorMail() {
        return operatorMail;
    }

    public void setOperatorMail(String operatorMail) {
        this.operatorMail = operatorMail;
    }

    public String getOperatorTelephone() {
        return operatorTelephone;
    }

    public void setOperatorTelephone(String operatorTelephone) {
        this.operatorTelephone = operatorTelephone;
    }

    public String getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts(String otherContacts) {
        this.otherContacts = otherContacts;
    }

    public String getSupportMaterials() {
        return supportMaterials;
    }

    public void setSupportMaterials(String supportMaterials) {
        this.supportMaterials = supportMaterials;
    }

    public String getMaterialCertificateImg() {
        return materialCertificateImg;
    }

    public void setMaterialCertificateImg(String materialCertificateImg) {
        this.materialCertificateImg = materialCertificateImg;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getMediaCodePic() {
        return mediaCodePic;
    }

    public void setMediaCodePic(String mediaCodePic) {
        this.mediaCodePic = mediaCodePic;
    }

    public String getContractPic() {
        return contractPic;
    }

    public void setContractPic(String contractPic) {
        this.contractPic = contractPic;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public String getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(String integralStatus) {
        this.integralStatus = integralStatus;
    }

    public String getHighQuality() {
        return highQuality;
    }

    public void setHighQuality(String highQuality) {
        this.highQuality = highQuality;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getAccountRatingGroup() {
        return accountRatingGroup;
    }

    public void setAccountRatingGroup(String accountRatingGroup) {
        this.accountRatingGroup = accountRatingGroup;
    }

    @Override
    public String toString() {
        return "DimAccountBean{" +
                "accId='" + accId + '\'' +
                ", eAccountId='" + eAccountId + '\'' +
                ", eId='" + eId + '\'' +
                ", weMediaName='" + weMediaName + '\'' +
                ", weMediaDesc='" + weMediaDesc + '\'' +
                ", fhtId='" + fhtId + '\'' +
                ", copyright='" + copyright + '\'' +
                ", fhhCopyright='" + fhhCopyright + '\'' +
                ", online='" + online + '\'' +
                ", field='" + field + '\'' +
                ", fieldlevel='" + fieldlevel + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accounttypeid='" + accounttypeid + '\'' +
                ", wemediatypeid='" + wemediatypeid + '\'' +
                ", weMediaType='" + weMediaType + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", status='" + status + '\'' +
                ", auditUserName='" + auditUserName + '\'' +
                ", auditUserId='" + auditUserId + '\'' +
                ", accountWeight='" + accountWeight + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", level='" + level + '\'' +
                ", videoChannel='" + videoChannel + '\'' +
                ", articleChannel='" + articleChannel + '\'' +
                ", priv='" + priv + '\'' +
                ", fieldcreatetime='" + fieldcreatetime + '\'' +
                ", fieldtype='" + fieldtype + '\'' +
                ", accountQuality='" + accountQuality + '\'' +
                ", accountQualityName='" + accountQualityName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", onlineTime='" + onlineTime + '\'' +
                ", pubnum='" + pubnum + '\'' +
                ", onlinenum='" + onlinenum + '\'' +
                ", orignum='" + orignum + '\'' +
                ", offlinenum='" + offlinenum + '\'' +
                ", vev='" + vev + '\'' +
                ", video_score='" + video_score + '\'' +
                ", video_categor='" + video_categor + '\'' +
                ", video_timeliness='" + video_timeliness + '\'' +
                ", video_level='" + video_level + '\'' +
                ", doc_category='" + doc_category + '\'' +
                ", doc_level='" + doc_level + '\'' +
                ", doc_score='" + doc_score + '\'' +
                ", isratings='" + isratings + '\'' +
                ", ratingsdate='" + ratingsdate + '\'' +
                ", oldLevel='" + oldLevel + '\'' +
                ", isLock='" + isLock + '\'' +
                ", lockTime='" + lockTime + '\'' +
                ", lockUserId='" + lockUserId + '\'' +
                ", sourceLink='" + sourceLink + '\'' +
                ", subscribeNum='" + subscribeNum + '\'' +
                ", onlineUserId='" + onlineUserId + '\'' +
                ", onlineUserName='" + onlineUserName + '\'' +
                ", onlineReason='" + onlineReason + '\'' +
                ", auditReason='" + auditReason + '\'' +
                ", haveVideo='" + haveVideo + '\'' +
                ", lastArticleTime='" + lastArticleTime + '\'' +
                ", lastVideoTime='" + lastVideoTime + '\'' +
                ", isEdit='" + isEdit + '\'' +
                ", isInternal='" + isInternal + '\'' +
                ", createSource='" + createSource + '\'' +
                ", isAuthority='" + isAuthority + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", idCardImg='" + idCardImg + '\'' +
                ", operatorAddress='" + operatorAddress + '\'' +
                ", operatorMail='" + operatorMail + '\'' +
                ", operatorTelephone='" + operatorTelephone + '\'' +
                ", otherContacts='" + otherContacts + '\'' +
                ", supportMaterials='" + supportMaterials + '\'' +
                ", materialCertificateImg='" + materialCertificateImg + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationAddress='" + organizationAddress + '\'' +
                ", mediaCodePic='" + mediaCodePic + '\'' +
                ", contractPic='" + contractPic + '\'' +
                ", officialWebsite='" + officialWebsite + '\'' +
                ", integralStatus='" + integralStatus + '\'' +
                ", highQuality='" + highQuality + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", accountRatingGroup='" + accountRatingGroup + '\'' +
                '}';
    }
}
