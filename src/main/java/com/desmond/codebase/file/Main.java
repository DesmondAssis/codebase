package com.desmond.codebase.file;

import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/11/30.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        List<String> contentList = FileUtils.getContentByLine("/Users/gk/Downloads/tech/sqls/m_visit_page");

        for(String str : contentList) {
            String afterStr = str;
            if(visitPageReplaceMap.containsKey(str)) {
                afterStr = visitPageReplaceMap.get(str);
            }

            if(!str.equalsIgnoreCase(afterStr)) {
                System.out.println(str + "=>" + afterStr);
            }
        }
    }

    public static Map<String, String> visitPageReplaceMap = null;

    static {
        String[][] relationArr = new String[][]{
                {"HotActListViewController","DailyHotActivity"}, //首页分流列表点击更多后的列表页
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
                {"ZWShopDetailsViewController", "ShopActivity"}, // 玩商主页页面
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

        visitPageReplaceMap = MapUtils.putAll(new HashMap<String, String>(), relationArr);
    }
}
