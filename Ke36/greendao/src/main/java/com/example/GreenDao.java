package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDao {
    public static void main(String args[]) {
        //数据库图标(数据库的框架)
        //第一个参数是数据库的版本
        //第二个参数是自动生成代码的包名
        Schema schema = new Schema(1, "com.jindapeng.ke36");
        addNote(schema);
        //自动生成代码
        //两个参数 第一个参数图标对象
        //第二个参数 自动生成的代码路径
        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNote(Schema schema) {
/**
 * @param schema
 * 此方法就是为数据库里添加内容
 */
        //添加表名
        Entity entity = schema.addEntity("Collection");
        //加入Id 并且自增
        entity.addIdProperty().autoincrement().primaryKey();
        //添加类属性 根据属性生成相应的表中的字段
        entity.addStringProperty("title");
        entity.addLongProperty("publishTime");
        entity.addStringProperty("columnName");
        entity.addStringProperty("name");
        entity.addStringProperty("featureImg");
    }

}
