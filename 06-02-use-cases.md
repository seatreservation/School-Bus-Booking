# Use cases

## 详述用例 Use case 1 用户订座

**范围** ：中大校车app


**级别** ：用户目标


**主要参与者** ：顾客


**涉众及关注点**：
- 顾客： 能够迅速地，便捷的查询到指定日期所有校车发车时间，及座位情况。可以对所需车次上的座位进行预定，或者修改或取消预订情况。最后在对预定的座位进行支付。
- 支付授权服务：希望接受到格式和协议正确的数字授权请求。希望准确计算对用户的应付款。

**前置条件** ：顾客必须先通过登陆账号，对所需的座位进行预定并支付相应金额。


**成功保证（后置条件）** ：存储销售信息。准确计算税金。更新账务和库存信息。记录提成、生成票据。记录支付授权的批准。

**主成功场景（或基本流程）** ：
- 1.用户通过账号登陆app。
- 2.用户通过线路信息了解各条线路情况
- 3.用户通过在线购票选择所需时间的班车车票，并且在线选座。
- 4.用户选完后确认订单，并且支付相应金额。


**扩展（或替代流程）**：
- *a. 用户在订座过程的任意时段意外退出小程序：
  1. 系统保存当前用户的订座状态。
  2. 用户重新进入系统后，系统恢复用户退出之前的界面信息。

  3. 用户仅能够在预订单中修改订座的状况。
 
- 4. 系统友情提醒再次确认订单无误。
 
- 5. 微信支付宝支付
  2. 用户点击支付，选择微信支付选项
  3. 系统调用微信支付接口，提示用户支付金额以及输入密码
  4. 用户确认金额无误支付订单，微信返回支付状态为支付成功，系统记录该在线支付，初始化该桌号的用餐状态






**技术与数据变元表**：
- 8. 可以支持微信、支付宝付款。

**发生频率** ：可能会不断地发生。



## 非正式用例
### Use Case 2.1 用户选座

**主成功场景**：用户选则日期，并选择当日所需方向的班车线路，并进行选择座位。



**用例图**：

![](https://note.youdao.com/yws/api/personal/file/90FC77EA4300400D8441F2FC7A100C9C?method=download&shareKey=8c4b0ca99567c222b20caa14bd8ab700)

**活动图**：

![](https://note.youdao.com/yws/api/personal/file/EDFF98980B2A4A4B902F2D2D7FD65500?method=download&shareKey=e65c713cf5b9d775fac76563a6052e2c)

      
### Use case 2.2 用户提交订单

**主成功场景**：用户选票完成后，便可以提交订单





**活动图**：

![](https://note.youdao.com/yws/api/personal/file/E018C614C3B04271A1CD8450070D25B0?method=download&shareKey=cf857d7a4ab053a9f5a86449a5a415b5)
        

    


        
  