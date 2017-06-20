package com.desmond.codebase.jdbc;

import com.desmond.codebase.file.FileUtils;
import com.desmond.codebase.number.NumberUtil;
import com.desmond.codebase.string.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * APP埋点数据录入
 * Created by presleyli on 2017/6/19.
 */
public class AppRecordsGenerator {
    private final static String filePath = "/Users/presleyli/Documents/wanzhoumo/data_center/app埋点测试/";
    private final static String record = "record_stg";
    private final static String path = "path_stg";

    private Map<String, Record> recordMap = new LinkedHashMap<>();
    private Map<String, Path> pathMap = new LinkedHashMap<>();
    private Map<String, Path> distinctPath = new LinkedHashMap<>();

    private ConnectionDB connectionDB = new ConnectionDB();

    public static void main(String[] args) throws IOException {
        AppRecordsGenerator generator = new AppRecordsGenerator();
        generator.fillOriginList();

        generator.check();

        generator.insertDatas();
    }

    private void insertDatas() {
        final String recordSql = getRecordSql();
        recordMap.forEach((key,record) -> {
            // ('page', 'ActDetailActivity', '活动详情页(p)')
            String type = Integer.valueOf(1).equals(record.getType())? "event" : "page";
            String desc = record.getDesc() + "(" + (Integer.valueOf(1).equals(record.getType())?"e":"p")+")";
            int id = connectionDB.executeUpdate(DataBaseEnum.DATA_CENER, recordSql, new Object[] {type, record.getName(), desc});

            record.setId(id);
        });

        final String pathSql = "INSERT INTO `hp_records_checker_path` (`type`, `category`, `name`, `desc`,`sort`)\n" +
                "VALUES\n" +
                "\t(?, ?, ?, ?, ?)";
        final List<Integer> tmpList = new ArrayList<>(); tmpList.add(1);
        distinctPath.forEach((key, path) -> {

            // (0, '首页', 'act', 'Act desc')
            int type = path.getType() - 1;
            String category = getPathCategory(path.getPosition());
            String name = path.getName();
            int id = connectionDB.executeUpdate(DataBaseEnum.DATA_CENER, pathSql, new Object[] {type, category, name, name, tmpList.size()});

            tmpList.add(1);
            path.setId(id);
        });

        final String refSql = "INSERT INTO `hp_records_checker_ref` ( `platform`, `path_id`, `record_id`, `sort`)\n" +
                "VALUES\n" +
                "\t(?, ?, ?, ?)";
        // (1, 100, 200, 1);
        pathMap.forEach((key, refPath) -> {
            Path path = distinctPath.get(refPath.getType() + "_" + refPath.getName() + "_" + refPath.getPosition());
            int platform = refPath.getPlatform();
            int pathId = path.getId();
            List<String> recordList = refPath.getRecordList();
            int sort = 1;
            for(String recordName : recordList) {
                Record record = recordMap.get(recordName);
                int recordId = record.getId();
                int id = connectionDB.executeUpdate(DataBaseEnum.DATA_CENER, refSql, new Object[] {platform, pathId, recordId, sort});
                sort ++;
            }
        });
    }

    public String getPathCategory(int position) {
        /*
        1	首页
        2	发现
        3	消息
        4	我的
        5	其他
         */
        switch (position) {
            case 1: return "首页";
            case 2: return "发现";
            case 3 : return "消息";
            case 4: return "我的";
            default: return "其他";
        }
    }

    private String getRecordSql() {
        return "INSERT INTO `hp_records_checker_records` (`type`, `name`, `desc`)\n" +
                "VALUES\n" +
                "\t(?, ?, ?)";
    }


    private void check() {
        pathMap.forEach((key, p) -> {
            for(String s : p.getRecordList()) {
                if(!recordMap.containsKey(s)) {
                    System.out.println(key + "==" + s);
                }
            }
        });

    }

    private void fillOriginList() throws IOException {
        List<String> dataStrList = FileUtils.getContentByLine(filePath + record);
        System.out.println("record：" + dataStrList.size());

        for(int i = 1; i < dataStrList.size(); i++) {
            String[] strArr = dataStrList.get(i).split(";");
            String name = strArr[1];
            recordMap.put(name, new Record(NumberUtil.toInt(strArr[0]), name, strArr[2], NumberUtil.toInt(strArr[3]), NumberUtil.toInt(strArr[4])));
        }

        dataStrList = FileUtils.getContentByLine(filePath + path);
        System.out.println("path：" + dataStrList.size());

        for(int i = 1; i < dataStrList.size(); i++) {
            String[] strArr = dataStrList.get(i).split(";");
            Path path = new Path(NumberUtil.toInt(strArr[0]), strArr[1], strArr[2], NumberUtil.toInt(strArr[3]), NumberUtil.toInt(strArr[4]));
            String key = strArr[0] + "_" + strArr[1] + "_" + strArr[3] + "_" + strArr[4];

            String dkey = strArr[0] + "_" + strArr[1] + "_" + strArr[4];
            distinctPath.put(dkey, path);
            pathMap.put(key, path);
        }

        System.out.println("record:" + recordMap.size() + ",path:" + pathMap.size());
    }

    private class Record {
        private int id;
        private int type;
        private String desc;
        private String name;
        private int platform;
        private int position;

        public Record(int type, String name, String desc, int platform, int position) {
            this.type = type;
            this.name = name;
            this.desc = desc;
            this.platform = platform;
            this.position = position;
        }

        public Record() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    private class Path {
        private int id;
        private int type;
        private String name;
        private String desc;
        private List<String> recordList = new ArrayList<>();

        private int platform;
        private int position;

        public Path() {
        }

        public Path(int type, String name, String desc, int platform, int position) {
            this.type = type;
            this.name = name;
            this.desc = desc;
            this.platform = platform;
            this.position = position;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getRecordList() {
            recordList.clear();
            if(StringUtils.isNotBlank(this.getDesc())) {
                String[] recordArr = this.getDesc().split(",");
                if(recordArr != null && recordArr.length > 0) {
                    for(String r : recordArr) {
                        recordList.add(r);
                    }
                }
            }

            return recordList;
        }

        public void setRecordList(List<String> recordList) {
            this.recordList = recordList;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
