
        1.APP端
        gmv:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.genre_main as gerne,  business_activity.take_channel as channel_type,
        0 as filter_type, 0 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.genre_main, business_activity.take_channel ;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.genre_main as city,  business_activity.take_channel as channel_type,
        2 as filter_type, 0 as platform,
        sum(num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.genre_main, business_activity.take_channel ;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  business_activity.take_channel as channel_type,
        1 as filter_type, 0 as platform,
        count(order_num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id, business_activity.take_channel ;

        —- sales channel
        gmv:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  (business_activity.sale_channel +10) as channel_type,
        0 as filter_type, 0 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id, business_activity.sale_channel ;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  (business_activity.sale_channel +10) as channel_type,
        2 as filter_type, 0 as platform,
        sum(num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id, business_activity.sale_channel ;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  (business_activity.sale_channel +10) as channel_type,
        1 as filter_type, 0 as platform,
        count(order_num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id, business_activity.sale_channel ;

        - coupon
        gmv:
        SELECT  unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  20 as channel_type,
        0 as filter_type, 0 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        where  coupon_name rlike '^.*\\u793c\\u54c1\\u5361.*$'
        and business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id;

        num:
        SELECT  unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,20 as channel_type,
        2 as filter_type, 0 as platform,
        sum(num) as num
        FROM `business_order`
        where  coupon_name rlike '^.*\\u793c\\u54c1\\u5361.*$'
        and business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id;

        ordernum:
        SELECT  unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,20 as channel_type,
        1 as filter_type, 0 as platform,
        count(order_num) as num
        FROM `business_order`
        where  coupon_name rlike '^.*\\u793c\\u54c1\\u5361.*$'
        and business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 0 and business_order.distributor_id =0
        group by 1, business_order.city_id;

        2.h5  端
        gmv:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  business_activity.take_channel as channel_type,
        0 as filter_type, 1 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id, business_activity.take_channel ;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  business_activity.take_channel as channel_type,
        2 as filter_type, 1 as platform,
        sum(num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id, business_activity.take_channel ;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  business_activity.take_channel as channel_type,
        1 as filter_type, 1 as platform,
        count(order_num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id, business_activity.take_channel ;

        — sales channel
        gmv:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  (business_activity.sale_channel+10) as channel_type,
        0 as filter_type, 1 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id, business_activity.sale_channel ;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  (business_activity.sale_channel+10) as channel_type,
        2 as filter_type, 1 as platform,
        sum(num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id, business_activity.sale_channel ;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  (business_activity.sale_channel+10) as channel_type,
        1 as filter_type, 1 as platform,
        count(order_num) as num
        FROM `business_order`
        left join business_activity on business_order.wzm_activity_id = business_activity.id
        where business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id, business_activity.sale_channel ;

        —coupon
        gmv:
        SELECT  unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,
        20 as channel_type,
        0 as filter_type, 1 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        where  coupon_name rlike '^.*\\u793c\\u54c1\\u5361.*$'
        and business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id;

        num:
        SELECT  unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,
        20 as channel_type,
        2 as filter_type, 1 as platform,
        sum(num) as num
        FROM `business_order`
        where  coupon_name rlike '^.*\\u793c\\u54c1\\u5361.*$'
        and business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id;

        ordernum:
        SELECT  unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,
        20 as channel_type,
        1 as filter_type, 0 as platform,
        count(order_num) as num
        FROM `business_order`
        where  coupon_name rlike '^.*\\u793c\\u54c1\\u5361.*$'
        and business_order.STATUS > 1 and business_order.price > 0
        and business_order.is_wap = 1
        group by 1, business_order.city_id;

        3.分销
        （1）去哪儿
        gmv:
        SELECT
        unix_timestamp(from_unixtime(create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        city_id as city,
        0 as channel_type,
        0 as filter_type, 2 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id=2
        group by 1, city_id;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  0 as channel_type,
        2 as filter_type, 2 as platform,
        sum(num) as num
        FROM `business_order`
        where STATUS > 1 and price > 0
        and is_wap=0  and distributor_id=2
        group by 1, city_id;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  0 as channel_type,
        1 as filter_type, 2 as platform,
        count(order_num) as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id=2
        group by 1, city_id;

        (2)陌陌
        gmv:
        SELECT
        unix_timestamp(from_unixtime(create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        city_id as city,  1 as channel_type,
        0 as filter_type, 2 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id=1
        group by 1, city_id;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  1 as channel_type,
        2 as filter_type, 2 as platform,
        sum(num) as num
        FROM `business_order`
        where STATUS > 1 and price > 0
        and is_wap=0  and distributor_id=1
        group by 1, city_id;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  1 as channel_type,
        1 as filter_type, 2 as platform,
        count(order_num) as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id=1
        group by 1, city_id;

        (3)其他
        gmv:
        SELECT
        unix_timestamp(from_unixtime(create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        city_id as city,  2 as channel_type,
        0 as filter_type, 2 as platform,
        sum(total_cost)/100 as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id >2
        group by 1, city_id;

        num:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  2 as channel_type,
        2 as filter_type, 2 as platform,
        sum(num) as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id >2
        group by 1, city_id;

        ordernum:
        SELECT
        unix_timestamp(from_unixtime(business_order.create_time,'yyyyMMdd'),'yyyyMMdd') as day,
        business_order.city_id as city,  2 as channel_type,
        1 as filter_type, 2 as platform,
        count(order_num) as num
        FROM `business_order`
        where  STATUS > 1 and price > 0
        and is_wap=0  and distributor_id > 2
        group by 1, city_id;