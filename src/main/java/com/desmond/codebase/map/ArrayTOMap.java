package com.desmond.codebase.map;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 15/9/17.
 */
public class ArrayTOMap {
    public Map<String, String> arrayToMap() {
        String[][] arr = new String[][]{
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
        };

       return MapUtils.putAll(new HashMap<String, String>(), arr);
    }

    public static void main(String[] args) {
        Map<String, String> map = new ArrayTOMap().arrayToMap();
        System.out.println(map.size());
        System.out.println("ADActivity".equals(map.get("ZWAdViewController")));
        System.out.println("FeedbackActivity".equals(map.get("ZWActFeedbackViewController")));
        System.out.println("ZWShareViewController".equals(map.get("ZWShareViewController")));

        System.out.println(hexDec("1e"));
        System.out.println(hexDec("a"));
        System.out.println(hexDec("11ff"));
        System.out.println(hexDec("cceeff"));
        System.out.println(hexDec("f"));
    }

    public static int hexDec(String hex) {
        return Integer.parseInt(hex, 16);
    }


}
