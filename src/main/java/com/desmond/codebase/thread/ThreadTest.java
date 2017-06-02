package com.desmond.codebase.thread;

import com.desmond.codebase.date.DateTimeUtils;
import com.desmond.codebase.hash.DataVisitConsistentHashUtil;

import java.util.*;

/**
 * Created by Li.Xiaochuan on 16/4/6.
 */
public class ThreadTest {

    static List<String>  resultList = new ArrayList<>();
    static Set<String> resultSet = new HashSet<>();

        private static Map<Integer, List<String>> map = new HashMap<Integer, List<String>>() {
            {put(0, new ArrayList<>());}
        {put(1, new ArrayList<>());}
        {put(2, new ArrayList<>());}
        {put(3, new ArrayList<>());}
        {put(4, new ArrayList<>());}
        {put(5, new ArrayList<>());}
        {put(6, new ArrayList<>());}
        {put(7, new ArrayList<>());}
        {put(8, new ArrayList<>());}
        {put(9, new ArrayList<>());}
        {put(10, new ArrayList<>());}
        {put(11, new ArrayList<>());}
        {put(12, new ArrayList<>());}
        {put(13, new ArrayList<>());}
        {put(14, new ArrayList<>());}
        {put(15, new ArrayList<>());}
    };

    static  class TestRun implements Runnable {
        private String uuid;

        public TestRun(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public void run() {
            Integer key = DataVisitConsistentHashUtil.get(uuid);
            List<String> dataList = map.get(key);
//            System.out.println("out:" + key + ":" + Thread.currentThread().getName() + ":" + DateTimeUtils.formatNow());
            synchronized (key) {
//                System.out.println("--->in:" + key + ":" + Thread.currentThread().getName() + ":" + DateTimeUtils.formatNow());

                dataList.add(uuid);
                if(dataList.size() > 5) {
                    List<String> tmpList= new ArrayList<>(dataList);
                    resultList.addAll(tmpList);
                    resultSet.addAll(tmpList);

                    dataList.clear();
                }

                System.out.println(key + "result:" + resultList.size() + ":" + resultSet.size() + ":" + dataList.size());
            }
        }
    }


    public static void main(String[] args) {

        for(String uuid : list) {
            Thread t = new Thread(new TestRun(uuid));
            t.start();
        }


    }

    public static void showServer(Map<Integer, List<String>> map) {
        int sum = 0;
        for (Map.Entry<Integer, List<String>> m : map.entrySet()) {
            sum += m.getValue().size();
            System.out.println("服务器 " + m.getKey() + "----" + m.getValue().size() + "个" + m.getValue().get(0));
        }
        System.out.println("::" + sum);
    }

