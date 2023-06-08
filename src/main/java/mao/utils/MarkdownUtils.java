package mao.utils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Project name(项目名称)：java报表_java导出Markdown
 * Package(包名): mao.utils
 * Class(类名): MarkdownUtils
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/8
 * Time(创建时间)： 13:09
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class MarkdownUtils
{

    /**
     * @return {@link SectionBuilder}
     */
    public static SectionBuilder of()
    {
        return new SectionBuilder(new Section(Section.Type.NORMAL, null, null, null, 0));
    }

    /**
     * 风格
     *
     * @date 2023/06/08
     */
    public enum Style
    {
        /**
         * 正常
         */
        NORMAL("normal"),
        /**
         * 大胆
         */
        BOLD("bold"),
        /**
         * 斜体
         */
        ITALIC("italic"),
        /**
         * 红色
         */
        RED("red"),
        /**
         * 绿色
         */
        GREEN("green"),
        /**
         * 灰色
         */
        GRAY("gray"),
        /**
         * 黄色
         */
        YELLOW("gold"),
        /**
         * 蓝色
         */
        BLUE("blue");

        /**
         * 名字
         */
        private final String name;

        /**
         * 风格
         *
         * @param name 名字
         */
        Style(String name)
        {
            this.name = name;
        }

        /**
         * 得到名字
         *
         * @return {@link String}
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * 字体
     *
     * @author mao
     * @date 2023/06/08
     */
    public static class Fonts
    {
        /**
         * 空
         */
        public static final Fonts EMPTY = Fonts.of("");
        /**
         * 文本
         */
        private final String text;

        /**
         * 风格
         */
        private Set<Style> styles = Collections.emptySet();

        /**
         * 字体
         *
         * @param text  文本
         * @param style 风格
         */
        private Fonts(String text, Style... style)
        {
            this.text = text != null ? text : "";
            if (style != null)
            {
                this.styles = new HashSet<>(Arrays.asList(style));
            }
        }


        /**
         * @param text 文本
         * @return {@link Fonts}
         */
        public static Fonts of(String text)
        {
            return new Fonts(text, Style.NORMAL);
        }

        /**
         * @param text  文本
         * @param style 风格
         * @return {@link Fonts}
         */
        public static Fonts of(String text, Style... style)
        {
            return new Fonts(text, style);
        }

        /**
         * 是空
         *
         * @return boolean
         */
        public boolean isEmpty()
        {
            return this.text == null || this.text.isEmpty();
        }

        /**
         * 字符串
         *
         * @return {@link String}
         */
        @Override
        public String toString()
        {
            if (styles.contains(Style.NORMAL))
            {
                return text;
            }
            String last = text;
            for (Style style : styles)
            {
                last = parseStyle(last, style);
            }
            return last;
        }

        /**
         * 解析风格
         *
         * @param text  文本
         * @param style 风格
         * @return {@link String}
         */
        private String parseStyle(String text, Style style)
        {
            if (text == null || style == null)
            {
                return text;
            }
            switch (style)
            {
                case NORMAL:
                    break;
                case BOLD:
                    return "**" + text + "**";
                case ITALIC:
                    return "*" + text + "*";
                case RED:
                case GREEN:
                case BLUE:
                case YELLOW:
                    return "<font color='" + style.getName() + "'>" + text + "</font>";
            }
            return text;
        }
    }

    /**
     * 代表一行，可以是一个普通文本或一个K-V(s)数据
     */
    public static class MetaData
    {

        /**
         * 默认分隔符
         */
        public static final String DEFAULT_SEPARATOR = "：";
        /**
         * 默认值分隔符
         */
        public static final String DEFAULT_VALUE_SEPARATOR = " | ";
        /**
         * 链接模板
         */
        public static final String LINK_TEMPLATE = "[%s](%s)";

        /**
         * 图片模板
         */
        public static final String PHOTO_TEMPLATE = "![%s](%s)";


        /**
         * 类型
         */
        private final Type type;
        /**
         * 文本
         */
        private final Fonts text;
        /**
         * 值
         */
        private final Collection<Fonts> values;
        /**
         * 分隔符
         */
        private final String separator = DEFAULT_SEPARATOR;
        /**
         * 值分隔符
         */
        private final String valueSeparator = DEFAULT_VALUE_SEPARATOR;

        /**
         * 元数据
         *
         * @param text 文本
         */
        public MetaData(Fonts text)
        {
            this(text, null);
        }

        /**
         * 元数据
         *
         * @param type 类型
         */
        public MetaData(Type type)
        {
            this(type, null, null);
        }

        /**
         * 元数据
         *
         * @param text   文本
         * @param values 值
         */
        public MetaData(Fonts text, Collection<Fonts> values)
        {
            this(Type.NORMAL, text, values);
        }

        /**
         * 元数据
         *
         * @param type   类型
         * @param text   文本
         * @param values 值
         */
        public MetaData(Type type, Fonts text, Collection<Fonts> values)
        {
            this.type = type;
            this.text = text;
            this.values = values;
        }

        /**
         * 字符串
         *
         * @return {@link String}
         */
        @Override
        public String toString()
        {
            return generateString(this.valueSeparator);
        }

        /**
         * 生成字符串
         * generate one line
         *
         * @param valueSeparator 值分隔符
         * @return {@link String}
         */
        private String generateString(String valueSeparator)
        {
            boolean hasValues = values != null && !values.isEmpty();
            boolean hasText = text != null && !text.isEmpty();
            StringJoiner joiner = new StringJoiner(valueSeparator);
            String ret = "";
            switch (type)
            {
                case NORMAL:
                    if (hasText && hasValues)
                    {
                        values.forEach(v -> joiner.add(v.toString()));
                        ret = text + separator + joiner;
                    }
                    else if (!hasText && hasValues)
                    {
                        values.forEach(v -> joiner.add(v.toString()));
                        ret = joiner.toString();
                    }
                    else if (hasText)
                    {
                        ret = text.toString();
                    }
                    break;
                case LINK:
                    if (hasText && hasValues)
                    {
                        Fonts fonts = values.stream().findFirst().orElse(null);
                        if (fonts == null)
                        {
                            break;
                        }
                        ret = String.format(LINK_TEMPLATE, text, fonts);
                    }
                    else if (!hasText && hasValues)
                    {
                        Fonts url = values.stream().findFirst().orElse(null);
                        if (url == null)
                        {
                            break;
                        }
                        ret = String.format(LINK_TEMPLATE, url, url);
                    }
                    else if (hasText)
                    {
                        ret = String.format(LINK_TEMPLATE, text, text);
                    }
                    break;
                case LINK_LIST:
                    if (hasText && hasValues)
                    {
                        ret = text + separator + generateLinkList(values);
                    }
                    else if (!hasText && hasValues)
                    {
                        ret = generateLinkList(values);
                    }
                    else if (hasText)
                    {
                        ret = String.format(LINK_TEMPLATE, text, text);
                    }
                    break;
                case PHOTO:
                    if (hasText && hasValues)
                    {
                        Fonts fonts = values.stream().findFirst().orElse(null);
                        if (fonts == null)
                        {
                            break;
                        }
                        ret = String.format(PHOTO_TEMPLATE, text, fonts);
                    }
                    else if (!hasText && hasValues)
                    {
                        Fonts url = values.stream().findFirst().orElse(null);
                        if (url == null)
                        {
                            break;
                        }
                        ret = String.format(PHOTO_TEMPLATE, url, url);
                    }
                    else if (hasText)
                    {
                        ret = String.format(PHOTO_TEMPLATE, text, text);
                    }
                    break;
                case BR:
                    ret = "\n<br>";
            }
            return ret;
        }


        /**
         * 生成链接列表
         *
         * @param values 值
         * @return {@link String}
         */
        private String generateLinkList(Collection<Fonts> values)
        {
            if (values == null || values.isEmpty())
            {
                return "";
            }
            Object[] valueArr = values.toArray();
            StringJoiner linkList = new StringJoiner(valueSeparator);
            for (int i = 0; i + 1 < valueArr.length; i += 2)
            {
                linkList.add(String.format(LINK_TEMPLATE, valueArr[i], valueArr[i + 1]));
            }
            boolean isPairNum = (valueArr.length % 2) == 0;
            if (!isPairNum)
            {
                String lastUrl = valueArr[valueArr.length - 1].toString();
                linkList.add(String.format(LINK_TEMPLATE, lastUrl, lastUrl));
            }
            return linkList.toString();
        }

        private enum Type
        {
            /**
             * 正常
             */
            NORMAL,
            /**
             * 链接
             */
            LINK,

            /**
             * 照片
             */
            PHOTO,
            /**
             * 链接列表
             */
            LINK_LIST,
            /**
             * br
             */
            BR,
        }
    }


    private static class Section
    {
        /**
         * 深度
         */
        private final int depth;
        /**
         * 类型
         */
        private Type type;
        /**
         * 数据
         */
        private Object data;
        /**
         * 父
         */
        private Section parent;
        /**
         * 孩子们
         */
        private List<Section> children;

        /**
         * 部分
         *
         * @param type     类型
         * @param data     数据
         * @param parent   父
         * @param children 孩子们
         * @param depth    深度
         */
        private Section(Type type, Object data, Section parent, List<Section> children, int depth)
        {
            this.type = type;
            this.data = data;
            this.parent = parent;
            this.children = children;
            this.depth = depth;
        }


        /**
         * 添加孩子
         *
         * @param child 孩子
         */
        public void addChild(Section child)
        {
            lazyInitChildren();
            children.add(child);
        }

        /**
         * 孩子是空
         *
         * @return boolean
         */
        public boolean childIsEmpty()
        {
            return children == null || children.isEmpty();
        }

        // ~ private methods

        /**
         * 解析
         *
         * @param latestData 最新数据
         * @return {@link StringBuilder}
         */
        private StringBuilder parse(StringBuilder latestData)
        {
            switch (type)
            {
                case LINK:
                case NORMAL:
                    latestData.append('\n').append(parseData(""));
                    return latestData;
                case BIG_TITLE:
                    latestData.append('\n').append(parseData("## "));
                    return latestData;
                case TITLE:
                    latestData.append('\n').append(parseData("### "));
                    return latestData;
                case SUBTITLE:
                    latestData.append('\n').append(parseData("#### "));
                    return latestData;
                case REF:
                    return parseRefSection(latestData);
                case CODE:
                    StringBuilder codeBlock = new StringBuilder(latestData.length() + 10);
                    codeBlock.append("\n```").append(latestData).append("\n```");
                    return codeBlock;
                case ORDER_LIST:
                    return parseOrderListSection(latestData);
                case UN_ORDER_LIST:
                    return parseUnOrderListSection(latestData);
                case TABLE:
                    return parseTableSection(latestData);
                case BR:
                    return latestData.append(parseData(""));
            }
            return latestData;
        }

        /**
         * 解析数据
         *
         * @param prefix 前缀
         * @return {@link String}
         */
        private String parseData(String prefix)
        {
            if (data == null)
            {
                return "";
            }
            return prefix + data;
        }

        /**
         * 解析参考部分
         *
         * @param latestData 最新数据
         * @return {@link StringBuilder}
         */
        private StringBuilder parseRefSection(StringBuilder latestData)
        {
            char[] chars = latestData.toString().toCharArray();
            if (chars.length <= 0)
            {
                return latestData;
            }
            StringBuilder data = new StringBuilder(chars.length * 2);
            if (chars[0] != '\n')
            {
                data.append("> ");
            }
            char last = 0;
            for (char c : chars)
            {
                if (last == '\n')
                {
                    data.append("> ");
                }
                data.append(c);
                last = c;
            }
            return data;
        }

        /**
         * 解析顺序列表部分
         *
         * @param latestData 最新数据
         * @return {@link StringBuilder}
         */
        private StringBuilder parseOrderListSection(StringBuilder latestData)
        {
            char[] chars = latestData.toString().toCharArray();
            if (chars.length <= 0)
            {
                return latestData;
            }
            StringBuilder data = new StringBuilder(chars.length * 2);
            String padding = String.join("", Collections.nCopies(depth * 4, " "));
            int order = 1;
            if (chars[0] != '\n')
            {
                data.append(padding).append(order++).append(". ");
            }
            char last = 0;
            for (char c : chars)
            {
                if (last == '\n' && c != '\n' && c != ' ')
                {
                    data.append(padding).append(order++).append(". ");
                }
                data.append(c);
                last = c;
            }
            return data;
        }

        /**
         * 解析联合国订单列表部分
         *
         * @param latestData 最新数据
         * @return {@link StringBuilder}
         */
        private StringBuilder parseUnOrderListSection(StringBuilder latestData)
        {
            char[] chars = latestData.toString().toCharArray();
            if (chars.length <= 0)
            {
                return latestData;
            }
            StringBuilder data = new StringBuilder(chars.length * 2);
            String padding = String.join("", Collections.nCopies(depth * 4, " "));
            if (chars[0] != '\n')
            {
                data.append(padding).append("- ");
            }
            char last = 0;
            for (char c : chars)
            {
                if (last == '\n' && c != '\n' && c != ' ')
                {
                    data.append(padding).append("- ");
                }
                data.append(c);
                last = c;
            }
            return data;
        }

        /**
         * 解析表部分
         *
         * @param latestData 最新数据
         * @return {@link StringBuilder}
         */
        private StringBuilder parseTableSection(StringBuilder latestData)
        {
            if (data != null)
            {
                Object[][] tableData = (Object[][]) data;
                if (tableData.length > 0 && tableData[0].length > 0)
                {
                    StringJoiner titles = new StringJoiner(" | "), extras = new StringJoiner(" | ");
                    for (Object t : tableData[0])
                    {
                        titles.add(t != null ? t.toString() : "");
                        extras.add("-");
                    }
                    latestData.append("\n\n").append(titles).append('\n').append(extras);
                    for (int i = 1; i < tableData.length; i++)
                    {
                        StringJoiner dataJoiner = new StringJoiner(" | ");
                        for (int j = 0; j < tableData[i].length; j++)
                        {
                            dataJoiner.add(tableData[i][j] != null ? tableData[i][j].toString() : "");
                        }
                        latestData.append('\n').append(dataJoiner);
                    }
                }
            }
            return latestData.append('\n');
        }

        /**
         * 延迟初始化孩子
         */
        private void lazyInitChildren()
        {
            if (children == null)
            {
                children = new ArrayList<>();
            }
        }


        /**
         * 得到类型
         *
         * @return {@link Type}
         */
        public Type getType()
        {
            return type;
        }

        /**
         * 设置类型
         *
         * @param type 类型
         */
        public void setType(Type type)
        {
            this.type = type;
        }

        /**
         * 得到数据
         *
         * @return {@link Object}
         */
        public Object getData()
        {
            return data;
        }

        /**
         * 设置数据
         *
         * @param data 数据
         */
        public void setData(Object data)
        {
            this.data = data;
        }

        /**
         * 得到父
         *
         * @return {@link Section}
         */
        public Section getParent()
        {
            return parent;
        }

        /**
         * 设置父
         *
         * @param parent 父
         */
        public void setParent(Section parent)
        {
            this.parent = parent;
        }

        /**
         * 得到孩子们
         *
         * @return {@link List}<{@link Section}>
         */
        public List<Section> getChildren()
        {
            return children;
        }

        /**
         * 设置孩子们
         *
         * @param children 孩子们
         */
        public void setChildren(List<Section> children)
        {
            this.children = children;
        }

        /**
         * 得到深度
         *
         * @return int
         */
        public int getDepth()
        {
            return depth;
        }

        private enum Type
        {
            /**
             * 正常
             */
            NORMAL,

            /**
             * 大标题
             */
            BIG_TITLE,

            /**
             * 标题
             */
            TITLE,

            /**
             * 副标题
             */
            SUBTITLE,

            /**
             * 裁判
             */
            REF,

            /**
             * 代码
             */
            CODE,

            /**
             * 表
             */
            TABLE,

            /**
             * 订单列表
             */
            ORDER_LIST,

            /**
             * 联合国订单列表
             */
            UN_ORDER_LIST,

            /**
             * 链接
             */
            LINK,

            /**
             * br
             */
            BR
        }
    }

    public static class SectionBuilder
    {
        /**
         * 解析器
         */
        private static final MdParser parser = new MdParser();
        /**
         * 坏蛋交会
         */
        private final Section curSec;
        /**
         * 父母交会
         */
        private Section parentSec;
        /**
         * 父母建设者
         */
        private SectionBuilder parentBuilder;

        /**
         * 部分施工
         *
         * @param curSec 坏蛋交会
         */
        private SectionBuilder(Section curSec)
        {
            this.curSec = curSec;
        }

        /**
         * 部分施工
         *
         * @param curSec        坏蛋交会
         * @param parentSec     父母交会
         * @param parentBuilder 父母建设者
         */
        private SectionBuilder(Section curSec, Section parentSec, SectionBuilder parentBuilder)
        {
            this.curSec = curSec;
            this.parentSec = parentSec;
            this.parentBuilder = parentBuilder;
        }


        /**
         * 文本
         *
         * @param text 文本
         * @return {@link SectionBuilder}
         */
        public SectionBuilder text(String text)
        {
            return text(text, (String) null);
        }

        /**
         * 文本
         *
         * @param name  名字
         * @param value 价值
         * @return {@link SectionBuilder}
         */
        public SectionBuilder text(String name, String value)
        {
            if (name != null)
            {
                Collection<Fonts> values
                        = value != null ? Collections.singletonList(Fonts.of(value)) : Collections.emptyList();
                curSec.addChild(new Section(Section.Type.NORMAL,
                        new MetaData(MetaData.Type.NORMAL, Fonts.of(name, (Style) null), values),
                        curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 文本
         *
         * @param text  文本
         * @param style 风格
         * @return {@link SectionBuilder}
         */
        public SectionBuilder text(String text, Style... style)
        {
            if (text != null)
            {
                curSec.addChild(new Section(Section.Type.NORMAL, new MetaData(Fonts.of(text, style)), curSec,
                        null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 文本
         *
         * @param values 值
         * @return {@link SectionBuilder}
         */
        public SectionBuilder text(Collection<String> values)
        {
            if (values != null && !values.isEmpty())
            {
                text(null, values);
            }
            return this;
        }

        /**
         * 文本
         *
         * @param name   名字
         * @param values 值
         * @return {@link SectionBuilder}
         */
        public SectionBuilder text(String name, Collection<String> values)
        {
            if (values == null || values.size() <= 0)
            {
                return text(name);
            }
            return text(name, null, values);
        }

        /**
         * 文本
         *
         * @param name       名字
         * @param valueStyle 价值风格
         * @param values     值
         * @return {@link SectionBuilder}
         */
        public SectionBuilder text(String name, Style valueStyle, Collection<String> values)
        {
            if (values == null || values.size() <= 0)
            {
                return text(name);
            }
            if (valueStyle == null)
            {
                valueStyle = Style.NORMAL;
            }
            List<Fonts> ele = new ArrayList<>(values.size());
            for (String value : values)
            {
                ele.add(Fonts.of(value, valueStyle));
            }
            curSec.addChild(new Section(Section.Type.NORMAL, new MetaData(Fonts.of(name), ele), curSec, null,
                    curSec.getDepth()));
            return this;
        }

        /**
         * 大标题
         *
         * @param title 标题
         * @return {@link SectionBuilder}
         */
        public SectionBuilder bigTitle(String title)
        {
            if (title != null && title.length() != 0)
            {
                curSec.addChild(new Section(Section.Type.BIG_TITLE, new MetaData(Fonts.of(title)), curSec,
                        null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 标题
         *
         * @param title 标题
         * @return {@link SectionBuilder}
         */
        public SectionBuilder title(String title)
        {
            return title(title, Style.NORMAL);
        }

        /**
         * 标题
         *
         * @param title 标题
         * @param color 颜色
         * @return {@link SectionBuilder}
         */
        public SectionBuilder title(String title, Style color)
        {
            if (title != null && title.length() != 0)
            {
                curSec.addChild(new Section(Section.Type.TITLE, new MetaData(Fonts.of(title, color)),
                        curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 标题
         *
         * @param title 标题
         * @param label 标签
         * @return {@link SectionBuilder}
         */
        public SectionBuilder title(String title, Fonts... label)
        {
            return title(title, null, label);
        }

        /**
         * 标题
         *
         * @param title      标题
         * @param titleColor 标题颜色
         * @param label      标签
         * @return {@link SectionBuilder}
         */
        public SectionBuilder title(String title, Style titleColor, Fonts... label)
        {
            if (title != null && title.length() != 0)
            {
                if (titleColor == null)
                {
                    titleColor = Style.NORMAL;
                }
                List<Fonts> labelList = label != null ? Arrays.asList(label) : Collections.emptyList();
                curSec.addChild(new Section(Section.Type.TITLE, new MetaData(Fonts.of(title, titleColor), labelList),
                        curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 子标题
         *
         * @param title 标题
         * @return {@link SectionBuilder}
         */
        public SectionBuilder subTitle(String title)
        {
            if (title != null && title.length() != 0)
            {
                curSec.addChild(new Section(Section.Type.SUBTITLE, new MetaData(Fonts.of(title)),
                        curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 裁判
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder ref()
        {
            Section refSection = new Section(Section.Type.REF, null, curSec, new ArrayList<>(), curSec.getDepth());
            curSec.addChild(refSection);
            return new SectionBuilder(refSection, curSec, this);
        }

        /**
         * 最终裁判
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder endRef()
        {
            return this.parentBuilder != null ? this.parentBuilder : this;
        }

        /**
         * 表
         *
         * @return {@link TableDataBuilder}
         */
        public TableDataBuilder table()
        {
            return new TableDataBuilder(curSec, this);
        }

        /**
         * 链接
         *
         * @param url url
         * @return {@link SectionBuilder}
         */
        public SectionBuilder link(String url)
        {
            return link(null, url);
        }

        /**
         * 链接
         *
         * @param name 名字
         * @param url  url
         * @return {@link SectionBuilder}
         */
        public SectionBuilder link(String name, String url)
        {
            if (name == null || name.length() == 0)
            {
                name = url;
            }
            if (url != null && url.length() != 0)
            {
                MetaData links = new MetaData(MetaData.Type.LINK, Fonts.of(name),
                        Collections.singletonList(Fonts.of(url)));
                curSec.addChild(new Section(Section.Type.NORMAL, links, curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 链接
         *
         * @param urlMappings url映射
         * @return {@link SectionBuilder}
         */
        public SectionBuilder links(Map<String, String> urlMappings)
        {
            return links(null, urlMappings);
        }

        /**
         * 链接
         *
         * @param name        名字
         * @param urlMappings url映射
         * @return {@link SectionBuilder}
         */
        public SectionBuilder links(String name, Map<String, String> urlMappings)
        {
            if (urlMappings != null && !urlMappings.isEmpty())
            {
                List<Fonts> serialUrlInfos = new ArrayList<>();
                for (Map.Entry<String, String> entry : urlMappings.entrySet())
                {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    serialUrlInfos.add(Fonts.of(key != null ? key : ""));
                    serialUrlInfos.add(Fonts.of(value != null ? value : ""));
                }
                Fonts wrappedName = name != null && name.length() != 0 ? Fonts.of(name) : Fonts.EMPTY;
                MetaData linksGroup = new MetaData(MetaData.Type.LINK_LIST, wrappedName, serialUrlInfos);
                curSec.addChild(new Section(Section.Type.NORMAL, linksGroup, curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 照片
         *
         * @param name 名字
         * @param url  url
         * @return {@link SectionBuilder}
         */
        public SectionBuilder photo(String name, String url)
        {
            if (name == null || name.length() == 0)
            {
                name = url;
            }
            if (url != null && url.length() != 0)
            {
                MetaData links = new MetaData(MetaData.Type.PHOTO, Fonts.of(name),
                        Collections.singletonList(Fonts.of(url)));
                curSec.addChild(new Section(Section.Type.NORMAL, links, curSec, null, curSec.getDepth()));
            }
            return this;
        }

        /**
         * 照片
         *
         * @param url url
         * @return {@link SectionBuilder}
         */
        public SectionBuilder photo(String url)
        {
            return photo(null, url);
        }


        /**
         * ol
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder ol()
        {
            int depth = (curSec.getType() == Section.Type.ORDER_LIST || curSec.getType() == Section.Type.UN_ORDER_LIST)
                    ? curSec.getDepth() + 1
                    : curSec.getDepth();
            Section OrderListSec = new Section(Section.Type.ORDER_LIST, null, curSec, new ArrayList<>(), depth);
            curSec.addChild(OrderListSec);
            return new SectionBuilder(OrderListSec, curSec, this);
        }

        /**
         * ol结束
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder endOl()
        {
            return this.parentBuilder != null ? this.parentBuilder : this;
        }

        /**
         * ul
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder ul()
        {
            int depth = (curSec.getType() == Section.Type.ORDER_LIST || curSec.getType() == Section.Type.UN_ORDER_LIST)
                    ? curSec.getDepth() + 1
                    : curSec.getDepth();
            Section unOrderListSec = new Section(Section.Type.UN_ORDER_LIST, null, curSec, new ArrayList<>(), depth);
            curSec.addChild(unOrderListSec);
            return new SectionBuilder(unOrderListSec, curSec, this);
        }

        /**
         * ul结束
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder endUl()
        {
            return this.parentBuilder != null ? this.parentBuilder : this;
        }

        /**
         * 代码
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder code()
        {
            Section codeSec = new Section(Section.Type.CODE, null, curSec, new ArrayList<>(), curSec.getDepth());
            curSec.addChild(codeSec);
            return new SectionBuilder(codeSec, curSec, this);
        }

        /**
         * 最终代码
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder endCode()
        {
            return this.parentBuilder != null ? this.parentBuilder : this;
        }

        /**
         * br
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder br()
        {
            curSec.addChild(new Section(Section.Type.BR, new MetaData(MetaData.Type.BR), parentSec, null,
                    curSec.getDepth()));
            return this;
        }

        /**
         * 构建
         *
         * @return {@link String}
         */
        public String build()
        {
            return parser.parse(curSec);
        }
    }

    public static class TableDataBuilder
    {
        /**
         * 父母交会
         */
        private final Section parentSec;
        /**
         * 父母建设者
         */
        private final SectionBuilder parentBuilder;
        /**
         * 表数据
         */
        private Object[][] tableData;

        /**
         * 表数据生成器
         *
         * @param parentSec     父母交会
         * @param parentBuilder 父母建设者
         */
        private TableDataBuilder(Section parentSec, SectionBuilder parentBuilder)
        {
            this.parentSec = parentSec;
            this.parentBuilder = parentBuilder;
        }


        /**
         * 数据
         *
         * @param table 表
         * @return {@link TableDataBuilder}
         */
        public TableDataBuilder data(Object[][] table)
        {
            if (table != null && table.length > 0 && table[0].length > 0)
            {
                tableData = table;
            }
            return this;
        }

        /**
         * 数据
         *
         * @param title 标题
         * @param data  数据
         * @return {@link TableDataBuilder}
         */
        public TableDataBuilder data(Object[] title, Object[][] data)
        {
            if (title == null && data != null)
            {
                return data(data);
            }
            if (data != null && data.length > 0 && data[0].length > 0)
            {
                int minCol = Math.min(title.length, data[0].length);
                tableData = new Object[data.length + 1][minCol];
                tableData[0] = Arrays.copyOfRange(title, 0, minCol);
                for (int i = 0; i < data.length; i++)
                {
                    tableData[i + 1] = Arrays.copyOfRange(data[i], 0, minCol);
                }
            }
            return this;
        }

        /**
         * 表格结束
         *
         * @return {@link SectionBuilder}
         */
        public SectionBuilder endTable()
        {
            parentSec.addChild(new Section(Section.Type.TABLE, tableData, parentSec, null, parentSec.getDepth()));
            return parentBuilder;
        }
    }

    private static class MdParser
    {

        /**
         * 解析
         *
         * @param sec 证券交易委员会
         * @return {@link String}
         */
        public String parse(Section sec)
        {
            Section root = findRoot(sec);
            return doParse(root, root).toString().trim();
        }

        /**
         * 找到根源
         *
         * @param sec 证券交易委员会
         * @return {@link Section}
         */
        private Section findRoot(Section sec)
        {
            if (sec.getParent() == null)
            {
                return sec;
            }
            return findRoot(sec.getParent());
        }

        /**
         * 做解析
         *
         * @param cur  坏蛋
         * @param root 根
         * @return {@link StringBuilder}
         */
        private StringBuilder doParse(Section cur, Section root)
        {
            if (cur == null)
            {
                return null;
            }
            if (cur.childIsEmpty())
            {
                return cur.parse(new StringBuilder());
            }
            StringBuilder childData = new StringBuilder();
            for (Section child : cur.getChildren())
            {
                StringBuilder part = doParse(child, root);
                if (part != null)
                {
                    childData.append(part);
                }
            }
            return cur.parse(childData).append(cur.getParent() == root ? '\n' : "");
        }
    }


    /**
     * 写入到磁盘
     *
     * @param str  str
     * @param path 路径
     */
    public static void write(String str, String path)
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8))
        {
            outputStreamWriter.write(str);
            outputStreamWriter.flush();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
