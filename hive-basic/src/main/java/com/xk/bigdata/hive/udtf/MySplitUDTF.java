package com.xk.bigdata.hive.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

public class MySplitUDTF extends GenericUDTF {

    List<String> list = new ArrayList<>();

    /**
     * 增加输出数据的schema
     *
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> structFieldNames = new ArrayList<>();
        List<ObjectInspector> structFieldObjectInspectors = new ArrayList<>();
        structFieldNames.add("word");
        structFieldObjectInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(structFieldNames, structFieldObjectInspectors);
    }

    /**
     * args[0] : words 输入的数据
     * args[1] : 传入的第二个参数，即分隔符
     *
     * @param args
     * @throws HiveException
     */
    @Override
    public void process(Object[] args) throws HiveException {
        if (args.length == 2) {
            String words = args[0].toString();
            String spiltFile = args[1].toString();
            String[] spilts = words.split(spiltFile);
            for (String word : spilts) {
                list.clear();
                list.add(word);
                forward(list);
            }
        } else {
            throw new HiveException("parameter error");
        }

    }

    @Override
    public void close() throws HiveException {

    }
}