    private final  static List<String>  list = Arrays.asList(

            "00000000-04fe-22dc-7a4c-d4a30033c587",
            "00000000-0506-7e28-79de-4e020033c587",
            "00000000-06d8-7e4f-af84-d2a30033c587",
            "00000000-0708-cf9e-d26c-f0c30033c587",
            "00000000-088c-0c64-24b2-f21d0033c587",
            "00000000-0b03-2468-8fc2-84750033c587",
            "00000000-0c21-13a4-0033-c5870033c587",
            "00000000-0e2e-d29e-f017-917c0033c587",
            "00000000-0f1b-34d0-0fe7-892a0033c587",
            "00000000-10af-093c-0000-000000000000",
            "00000000-10af-093c-2db6-4a7f49d53ced",
            "00000000-1216-7313-1614-43d90033c587",
            "00000000-13d5-c2c3-5288-26b211365143",
            "00000000-13d8-b92e-0033-c5870033c587",
            "00000000-168c-f271-ffff-ffffcca74f7f",
            "00000000-17be-a693-81aa-52040033c587",
            "00000000-1a65-ec44-b86a-7d640033c587",
            "00000000-1ab6-fb07-ceb4-f1231b3f84a4",
            "00000000-1c2a-de6c-ffff-ffffd498a5c9",
            "00000000-1d2b-7db1-4ace-1f970033c587",
            "00000000-1e79-fcbd-130b-f00d1b3f84a4",
            "00000000-1ff2-7845-5a87-6bc90033c587",
            "00000000-2012-eb61-ffff-ffff99d603a9",
            "00000000-2058-c9b6-efc8-ae020033c587",
            "00000000-2250-6b4c-e257-e1e20033c587",
            "00000000-2614-d9c6-abc6-1a1d7e592705",
            "00000000-2614-d9c6-abc6-1a1d7e592705bak",
            "00000000-266e-df41-243d-cab70033c587",
            "00000000-2845-e583-ffff-ffffd498a5c9",
            "00000000-28c2-8752-9651-38680033c587",
            "00000000-29d3-5d30-ce53-c7d60033c587",
            "00000000-2b99-2d1d-ffff-ffffeddd3a0d",
            "00000000-2bc4-3ea2-16ff-ab1162cce3ff",
            "00000000-2cc5-92a1-e5f3-e59532b73cbf",
            "00000000-2dbe-0364-4013-afac0033c587",
            "00000000-2fb3-26dc-b622-fc7d0033c587",
            "00000000-3323-e534-a692-91940033c587",
            "00000000-3393-3b52-9b67-db950033c587",
            "00000000-353e-41e3-ffff-ffffc838eb02",
            "00000000-3b80-208a-0033-c5870033c587",
            "00000000-3de2-eb2a-0033-c5870033c587",
            "00000000-3fd0-c433-921a-e6af00000000",
            "00000000-4059-6221-abc6-1a1d7e592705",
            "00000000-4069-4733-0033-c5870033c587",
            "00000000-40d7-d3ec-db5c-6d6c0033c587",
            "00000000-40e1-1fb8-ffff-ffffb4a802bc",
            "00000000-43aa-a170-a692-91940033c587",
            "00000000-43aa-a170-ffff-ffffd498a5c9",
            "00000000-4483-08fa-ffff-ffffd498a5c9",
            "00000000-45a7-83b1-e98f-8bd60033c587",
            "00000000-470d-2c66-82ec-52ca0033c587",
            "00000000-4c29-8dbd-ffff-ffff99d603a9",
            "00000000-4f87-8e2d-5070-54430033c587",
            "00000000-5045-45dc-ffff-ffffd498a5c9",
            "00000000-524c-87d6-044b-fa5e0033c587",
            "00000000-5484-edcd-cd04-70d90033c587",
            "00000000-550e-068f-0033-c5870033c587",
            "00000000-5754-16fa-0033-c5870033c587",
            "00000000-57f5-26ae-5f3e-64830033c587",
            "00000000-5b0c-2724-ffff-ffffcd841cea",
            "00000000-5d26-9e02-af6a-fafa00000000",
            "00000000-5df2-0b1c-6d8a-6e030033c587",
            "00000000-5e5a-96b7-1817-1e9e00000000",
            "00000000-5ea3-0eee-79fb-0df40033c587",
            "00000000-61e5-e72e-ffff-ffff9b468ba5",
            "00000000-61e5-e72e-ffff-ffff9d99e38e",
            "00000000-62fe-530c-ffff-ffff99d603a9",
            "00000000-633f-7047-19d0-25c80033c587",
            "00000000-6488-101e-14a9-e7fb0033c587",
            "00000000-67c7-3800-e32a-bdf00033c587",
            "00000000-67c7-3800-e32a-bdf00033c587bak",
            "00000000-69d6-0a8c-a692-91940033c587",
            "00000000-6a2b-becb-b932-7a731ee7dbcb",
            "00000000-6b8e-373a-0033-c5870033c587",
            "00000000-6c52-1389-ffff-ffffc0b7a409",
            "00000000-7224-07a9-cb68-e0350033c587",
            "00000000-72f4-6be0-c191-9b5d00000000",
            "00000000-757f-dc50-1a61-59b300000000",
            "00000000-77bc-331f-ffff-ffff99d603a9",
            "00000000-785d-0aa1-0883-17834ed800e2",
            "00000000-78e1-c160-97c0-25bc0033c587",
            "00000000-78e1-c160-97c0-25bc0033c587bak",
            "00000000-79f4-f369-e58b-7f530033c587",
            "00000000-7a8b-9b08-acd2-96ee4e5782c2",
            "00000000-7e53-b29b-2d08-dcad0033c587",
            "0037438B-6AFA-4582-9043-C5FD9CD9084A",
            "004349D7-7B95-40D0-B10F-BEB4109DC7D8",
            "005D06BF-DF44-4287-9D70-5CB307C75692",
            "0083F22E-78D5-4007-8EB2-8954101A430C",
            "00A472EB-75E7-427F-96E2-0210822BBA71",
            "00C2F3D6-0452-4FCA-8E68-B17347C7A6B1",
            "00EF0665-01F8-4F4C-9A42-8F284D51FBDA",
            "017D1349-B8D7-4E04-9949-54887F1790D7",
            "01A32D36-12B7-4969-B41B-D4D7C82085CE",
            "01B422B3-DB85-489E-BD3D-59C503C9A83A",
            "01CFCC7F-C565-4F0D-9B97-5FA58C764DB6",
            "020190C6-56E1-414D-BD52-EE31C0D5F315",
            "02055B7B-46D2-42C2-9108-45EF6673F787",
            "022BD73D-101B-46AB-909B-2DF2D8F1A4EC",
            "026DFEB6-8460-44DE-ADC2-42781833BA2B",
            "0285B236-4B1F-4A37-A026-1D9F5E302B3F",
            "02BE0283-47F5-4AE6-8F4D-4905B62EAA3D",
            "02E0DCBD-5B83-47DE-A1CE-308B0E00E330",
            "02E82937-9B82-44DF-A086-C09DAAEA4171",
            "035AB1F2-839A-4FBC-A1BA-56F3626C7F1A",
            "035CB31A-C263-482A-B1B3-FF31402C8106",
            "0387BA78-A050-49D4-AD42-09AA93EDA9C6",
            "03985D75-E2DE-42D0-8090-9622BA91FD96",
            "03A71584-F80B-4D22-BC1D-F5E8F0D309EA",
            "03CBADEC-6803-4CAA-8148-7F79E70AE04B",
            "03F63B9C-1222-493E-9267-69322B0A6943",
            "044673F8-5B92-420E-AC74-9FFEB6B5E303",
            "04A07F81-5A0D-4688-BCCD-91552326043D",
            "04ACA51C-A5A2-40AB-9F2F-5F10CFAC5BAF",
            "04D5C2AF-1A01-4E3C-ABE4-FA63FD6F69F6",
            "0510380F-2539-4D87-87F2-BB434D9D5160",
            "0514E309-87F4-4027-A591-4405908D6E5A",
            "0528A29E-8055-4220-880B-13226F96BEA1",
            "0566BA1B-7EB2-47F8-9461-92601874FAF1",
            "05C7071B-6138-43FA-8FEB-9DBE11C8F383",
            "05D60776-8818-4B53-88AF-460FCEC813C3",
            "05D855AE-5C10-4875-9341-F6421D5B449E",
            "05DF07F8-DE7E-4F50-A7A0-325A88E69024",
            "05F6BD02-BC98-4CF2-8EBD-A34C44FB0A78",
            "060A7A4A-BD0C-4D9C-9FF4-0A4DB49E9084",
            "060D6CCC-480B-4E75-B57B-8E02702BA706",
            "060E0804-7B26-4BA6-B708-78F658148C78",
            "065D1B08-4359-4489-B803-741179ACF011",
            "0660E670-E35D-40FF-A816-7128B1501D4D",
            "068F545D-5FD5-45C4-BB8D-4E829BF1C3EA",
            "06DAA305-CBE1-4590-870D-C45FA6C8FF65",
            "06E9D801-3B8B-4A02-BA91-7694526DE590",
            "06EFEE5B-79E8-4DFB-8BA9-65EDD7AEE0BE",
            "072D0C2C-190E-4B77-BCF8-536611471151",
            "073B5BE7-3BC5-4CB7-9040-77AD8C30524A",
            "073CB96A-AFAF-444C-814D-61E2F088E0F1",
            "073DD661-068B-4F9E-ACA9-D93C64D22F5C",
            "074CBDD3-05BE-4A01-BAE5-2CAEE8E59202",
            "074DB627-5830-4A20-870C-E6AA3D5D75A8",
            "078588FD-68AA-4579-82B0-BA7350EDD730",
            "07E083F7-FA41-44EF-8108-C3B26B6AB33F",
            "07F557A6-088A-4219-9533-71C3CC2E5CF3",
            "07FC1F18-1AD4-4944-886F-929708B1F5F2",
            "08451F3E-F54A-46F3-90CF-03517FBA37A4",
            "084B280C-77AF-4FA7-8BE7-8989F2226656",
            "085EB927-9B7F-4DA9-B4EA-F47E34B0B9A9",
            "0899330A-6E03-49CF-B1CD-9CD1283D108C",
            "08AC68BF-AC4B-4445-A9D8-6B53D884E563",
            "08FF1373-0AAF-4CE6-B922-AE54C5D7DFCC",
            "08FF9821-E6AA-4205-80FA-8FC5E2603024",
            "0919E0CF-ADB2-4616-A5D8-25663BCDA560",
            "0923227A-7F2E-489F-AECF-B80EF9236B11",
            "092AABF9-A1C5-4096-A7F2-4DD4A28853D4",
            "0932C054-C408-4A69-A633-1B1177C3643A",
            "09584AB6-6DC5-4C04-999C-4D066029F0AD",
            "097E7137-B189-4614-94E5-66C3CB0F65A9",
            "09E65C96-AFF6-4FB0-A4AB-8FB5EE873FBA",
            "0A07B218-12FF-444B-A266-4B093FBEAC94",
            "0A229313-E782-4C8F-B643-87C80AE2FB29",
            "0A33CE53-DFCA-4D71-BAB0-6FD1EFD4EB76",
            "0A407C7D-9778-461F-8977-A615DB0EBB9E",
            "0A64E188-A382-4315-9802-117FAEE4D0BB",
            "0A9D10A4-982B-4FBA-97D0-CC3B7E97C234",
            "0AAFE770-F6E0-4454-A061-6DEA7D5D5383",
            "0AC7D590-3C6A-4B66-A60E-2D8E3E1B1A3F",
            "0AC9CA07-310F-45BE-91BF-ED78F3BF2120"
    );
}
