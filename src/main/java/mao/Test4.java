package mao;

import mao.utils.MarkdownUtils;

/**
 * Project name(项目名称)：java报表_java导出Markdown
 * Package(包名): mao
 * Class(类名): Test4
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/8
 * Time(创建时间)： 13:57
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test4
{
    public static void main(String[] args)
    {
        Object[] objects1 = new Object[]{"10001", "张三", "男", "18"};
        Object[] objects2 = new Object[]{"10002", "张三2", "男", "16"};
        Object[] objects3 = new Object[]{"10003", "张三3", "男", "17"};
        Object[] objects4 = new Object[]{"10004", "张三4", "男", "15"};
        Object[] objects5 = new Object[]{"10005", "张三5", "女", "12"};
        Object[] objects6 = new Object[]{"10006", "张三6", "男", "17"};

        String s = MarkdownUtils.of()

                .table()
                .data(new String[]{"编号", "姓名", "性别", "年龄"},
                        new Object[][]{objects1, objects2, objects3, objects4, objects5, objects6})
                .endTable()
                .build();
        System.out.println(s);
        MarkdownUtils.write(s, "test4.md");
    }
}
