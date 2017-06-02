package com.desmond.codebase.map;

import com.desmond.codebase.file.FileUtils;
import com.desmond.codebase.string.RegexUtil;
import com.desmond.codebase.string.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.desmond.codebase.print.Print.print;

/**
 * Created by Li.Xiaochuan on 16/8/14.
 */
public class MapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;


    public static void main(String[] args) throws IOException {
//        generateMappings();
//        printInOrder("/Users/gk/java/doc/data_center/dau_data/actual_all.csv");
//        compare();
//        handleWhilePageList();
//        listMissing();
//        handleSourcePageList();
//        handleSourceWholeList();
        generateSql("/Users/gk/java/doc/data_center/dau_data/actual_all.csv");
        generateSql("/Users/gk/java/doc/data_center/dau_data/actual_all_new.csv");
    }

    public static void handleSourceWholeList() throws IOException {
        List<String> list = FileUtils.getContentByLine("/Users/gk/Downloads/test.java");

        List<String> resultList = new ArrayList<>();
        for(String str : list) {
            String result = RegexUtil.fetch(str);
            if(StringUtils.isNotBlank(result)) {
                resultList.add(result.substring(0, result.length() - 1).substring(1));
            }
        }

        Set<String> s = new HashSet<>(resultList);
        s.add("search_discover");
        s.add("search_home");
        s.add("message_notifica");
        s.add("message_review");
        s.add("message_up");

        int l1 =0, l2 = 0;
        Iterator<String> ite = s.iterator();
        while (ite.hasNext()) {
            l1 ++;
            String s1 = ite.next();
            if(true) {
                l2 ++;
                System.out.println(s1);
            }
        }

        System.out.println("l1: " + l1 + "l2: " + l2);
    }

    public static void handleSourcePageList() throws IOException {
        List<String> list = FileUtils.getContentByLine("/Users/gk/Downloads/business_order.sql");

        //  TOPIC("topic", "topic", "专题"),
        String tpt = "{0}(\"{1}\", \"{2}\", \"{3}\"),";

        System.out.println(list.size());
        StringBuilder sb = new StringBuilder();
        for(String str : list) {
            String[] strArr = str.split(",");
            String ot = strArr[0].trim();
            String name = strArr[1].trim();
            String flag = strArr.length == 3 ? strArr[2].trim() : ot;

            sb.append(tpt.replace("{0}", ot.toUpperCase())
                    .replace("{1}", ot)
                    .replace("{2}",flag)
                    .replace("{3}", name)
            )
                    .append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void compare() throws IOException {
        List<String> all9 = FileUtils.getContentByLine("/Users/gk/java/doc/data_center/dau_data/actual_all.csv");
        List<String> actall = FileUtils.getContentByLine("/Users/gk/java/doc/data_center/dau_data/actual_all_new.csv");

        System.out.println("all9:" + all9.size() + ",allact:" + actall.size());

        Map<String, String> strMap = new HashMap<>();
        for (String s : actall) {
            if(!strMap.containsKey(s)) {
                strMap.put(s, s);
            } else {
                System.out.println("tt:" + s);
            }

        }
        Set<String> all9Set = new HashSet<>(all9);
        Set<String> actAllSet = new HashSet<>(actall);
        System.out.println("all9Set:" + all9Set.size() + ",actAllSet:" + actAllSet.size());

        List<String> exList = all9.stream().filter(data -> !actall.contains(data)).sorted().collect(Collectors.toList());

        exList = exList.stream().filter(data -> getMap().containsKey(data)).collect(Collectors.toList());

        exList.stream().sorted().forEach(System.out::println);

        System.out.println(exList.size());
    }

    public static void handleWhilePageList() throws IOException {
        List<String> iosNotIn = Arrays.asList(
                "ZWFeatureViewController",
                "ZWWeekActListController",
                "ZWDiscoveryViewController",
                "MessageRootTVC",
                "MineContainerViewController",
                "MineViewController",
                "ZWShopMineViewController",
                "OrderListV2ViewController",
                "ZWWeekendOrderListViewController");

        List<String> iosList = FileUtils.getContentByLine("/Users/gk/Downloads/query_result.csv");
        List<String> andList = FileUtils.getContentByLine("/Users/gk/Downloads/and.csv");
        Set<String> iosPageList = new HashSet<>(iosList.size());
        Set<String> andPageList = new HashSet<>(andList.size());

        Set<String> finalSet = new HashSet<>();

        for(String str : iosList) {
            String [] arr = str.split("\\|");
            String page = arr[2].trim();

            if(iosPageList.contains(page)) {
                System.out.println("tt"  + page);
            }

            if(!iosNotIn.contains(page)) {
                iosPageList.add(page);
            }
        }

        for(String str : andList) {
            andPageList.add(str.trim());
        }

        System.out.println("ios:" + iosPageList.size());
        System.out.println("and:" + andPageList.size());
        if(87 != iosPageList.size()) {
            throw new RuntimeException("ios size");
        }

        if(71 != andPageList.size()) {
            throw new RuntimeException("and size");
        }

        iosPageList.forEach(data -> finalSet.add(getVisitPage(data)));
        andPageList.forEach(data -> finalSet.add(getVisitPage(data)));

//        iosPageList.forEach(System.out::println);
//
//        System.out.println("\n\n\n===");
//
//        andPageList.forEach(System.out::println);

        System.out.println(finalSet.size());
        List<String> finalList = new ArrayList<>(finalSet);

        finalList.stream().sorted().forEach(System.out::println);

        System.out.println(finalSet.stream().reduce((s, d) -> s + ",'" + d + "'").toString());
    }


    public static void generateSql(String file) throws IOException {
        List<String> pageList = FileUtils.getContentByLine(file);

        Set<String> st = new HashSet<>(pageList);
        System.out.println(st.size());
        System.out.println(st.stream().reduce((s, d) -> s + ",\"" + d + "\"").toString());
    }



    public static void listMissing() throws IOException {
        List<String> all9 = FileUtils.getContentByLine("/Users/gk/java/doc/data_center/dau_data/missing_page.csv");
        List<String> iosNotIn = Arrays.asList(
                "ZWFeatureViewController",
                "ZWWeekActListController",
                "ZWDiscoveryViewController",
                "MessageRootTVC",
                "MineContainerViewController",
                "MineViewController",
                "ZWShopMineViewController",
                "OrderListV2ViewController",
                "ZWWeekendOrderListViewController");

        Set<String> all9Set = new HashSet<>(all9);
        all9 = new ArrayList<>(all9Set);

//        all9 = all9.stream().filter(data -> iosNotIn.contains(data)).collect(Collectors.toList());

        all9.stream().sorted().forEach(System.out::println);

        System.out.println(all9Set.size());
    }

    private static void daikuan(int num) {
        int tmp1 = (num * 12 * (60-28) + (num*6)) * 2;
        int tmp2 = (num * 12 * (60-28) + (num*12)) * 2;

        System.out.println(num + "," + tmp1 + "," + tmp2);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static String getVisitPage(String visitPage) {
        if(ArrayUtils.contains(new String[] {"MainActListViewController", "ActListFragment"}, visitPage)) {
            visitPage = "WeekFragment";
        }

        if(getMap().containsKey(visitPage)) {
            visitPage = getMap().get(visitPage);
        }

        return visitPage;
    }

    private static String[][] relationArr = new String[][]{
            {"ZWWeekActListController", "WeekFragment"}, //本周活动
            {"MainActListViewController", "ActListFragment"}, //原本周活动
            {"ActListViewController", "PastActActivity"}, //往期活动
            {"ActDetailViewController", "ActDetailActivity"}, //活动详情
            {"ZWActDetailViewController", "ActDetailActivity"}, //活动详情
            {"ZWCategoryViewController", "MainDrawerOpen"}, //选择分类的则边框

            {"MainPOIListViewController", "PoiListFragment"}, //周边地点
            {"POIListViewController", "PoiPoiListActivity"}, //子poi列表
            {"POIDetailViewController", "POIDetailActivity"}, //商户详情/POI详情
            {"ZWPoiDetailViewController", "POIDetailActivity"}, //商户详情/POI详情
            {"POIDetailsActivity", "POIDetailActivity"}, //商户详情/POI详情
            {"ZWBigImageScrollViewController", "POIPicsViewerActivity"}, //商户相册大图显示，参数poi_id

            {"ZWCommentsViewController", "CommentActivity"}, //评论列表
            {"ZWCommentDetailTableViewController", "CommentDetailActivity"}, //评论详情页
            {"ZWSendCommentViewController", "AddCommentActivity"}, //添加评论

            {"SearchResultViewController", "SearchActivity"}, //搜索界面

            {"UserViewController", "UserActivity"}, //我的主页
            {"MainUserViewController", "UserFragment"}, //用户主页
            {"ZWUserViewController", "UserFragment"}, //用户主页
            {"ZWFavoriteActListController", "CollectionFragment"}, //我的收藏
            {"UserProfileViewController", "ProfileActivity"}, //编辑资料
            {"BindPhoneViewController", "BindMobileActivity"}, //绑定手机号
            {"ZWRegisterPhoneFirstViewController", "RegisterActivity"}, // 自有用户注册
            {"ZWLoginViewController", "LoginActivity"}, //  登陆页面
            {"ZWPhoneLoginFirstViewController", "LoginPhoneActivity"}, //   自有用户登录
            {"ZWVerifyRootViewController", "RegisterValidateActivity"}, //  自有用户手机号验证
            {"ZWRestPasswordFirstViewController", "ResetPwdPhoneActivity"}, //   自有用户忘记密码
            {"ZWRestPasswordFinalViewController", "RestPwdEditActivity"}, //  重置密码

            {"SearchCityViewController", "SearchCityActivity"}, //选择城市页面
            {"ZWFindFriendsViewController", "FindFriendActivity"}, //找新朋友
            {"ZWFriendsViewController", "FriendActivity"}, //我的好友列表
            {"UserActViewController", "UserActListActivity"}, //我的活动列表
            {"UserCommentViewController", "UserCommentActivity"}, //我的评论列表
            {"UserPOIViewController", "UserPoiListActivity"}, //我的地点

            {"ZWExploreViewController", "HotFragment"}, //旧版发现
            {"ZWTopicListViewController", "TopicFragment"}, //专题列表
            {"ZWTopicDetailViewController", "TopicDetailActivity"}, //专题详情

            {"AboutViewController", "AboutActivity"}, //关于我们
            {"MessageViewController", "MessageFragment"}, //消息列表
            {"ZWMessageViewController", "MessageFragment"}, //消息列表
            {"ZWFeedsViewController", "FeedsFragment"}, //我的动态
            {"SettingViewController", "SettingFragment"}, //应用设置
            {"FeedbackViewController", "FeedbackActivity"}, //用户反馈和我要报错
            {"ZWActFeedbackViewController", "FeedbackActivity"}, //用户反馈和我要报错
            {"ReportErroeActivity", "FeedbackActivity"}, //用户反馈和我要报错
            {"SourceViewController", "SourceActivity"}, //我有线报
            {"ZWSourceViewController", "SourceActivity"}, //线报
            {"UserAgreementViewController", "UserAgreementActivity"}, //用户使用协议
            {"HomeViewController", "HomePageActivity"},
            {"ZWAdViewController", "ADActivity"}, //   广告

            {"ZWSettingViewController", "SettingsActivity"}, //设置界面
            {"MapViewController", "GDMapActivity"}, //地图页面

            {"ZWShareViewController", "ZWShareViewController"}, //分享界面
            {"NSKVONotifying_ZWActDetailViewController", "ActDetailActivity"} // 活动详情
    };


    private static String[][] relationArrV2 = new String[][]{
            {"MainViewController", "MainActivity"}, // 主页
            {"ZWSearchViewController", "SearchActivity"}, // 搜索页
            {"ZWActListViewController", "ActListActivity"}, // 通用的活动列表页
            {"ActDetailViewController", "ActDetailActivity"}, // 活动详情页
            {"NSKVONotifying_ZWActDetailViewController", "ActDetailActivity"}, // 活动详情页
            {"ZWActDetailViewController", "ActDetailActivity"}, // 活动详情页
            {"ZWTopicDetailViewController", "TopicDetailActivity"}, // 专题详情页
            {"ShareViewController", "ShareDialogActivity"}, // 分享弹框
            {"ZWBigImageScrollViewController", "PicturesActivity"}, // 查看大图
            {"ZWUserViewController", "UserActivity"}, // 其他用户主页
            {"ZWActFeedbackViewController", "FeedbackActivity"}, // 意见反馈
            {"ZWAboutViewController", "AboutActivity"}, // 关于我们
            {"ZWUserAgreementViewController", "UserAgreementActivity"}, // 用户使用协议
            {"ZWUserSettingsViewController", "ProfileAndSettingsActivity"}, // 用户信息和设置页
            {"BindMobileViewController", "BindMobileActivity"}, // 绑定或者解除绑定号码
            {"SearchCityViewController", "SearchCityActivity"}, // 城市搜索
            {"ZWLoginViewController", "LoginActivity"}, // 登录页
            {"ZWRegisterPhoneViewController", "RegisterActivity"}, // 注册页
            {"ZWRestPasswordFirstViewController", "ResetPwdPhoneActivity"}, // 重设密码发送验证码页
            {"ZWRestPasswordFinalViewController", "RestPwdEditActivity"}, // 重设新密码页
            {"MapViewController", "GDMapActivity"}, // 地图页
            {"WebViewController", "WebViewActivity"}, // Web页
            {"ZWAdViewController", "ADActivity"}, // 广告页
            {"ZWFavoriteActListController", "UserCollectionActivity"}, // 用户收藏和关注页
            {"ZWUserConsultsViewController", "UserConsultsActivity"}, // 用户咨询页
            {"ZWShopIntroduceViewController", "ShopIntroduceActivity"}, // 玩商介绍页
            {"ZWMessageShopTableViewController", "ShopMessageActivity"}, // 玩商消息页
            {"OrderConfirmationViewController", "ConfirmOrderActivity"}, // 确认订单页
            {"CreateNewOrderViewController", "BookActivity"}, // 预订页
            {"OrderCreateViewController", "BookActivity"}, // 预订页
            {"ZWCouponsViewController", "CouponActivity"}, // 周末券页
            {"CouponsQATableViewController", "CouponInstructionsActivity"}, // 周末券使用说明
            {"ConsultListViewController", "ConsultListActivity"}, // 咨询列表页
            {"ReviewListViewController", "ReviewListActivity"}, // 评价列表页
            {"CreateReviewViewController", "AddReviewActivity"}, // 添加评价页
            {"ReviewCommentViewController", "AddReviewCommentActivity"}, // 填写或者回复评论页面
            {"ZWCommentDetailTableViewController", "ReviewDetailActivity"}, // 评论详情页面
            {"ZWShopDetailsViewControlle", "ShopActivity"}, // 玩商主页页面
            {"UserProfileViewController", "UserProfileActivity"}, // 用户资料设置页面
            {"ZWShopSimpleProfileViewController", "ShopProfileActivity"}, // 玩商填写资料页面
            {"ZWShopBandAccountViewController", "ShopAuthenticateActivity"}, // 银行账号页面
            {"ZWShopVerticateViewController", "ShopMobileVerifyActivity"}, // 填写验证码页面
            {"ActOfflineCauseViewController", "ActDownActivity"}, // 玩商下线活动添加理由页面
            {"ConsumptionVertificationViewController", "ShopConsumeVerifyActivity"}, // 手动输入验证码界面
            {"OrderInformationViewController", "ShopConfirmConsumeActivity"}, // 验证码正确确认到店页面
            {"OrderDetailViewController", "BookPayActivity"}, // 等待订单付款页面
            {"ComplainViewController", "OrderComplaintsActivity"}, // 订单投诉页面
            {"ComplainResultController", "MyComplaintsActivity"}, // 用户的投诉结果页面
            {"UserListViewController", "InterestedUserActivity"}, // 活动详情内的感兴趣的人页面
            {"PreferentialListViewController", "DiscountActivity"}, // 优惠列表页面
            {"ZWActVideoDetailViewController", "VideoActivity"}, // 播放视频页面
            {"MarketActListViewController", "MarketingDetailActivity"}, // 营销专区列表页面
            {"QRScannerViewController", "ShopVerifyActivity"}, // 验证码扫描二维码界面
            {"ZWEnableCouponsViewController", "UselessCouponActivity"}, // 不可用周末券页面
            {"ShopActivityListViewController", "ShopActActivity"}, // 活动管理页面
            {"RAAddPOIVC", "FakeLocationActivity"}, // 新增地点页面
            {"WaitReviewListViewController", "ReviewNotYetActivity"}, // 待评价列表
            {"ZWOrderListViewController", "OrderListActivity"}, // 订单列表
    };

    private static void printInOrder(String fileName) throws IOException {
        List<String> iosList = FileUtils.getContentByLine(fileName);

        iosList.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void generateMappings() throws IOException {
        List<String> iosList = FileUtils.getContentByLine("/Users/gk/java/doc/data_center/dau_data/mapping-pre.csv");

        iosList.stream().filter(data -> StringUtils.isNotBlank(data))
                .forEach(data -> {
                    String[] arr = data.split("-");
                    String contr = arr[2].trim();
                    String act = arr[1].trim();
                    String comment = arr[0].trim();

                    // {"ZWExploreViewController", "HotFragment"}, //旧版发现
                    System.out.println("{\"" + contr + "\", \"" + act + "\"}, // " + comment);
                });
    }

    private static Map<String, String> visitPageReplaceMap = null;

    private static Map<String, String> getMap() {
        if (visitPageReplaceMap == null) {
            visitPageReplaceMap = MapUtils.putAll(new HashMap<String, String>(), relationArrV2);
        }

        return visitPageReplaceMap;
    }
    /*
     -- 老版(废弃)
    SELECT from_unixtime(visit_time/1000,'%Y-%m-%d') as day ,count(DISTINCT phone_id) FROM `data_visit_year` where visit_time >= unix_timestamp('2016-8-26')*1000 and visit_time < unix_timestamp('2016-9-17')*1000 and visit_page in
('ZWMainViewController','ZWRegisterPhoneViewController','CollectionFragment','ZWShopBandAccountViewController','ReviewCommentViewController','StartActivity','OrderInformationViewController','CustomWayViewController','AddReviewCommentActivity','MarketingDetailActivity','ReviewListViewController','PicturesActivity','BookSuccessActivity','FakeLocationActivity','ShopConsumeVerifyActivity','WaitReviewListViewController','FeedbackActivity','OrderPhoneVerifyViewController','UselessCouponActivity','ZWShopDetailsViewController','InputTextViewController','ZWWelcomeViewController','SecureInfoViewController','ConsultListViewController','CouponsQATableViewController','ShopVerifyActivity','ShopActivity','ActOfflineCauseViewController','ShopProfileActivity','ShopAuthenHelpActivity','PriceExplainDialogActivity','ShopMessageActivity','CalendarActivity','OrderDetailViewController','ShopAuthenticateActivity','LocalServiceViewController','AddReviewActivity','ShopIntroduceActivity','ZWMessageShopViewController','AgeLimitedViewController','ShareDialogActivity','UserActivity','ActDetailActivity','GDMapActivity','ZWSearchViewController','POIPicsViewerActivity','RAAddTimeAndPriceVC','InterestedUserActivity','ComplainResultController','BookingTimeViewController','TopicDetailActivity','RestPwdEditActivity','NeedToOrderViewController','ProfileActivity','CreateReviewViewController','AskDialogActivity','FollowedShopTrendActivity','ZWOrderListViewController','ShopConfirmConsumeActivity','ZWShopProfileContainViewController','ReviewDetailActivity','ZWCouponsViewController','UserAgreementActivity','OrderConfirmationViewController','ReviewListActivity','ZWShopMineSearchActivitiesViewController','ActivityIntroductionViewController','CommentDetailActivity','ZWEnableCouponsViewController','ZWUpdatedViewController','MarketActListViewController','ZWAboutViewController','CreateNewOrderViewController','ActDownActivity','UserConsultsActivity','VideoActivity','RegisterValidateActivity','WebViewActivity','UserListViewController','MessageActivity','OrderCreateViewController','RAAddPOIVC','ShopActivityListViewController','AccountLoginActivity','CommentActivity','BookPayActivity','CouponInstructionsActivity','UserProfileActivity','TimeExplainDialogActivity','PreferentialListViewController','ZWUserConsultsViewController','ADActivity','RABookSettingVC','ZWShopVerticateViewController','ZWShopSimpleProfileViewController','ShopActActivity','PrepareItemsViewController','BindMobileActivity','ProfileAndSettingsActivity','ZWActListViewController','ZWUserSettingsViewController','ZWUserAgreementViewController','ComplainViewController','AboutActivity','ResetPwdPhoneActivity','ConfirmOrderActivity','BookActivity','QRScannerViewController','RegisterActivity','ShopMobileVerifyActivity','OrderListActivity','UserCollectionActivity','ZWActVideoDetailViewController','ZWMessageV2ViewController','RemindDialogActivity','MakeGroupViewController','MainActivity','ZWShopCertificationViewController','ConsumptionVertificationViewController','WebViewController','RAGenreListTVC','ZWShopProfileViewController','ZWShopGuideReleaseActPageViewController','ActListActivity','ReviewNotYetActivity','RAPOIListTVC','ZWCertificateIllustrationViewController','ConsultReplyViewController','POIDetailActivity','SearchCityActivity','DiscountActivity','RATimeAndPriceVC','ConsultationNumberViewController','OrderComplaintsActivity','ReviewDetailsViewController','LoginActivity','ActDetailPreviewActivity','MyComplaintsActivity','SearchActivity','ReleaseActTVC','DailyListActivity','CouponActivity','ZWShopIntroduceViewController','BindMobileViewController','BookDetailActivity','PreOrderInforViewController','TimeTypeDetailViewController','NearbyListActivity','ConsultListActivity','ZWShopBandAccountContainViewController') group by day order by day asc;

    -- 新版(老数据)
    SELECT from_unixtime(visit_time/1000,'%Y-%m-%d') as day ,count(DISTINCT phone_id) FROM `data_visit_year` where visit_time >= unix_timestamp('2016-8-26')*1000 and visit_time < unix_timestamp('2016-9-17')*1000 and visit_page in
('ZWRegisterPhoneViewController','CollectionFragment','ZWShopBandAccountViewController','ReviewCommentViewController','StartActivity','OrderInformationViewController','CustomWayViewController','AddReviewCommentActivity','MarketingDetailActivity','ReviewListViewController','PicturesActivity','BookSuccessActivity','FakeLocationActivity','ShopConsumeVerifyActivity','WaitReviewListViewController','FeedbackActivity','OrderPhoneVerifyViewController','UselessCouponActivity','ZWShopDetailsViewController','InputTextViewController','ZWWelcomeViewController','SecureInfoViewController','ConsultListViewController','CouponsQATableViewController','ShopVerifyActivity','ShopActivity','ActOfflineCauseViewController','ShopProfileActivity','ShopAuthenHelpActivity','ZWSearchResultViewController','PriceExplainDialogActivity','CalendarActivity','ShopMessageActivity','OrderDetailViewController','ShopAuthenticateActivity','AddReviewActivity','LocalServiceViewController','ShopIntroduceActivity','ZWMessageShopViewController','FakeSearchActivity','FilterActivity','AgeLimitedViewController','ShareDialogActivity','UserActivity','ActDetailActivity','GDMapActivity','POIPicsViewerActivity','ZWSearchViewController','InterestedUserActivity','RAAddTimeAndPriceVC','ComplainResultController','BookingTimeViewController','TopicDetailActivity','NeedToOrderViewController','RestPwdEditActivity','CreateReviewViewController','ProfileActivity','AskDialogActivity','FollowedShopTrendActivity','ZWOrderListViewController','ShopConfirmConsumeActivity','ZWShopProfileContainViewController','ReviewDetailActivity','SortActivity','ZWCouponsViewController','UserAgreementActivity','OrderConfirmationViewController','ReviewListActivity','ZWShopMineSearchActivitiesViewController','ActivityIntroductionViewController','CommentDetailActivity','ZWEnableCouponsViewController','ZWUpdatedViewController','MarketActListViewController','CreateNewOrderViewController','ZWAboutViewController','ActDownActivity','UserConsultsActivity','VideoActivity','RegisterValidateActivity','WebViewActivity','UserListViewController','MessageActivity','OrderCreateViewController','AccountLoginActivity','RAAddPOIVC','ShopActivityListViewController','BookPayActivity','CommentActivity','CouponInstructionsActivity','TimeExplainDialogActivity','UserProfileActivity','PreferentialListViewController','ZWUserConsultsViewController','ADActivity','RABookSettingVC','ShopActActivity','ZWShopSimpleProfileViewController','ZWShopVerticateViewController','BindMobileActivity','PrepareItemsViewController','ProfileAndSettingsActivity','ZWActListViewController','ZWUserAgreementViewController','ZWUserSettingsViewController','AboutActivity','ComplainViewController','ShopNewPublishActListViewController','ResetPwdPhoneActivity','BookActivity','ConfirmOrderActivity','ZWGuideLoginViewController','SearchEditViewController','QRScannerViewController','RegisterActivity','ShopMobileVerifyActivity','OrderListActivity','UserCollectionActivity','ZWActVideoDetailViewController','RemindDialogActivity','ZWMessageV2ViewController','MakeGroupViewController','MainActivity','ZWMainViewController','ZWShopCertificationViewController','ConsumptionVertificationViewController','WebViewController','RAGenreListTVC','ZWWeekFilterViewController','OrderPopViewController','ZWShopProfileViewController','ActListActivity','ZWShopGuideReleaseActPageViewController','ReviewNotYetActivity','RAPOIListTVC','ConsultReplyViewController','ZWCertificateIllustrationViewController','POIDetailActivity','GuideLoginActivity','DiscountActivity','SearchCityActivity','RATimeAndPriceVC','ConsultationNumberViewController','OrderComplaintsActivity','ReviewDetailsViewController','LoginActivity','ActDetailPreviewActivity','MyComplaintsActivity','SearchActivity','DailyListActivity','ReleaseActTVC','CouponActivity','BindMobileViewController','ZWShopIntroduceViewController','BookDetailActivity','PreOrderInforViewController','NearbyListActivity','TimeTypeDetailViewController','ConsultListActivity','ZWShopBandAccountContainViewController');


    -- 新版(新数据)
SELECT from_unixtime(visit_time/1000,'%Y-%m-%d') as day ,count(DISTINCT phone_id) FROM `data_visit_year` where visit_time >= unix_timestamp('2016-8-26')*1000 and visit_time < unix_timestamp('2016-9-17')*1000 and visit_page in
('ZWCommentsViewController','StartActivity','CustomWayViewController','AddReviewCommentActivity','MarketingDetailActivity','PicturesActivity','BookSuccessActivity','FakeLocationActivity','ShopConsumeVerifyActivity','FeedbackActivity','OrderPhoneVerifyViewController','UselessCouponActivity','ZWShopDetailsViewController','InputTextViewController','ZWWelcomeViewController','SecureInfoViewController','ShopVerifyActivity','ShopActivity','ShopProfileActivity','ShopAuthenHelpActivity','PriceExplainDialogActivity','CalendarActivity','ShopMessageActivity','ShopAuthenticateActivity','AddReviewActivity','LocalServiceViewController','ShopIntroduceActivity','ZWMessageShopViewController','AgeLimitedViewController','ShareDialogActivity','UserActivity','ActDetailActivity','GDMapActivity','InterestedUserActivity','RAAddTimeAndPriceVC','BookingTimeViewController','TopicDetailActivity','NeedToOrderViewController','RestPwdEditActivity','AskDialogActivity','FollowedShopTrendActivity','ShopConfirmConsumeActivity','ZWShopProfileContainViewController','ReviewDetailActivity','UserAgreementActivity','ReviewListActivity','ZWShopMineSearchActivitiesViewController','ActivityIntroductionViewController','ZWPoiDetailViewController','ZWUpdatedViewController','ActDownActivity','UserConsultsActivity','VideoActivity','RegisterValidateActivity','WebViewActivity','MessageActivity','AccountLoginActivity','BookPayActivity','CouponInstructionsActivity','TimeExplainDialogActivity','UserProfileActivity','ADActivity','RABookSettingVC','ShopActActivity','BindMobileActivity','PrepareItemsViewController','ProfileAndSettingsActivity','AboutActivity','ShopNewPublishActListViewController','ResetPwdPhoneActivity','BookActivity','ConfirmOrderActivity','ZWGuideLoginViewController','SearchEditViewController','RegisterActivity','ShopMobileVerifyActivity','OrderListActivity','UserCollectionActivity','RemindDialogActivity','ZWMessageV2ViewController','MakeGroupViewController','MainActivity','ZWShopCertificationViewController','ZWMainViewController','RAGenreListTVC','ZWShopProfileViewController','OrderPopViewController','ActListActivity','ZWShopGuideReleaseActPageViewController','ReviewNotYetActivity','RAPOIListTVC','ConsultReplyViewController','ZWCertificateIllustrationViewController','DiscountActivity','SearchCityActivity','RATimeAndPriceVC','ConsultationNumberViewController','OrderComplaintsActivity','ReviewDetailsViewController','LoginActivity','ActDetailPreviewActivity','MyComplaintsActivity','SearchActivity','DailyListActivity','ReleaseActTVC','CouponActivity','BookDetailActivity','PreOrderInforViewController','NearbyListActivity','TimeTypeDetailViewController','ConsultListActivity','ZWShopBandAccountContainViewController');

     */
}
