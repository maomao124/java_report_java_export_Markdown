package mao;

import mao.utils.MarkdownUtils;

/**
 * Project name(项目名称)：java报表_java导出Markdown
 * Package(包名): mao
 * Class(类名): Test5
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/8
 * Time(创建时间)： 14:22
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test5
{
    public static void main(String[] args)
    {
        String s = MarkdownUtils.of()
                .code("java")
                .text("/**\n" +
                        "         * 数据\n" +
                        "         *\n" +
                        "         * @param title 标题\n" +
                        "         * @param data  数据\n" +
                        "         * @return {@link TableDataBuilder}\n" +
                        "         */\n" +
                        "        public TableDataBuilder data(Object[] title, Object[][] data)\n" +
                        "        {\n" +
                        "            if (title == null && data != null)\n" +
                        "            {\n" +
                        "                return data(data);\n" +
                        "            }\n" +
                        "            if (data != null && data.length > 0 && data[0].length > 0)\n" +
                        "            {\n" +
                        "                int minCol = Math.min(title.length, data[0].length);\n" +
                        "                tableData = new Object[data.length + 1][minCol];\n" +
                        "                tableData[0] = Arrays.copyOfRange(title, 0, minCol);\n" +
                        "                for (int i = 0; i < data.length; i++)\n" +
                        "                {\n" +
                        "                    tableData[i + 1] = Arrays.copyOfRange(data[i], 0, minCol);\n" +
                        "                }\n" +
                        "            }\n" +
                        "            return this;\n" +
                        "        }")
                .endCode()

                .br()

                .code("c++")
                .text("void ShellSort(int* slist, int length)\n" +
                        "{\n" +
                        "    int temp;\n" +
                        "    //gap为增量，一般取长度的一般\n" +
                        "    int gap = length / 2;\n" +
                        "    //当增量小于1时结束排序\n" +
                        "    while (gap >= 1)\n" +
                        "    {\n" +
                        "        //最多分为gap个列表\n" +
                        "        for (int i = 0; i < gap; i++)\n" +
                        "        {\n" +
                        "            //下面的代码为一个简单的插入排序，只是插入排序的数组下标每次移动的不是1而是gap\n" +
                        "            for (int j = i+gap; j < length; j = j + gap)\n" +
                        "            {\n" +
                        "                if (slist[j] < slist[j-gap])\n" +
                        "                {\n" +
                        "                    int k = j - gap;\n" +
                        "                    int temp = slist[j];\n" +
                        "                    while (k >= 0 && slist[k] > temp)\n" +
                        "                    {\n" +
                        "                        slist[k + gap] = slist[k];\n" +
                        "                        k = k - gap;\n" +
                        "                    }\n" +
                        "                    slist[k+gap] = temp;\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "        //增量递减\n" +
                        "        gap = gap/ 2;\n" +
                        "    }\n" +
                        "}\n")
                .endCode()

                .br()

                .code("sql")
                .text("SET NAMES utf8mb4;\n" +
                        "SET FOREIGN_KEY_CHECKS = 0;\n" +
                        "\n" +
                        "-- ----------------------------\n" +
                        "-- Table structure for pay_order\n" +
                        "-- ----------------------------\n" +
                        "DROP TABLE IF EXISTS `pay_order`;\n" +
                        "CREATE TABLE `pay_order`  (\n" +
                        "  `ID` bigint NOT NULL,\n" +
                        "  `TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '?ۺ?֧???????',\n" +
                        "  `MERCHANT_ID` bigint NOT NULL COMMENT '?????̻?',\n" +
                        "  `STORE_ID` bigint NULL DEFAULT NULL COMMENT '?̻????ŵ',\n" +
                        "  `APP_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '????Ӧ?',\n" +
                        "  `PAY_CHANNEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ֧?????????',\n" +
                        "  `PAY_CHANNEL_MCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ?????̻?id',\n" +
                        "  `PAY_CHANNEL_MCH_APP_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ?????̻?Ӧ??id',\n" +
                        "  `PAY_CHANNEL_TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ?????????',\n" +
                        "  `CHANNEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?ۺ?֧?????',\n" +
                        "  `OUT_TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?̻??????',\n" +
                        "  `SUBJECT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '??Ʒ???',\n" +
                        "  `BODY` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '???????',\n" +
                        "  `CURRENCY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????CNY',\n" +
                        "  `TOTAL_AMOUNT` int NULL DEFAULT NULL COMMENT '?????ܽ??\uE8EC??λΪ?',\n" +
                        "  `OPTIONAL` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?û??Զ????Ĳ???,?̻??Զ????',\n" +
                        "  `ANALYSIS` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????ͳ?Ʒ?????????,?û??Զ??',\n" +
                        "  `EXTRA` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?ض?????????ʱ???????',\n" +
                        "  `TRADE_STATE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????״̬֧??״̬,0-????????,1-֧????(Ŀǰδʹ??),2-֧???ɹ?,3-ҵ??????????,4-?ر',\n" +
                        "  `ERROR_CODE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????֧?????',\n" +
                        "  `ERROR_MSG` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????֧????????Ϣ',\n" +
                        "  `DEVICE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?豸',\n" +
                        "  `CLIENT_IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?ͻ???IP',\n" +
                        "  `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '????ʱ?',\n" +
                        "  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '????ʱ?',\n" +
                        "  `EXPIRE_TIME` datetime NULL DEFAULT NULL COMMENT '????????ʱ?',\n" +
                        "  `PAY_SUCCESS_TIME` datetime NULL DEFAULT NULL COMMENT '֧???ɹ?ʱ?',\n" +
                        "  PRIMARY KEY (`ID`) USING BTREE,\n" +
                        "  UNIQUE INDEX `unique_TRADE_NO`(`TRADE_NO`) USING BTREE\n" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;\n" +
                        "\n" +
                        "SET FOREIGN_KEY_CHECKS = 1;")
                .endCode()

                .br()

                .code("html")
                .text("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0,user-scalable=no,minimal-ui\" />\n" +
                        "    <title>echarts图表报表</title>\n" +
                        "    <link rel=\"stylesheet\" href=\"css/charts_demo.css\">\n" +
                        "    <script src=\"js/vuejs-2.5.16.js\"></script>\n" +
                        "    <script src=\"js/axios-0.18.0.js\"></script>\n" +
                        "    <script src=\"js/echarts.min.js\"></script>\n" +
                        "\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div id=\"app\" class=\"my-box\">\n" +
                        "    <div class=\"my-box-left\">\n" +
                        "        <button @click=\"columnCharts\">统计各部门人数</button> <br/> <br/>\n" +
                        "        <button @click=\"lineCharts\">月份入职人数统计</button> <br/> <br/>\n" +
                        "        <button @click=\"pieCharts\">员工地方来源统计</button> <br/> <br/>\n" +
                        "        <button @click=\"mapCharts\">员工地方来源统计(地图)</button> <br/> <br/>\n" +
                        "    </div>\n" +
                        "    <div class=\"my-box-right\">\n" +
                        "        <div id=\"container\" style=\"height:450px;\"></div>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "<script>\n" +
                        "    new Vue({\n" +
                        "        el:\"#app\",\n" +
                        "        data:{},\n" +
                        "        methods: {\n" +
                        "            columnCharts() {\n" +
                        "\n" +
                        "            },\n" +
                        "            lineCharts(){\n" +
                        "\n" +
                        "            },\n" +
                        "            pieCharts(){\n" +
                        "\n" +
                        "            },\n" +
                        "            mapCharts(){\n" +
                        "\n" +
                        "            }\n" +
                        "        }\n" +
                        "    });\n" +
                        "</script>\n" +
                        "</html>")
                .endCode()
                .build();
        System.out.println(s);
        MarkdownUtils.write(s, "./test5.md");
    }
}
