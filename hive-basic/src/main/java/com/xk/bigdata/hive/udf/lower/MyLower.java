package com.xk.bigdata.hive.udf.lower;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * MyLower
 */
@Description(name = "my_lower",
        value = "_FUNC_(str) - returns lower str",
        extended = "Example:\n"
                + "  > SELECT _FUNC_('HADOOP') FROM src LIMIT 1;\n"
                + "  'hadoop'")

public class MyLower extends UDF {
    public Text evaluate(final Text s) {
        if (s == null) {
            return null;
        }
        return new Text(s.toString().toLowerCase());
    }
}
