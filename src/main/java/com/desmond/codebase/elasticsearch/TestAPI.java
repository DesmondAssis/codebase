//package com.desmond.codebase.elasticsearch;
//
//import org.apache.commons.lang3.math.NumberUtils;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.node.Node;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import static org.elasticsearch.node.NodeBuilder.*;
//
///**
// * Created by Li.Xiaochuan on 16/2/14.
// */
//public class TestAPI {
//
//    public static void main(String[] args) throws UnknownHostException {
//        Client client = TransportClient.builder().build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//       System.out.println( NumberUtils.toDouble("121.2195600000,"));
//        System.out.println("test");
////    }
//}
