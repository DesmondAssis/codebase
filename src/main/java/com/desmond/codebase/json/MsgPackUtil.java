package com.desmond.codebase.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Li.Xiaochuan on 16/6/28.
 */
public class MsgPackUtil {
    public static void main(String[] args) {
        // Create serialize objects.
       /* List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePa msgpack = new MessagePack();
// Serialize
        byte[] raw = msgpack.write(src);

// Deserialize directly using a template
        List<String> dst1 = msgpack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst1.get(0));
        System.out.println(dst1.get(1));
        System.out.println(dst1.get(2));

// Or, Deserialze to Value then convert type.
        Value dynamic = msgpack.read(raw);
        List<String> dst2 = new Converter(dynamic)
                .read(Templates.tList(Templates.TString));
        System.out.println(dst2.get(0));
        System.out.println(dst2.get(1));
        System.out.println(dst2.get(2));*/
    }
}
