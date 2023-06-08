```java
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
```

<br>
```c++
void ShellSort(int* slist, int length)
{
    int temp;
    //gap为增量，一般取长度的一般
    int gap = length / 2;
    //当增量小于1时结束排序
    while (gap >= 1)
    {
        //最多分为gap个列表
        for (int i = 0; i < gap; i++)
        {
            //下面的代码为一个简单的插入排序，只是插入排序的数组下标每次移动的不是1而是gap
            for (int j = i+gap; j < length; j = j + gap)
            {
                if (slist[j] < slist[j-gap])
                {
                    int k = j - gap;
                    int temp = slist[j];
                    while (k >= 0 && slist[k] > temp)
                    {
                        slist[k + gap] = slist[k];
                        k = k - gap;
                    }
                    slist[k+gap] = temp;
                }
            }
        }
        //增量递减
        gap = gap/ 2;
    }
}

```

<br>
```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order`  (
  `ID` bigint NOT NULL,
  `TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '?ۺ?֧???????',
  `MERCHANT_ID` bigint NOT NULL COMMENT '?????̻?',
  `STORE_ID` bigint NULL DEFAULT NULL COMMENT '?̻????ŵ',
  `APP_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '????Ӧ?',
  `PAY_CHANNEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ֧?????????',
  `PAY_CHANNEL_MCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ?????̻?id',
  `PAY_CHANNEL_MCH_APP_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ?????̻?Ӧ??id',
  `PAY_CHANNEL_TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ԭʼ?????????',
  `CHANNEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?ۺ?֧?????',
  `OUT_TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?̻??????',
  `SUBJECT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '??Ʒ???',
  `BODY` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '???????',
  `CURRENCY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????CNY',
  `TOTAL_AMOUNT` int NULL DEFAULT NULL COMMENT '?????ܽ????λΪ?',
  `OPTIONAL` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?û??Զ????Ĳ???,?̻??Զ????',
  `ANALYSIS` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????ͳ?Ʒ?????????,?û??Զ??',
  `EXTRA` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?ض?????????ʱ???????',
  `TRADE_STATE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????״̬֧??״̬,0-????????,1-֧????(Ŀǰδʹ??),2-֧???ɹ?,3-ҵ??????????,4-?ر',
  `ERROR_CODE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????֧?????',
  `ERROR_MSG` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '????֧????????Ϣ',
  `DEVICE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?豸',
  `CLIENT_IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '?ͻ???IP',
  `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '????ʱ?',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '????ʱ?',
  `EXPIRE_TIME` datetime NULL DEFAULT NULL COMMENT '????????ʱ?',
  `PAY_SUCCESS_TIME` datetime NULL DEFAULT NULL COMMENT '֧???ɹ?ʱ?',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `unique_TRADE_NO`(`TRADE_NO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
```

<br>
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no,minimal-ui" />
    <title>echarts图表报表</title>
    <link rel="stylesheet" href="css/charts_demo.css">
    <script src="js/vuejs-2.5.16.js"></script>
    <script src="js/axios-0.18.0.js"></script>
    <script src="js/echarts.min.js"></script>

</head>
<body>
<div id="app" class="my-box">
    <div class="my-box-left">
        <button @click="columnCharts">统计各部门人数</button> <br/> <br/>
        <button @click="lineCharts">月份入职人数统计</button> <br/> <br/>
        <button @click="pieCharts">员工地方来源统计</button> <br/> <br/>
        <button @click="mapCharts">员工地方来源统计(地图)</button> <br/> <br/>
    </div>
    <div class="my-box-right">
        <div id="container" style="height:450px;"></div>
    </div>
</div>
</body>
<script>
    new Vue({
        el:"#app",
        data:{},
        methods: {
            columnCharts() {

            },
            lineCharts(){

            },
            pieCharts(){

            },
            mapCharts(){

            }
        }
    });
</script>
</html>
```