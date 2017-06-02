package com.xish.datacenterservice.util.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/9/25.
 */
public enum SourceTypeTransformEnum {
    TOPIC("topic", "topic", "专题"),
    TOPIC_H5("topic_h5", "topic", "专题"),
    OP_CALENDAR("op_calendar", "operating", "运营位"),
    OP_DAILYLIST("op_dailylist", "operating", "运营位"),
    OP_GENRE("op_genre", "operating", "运营位"),
    OP_NEARBY("op_nearby", "operating", "运营位"),
    OP_TOPIC("op_topic", "operating", "运营位"),
    OP_ACT("op_act", "operating", "运营位"),
    OP_TAG("op_tag", "operating", "运营位"),
    OP_TOPIC_DISNEY("op_topic_disney", "operating", "运营位"),
    OP_TOPIC_H5("op_topic_h5", "operating", "运营位"),
    OP_MARKETING("op_marketing", "operating", "运营位"),
    OP_SEARCH("op_search", "operating", "运营位"),
    OP_HOTTAG("op_hottag", "operating", "运营位"),
    PERSONAL_CENTER("personal_center", "personal_center", "用户个人主页"),
    XMR("xmr", "xmr", "小末推荐"),
    XMR_CALENDAR("xmr_calendar", "xmr", "小末推荐"),
    XMR_DAILYLIST("xmr_dailylist", "xmr", "小末推荐"),
    XMR_GENRE("xmr_genre", "xmr", "小末推荐"),
    XMR_NEARBY("xmr_nearby", "xmr", "小末推荐"),
    XMR_TOPIC("xmr_topic", "xmr", "小末推荐"),
    XMR_ACT("xmr_act", "xmr", "小末推荐"),
    XMR_TAG("xmr_tag", "xmr", "小末推荐"),
    XMR_TOPIC_DISNEY("xmr_topic_disney", "xmr", "小末推荐"),
    XMR_TOPIC_H5("xmr_topic_h5", "xmr", "小末推荐"),
    XMR_MARKETING("xmr_marketing", "xmr", "小末推荐"),
    XMR_SEARCH("xmr_search", "xmr", "小末推荐"),
    XMR_HOTTAG("xmr_hottag", "xmr", "小末推荐"),
    MESSAGE("message", "message", "消息"),
    MESSAGE_NOTIFICA("message_notifica", "message", "消息"),
    MESSAGE_REVIEW("message_review", "message", "消息"),
    MESSAGE_UP("message_up", "message", "消息"),
    PUSH_CALENDAR("push_calendar", "push", "推送"),
    PUSH_DAILYLIST("push_dailylist", "push", "推送"),
    PUSH_GENRE("push_genre", "push", "推送"),
    PUSH_NEARBY("push_nearby", "push", "推送"),
    PUSH_TOPIC("push_topic", "push", "推送"),
    PUSH_ACT("push_act", "push", "推送"),
    PUSH_TAG("push_tag", "push", "推送"),
    PUSH_TOPIC_DISNEY("push_topic_disney", "push", "推送"),
    PUSH_TOPIC_H5("push_topic_h5", "push", "推送"),
    PUSH_MARKETING("push_marketing", "push", "推送"),
    PUSH_SEARCH("push_search", "push", "推送"),
    PUSH_HOTTAG("push_hottag", "push", "推送"),
    ACT_RECOMMEND("act_recommend", "recommend", "推荐"),
    GUESS("guess", "recommend", "推荐"),
    ORDER_SF("order_sf", "recommend", "推荐"),
    SEARCH("search", "search", "搜索"),
    SEARCH_DISCOVER("search_discover", "search", "搜索"),
    SEARCH_HOME("search_home", "search", "搜索"),
    HOME("home", "home", "首页"),
    FAVOR("favor", "favor", "收藏"),
    MARKETING("marketing", "marketing", "市场活动营销专区"),
    SHOP("shop", "shop", "商户"),
    FILTER("filter", "filter", "筛选按钮（老版本）"),
    CALENDAR("calendar", "calendar", "日历"),
    DAILYLIST("dailylist", "dailylist", "每日榜单"),
    AD_CALENDAR("ad_calendar", "ad_startup", "开屏"),
    AD_DAILYLIST("ad_dailylist", "ad_startup", "开屏"),
    AD_GENRE("ad_genre", "ad_startup", "开屏"),
    AD_NEARBY("ad_nearby", "ad_startup", "开屏"),
    AD_TOPIC("ad_topic", "ad_startup", "开屏"),
    AD_ACT("ad_act", "ad_startup", "开屏"),
    AD_TAG("ad_tag", "ad_startup", "开屏"),
    AD_TOPIC_DISNEY("ad_topic_disney", "ad_startup", "开屏"),
    AD_TOPIC_H5("ad_topic_h5", "ad_startup", "开屏"),
    AD_MARKETING("ad_marketing", "ad_startup", "开屏"),
    AD_SEARCH("ad_search", "ad_startup", "开屏"),
    AD_HOTTAG("ad_hottag", "ad_startup", "开屏"),
    OPENAPI("openapi", "openapi", "接口"),
    NEARBY("nearby", "nearby", "附近"),
    GENRE("genre", "genre", "分类"),
    TOPTAB_CALENDAR("toptab_calendar", "toptab", "顶部通栏"),
    TOPTAB_DAILYLIST("toptab_dailylist", "toptab", "顶部通栏"),
    TOPTAB_GENRE("toptab_genre", "toptab", "顶部通栏"),
    TOPTAB_NEARBY("toptab_nearby", "toptab", "顶部通栏"),
    TOPTAB_TOPIC("toptab_topic", "toptab", "顶部通栏"),
    TOPTAB_ACT("toptab_act", "toptab", "顶部通栏"),
    TOPTAB_TAG("toptab_tag", "toptab", "顶部通栏"),
    TOPTAB_TOPIC_DISNEY("toptab_topic_disney", "toptab", "顶部通栏"),
    TOPTAB_TOPIC_H5("toptab_topic_h5", "toptab", "顶部通栏"),
    TOPTAB_MARKETING("toptab_marketing", "toptab", "顶部通栏"),
    TOPTAB_SEARCH("toptab_search", "toptab", "顶部通栏"),
    TOPTAB_HOTTAG("toptab_hottag", "toptab", "顶部通栏"),
    HOTTAG("hottag", "tag", "标签"),
    TAG("tag", "tag", "标签"),
    CMBCHINA("cmbchina", "h5", "H5"),
    COOPERATION("cooperation", "h5", "H5"),
    SHARE("share", "h5", "H5"),
    UNIONPAY("unionpay", "h5", "H5"),

    BLANK("blank", "blank", "空"),
    H5("h5", "h5", "H5");

    private String originType;
    private String type;
    private String typeName;

    SourceTypeTransformEnum(String originType, String type, String typeName) {
        this.originType = originType;
        this.type = type;
        this.typeName = typeName;
    }

    public static String getTypeByOriginType(String orginType) {
        String type = orginType;

        for(SourceTypeTransformEnum eu : SourceTypeTransformEnum.values()) {
            if(eu.getOriginType().equals(orginType)) {
                type = eu.getType();
                break;
            }
        }

        return type;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        System.out.println(SourceTypeTransformEnum.values().length);
        for(SourceTypeTransformEnum eu : SourceTypeTransformEnum.values()) {
            System.out.println(eu);
        }
    }

    @Override
    public String toString() {
        return originType + "," + type + "," + typeName;
    }
}
