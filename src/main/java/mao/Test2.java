package mao;

import mao.utils.MarkdownUtils;

/**
 * Project name(项目名称)：java报表_java导出Markdown
 * Package(包名): mao
 * Class(类名): Test2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/8
 * Time(创建时间)： 13:33
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test2
{
    public static void main(String[] args)
    {
        String s = MarkdownUtils.of()
                .ul()
                .text("hello1")
                .text("hello2")
                .text("hello3")
                .text("hello4")
                .text("hello5")
                .endUl()
                .br()
                .br()
                .ol()
                .text("hello1")
                .text("hello2")
                .text("hello3")
                .text("hello4")
                .text("hello5")
                .endOl()
                .br()
                .br()
                .ref()
                .text("hello1")
                .text("hello2")
                .text("hello3")
                .text("hello4")
                .text("hello5")
                .endRef()
                .build();
        System.out.println(s);
        MarkdownUtils.write(s, "test2.md");
    }
}
