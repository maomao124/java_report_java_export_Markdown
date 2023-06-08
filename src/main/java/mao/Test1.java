package mao;

import mao.utils.MarkdownUtils;

/**
 * Project name(项目名称)：java报表_java导出Markdown
 * Package(包名): mao
 * Class(类名): Test1
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/8
 * Time(创建时间)： 13:09
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test1
{
    public static void main(String[] args)
    {
        String s = MarkdownUtils.of()
                .bigTitle("hello")
                .title("hello")
                .title("hello", MarkdownUtils.Style.BLUE)
                .subTitle("hello")
                .title("hello")
                .code()
                .text("hello12")
                .endCode()
                .text("hello").build();
        System.out.println(s);
        MarkdownUtils.write(s,"./test.md");
    }
}
