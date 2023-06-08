package mao;

import mao.utils.MarkdownUtils;

/**
 * Project name(项目名称)：java报表_java导出Markdown
 * Package(包名): mao
 * Class(类名): Test3
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/8
 * Time(创建时间)： 13:39
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test3
{
    public static void main(String[] args)
    {
        String s = MarkdownUtils.of()
                .photo("图片", "https://bkimg.cdn.bcebos.com/pic/d009b3de9c82d158ccbf98bc1b430ed8bc3e" +
                        "b135e42e?x-bce-process=image/resize,m_lfit,w_440,limit_1")
                .photo("图片2", "https://pics0.baidu.com/feed/0ff41bd5ad6eddc49b0e4e404d077cfa50663380.jpeg@" +
                        "f_auto?token=a369c5afb4356baf7c521ae972f7e983&s=19C3336E2D621719085AD210020050C9")
                .br()
                .link("github", "https://github.com/maomao124")
                .link("b站", "https://www.bilibili.com/")
                .build();
        System.out.println(s);
        MarkdownUtils.write(s, "test3.md");
    }
}
