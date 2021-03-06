-- drop database if exists purchase_sale_stock;

-- create database purchase_sale_stock;

mysql -uroot -proot

USE purchase_sale_stock;

CREATE TABLE `accounts` (
  `usrid` int(22) NOT NULL AUTO_INCREMENT,
  `usrname` varchar(30) NOT NULL,
  `region_department` int(4) NOT NULL COMMENT '地方区域部门,如0-15为常川分部,16-21为滨河分部',
  `competence` int(1) DEFAULT NULL COMMENT '岗位对应之权限,0:管理员,1:总经理,2:采购经理,3:销售经理,4:仓库主管,5普通雇员',
  `active_status` int(1) DEFAULT '1' COMMENT '激活状态 0-Sleep,1-Awake',
  `phone` char(30) NOT NULL COMMENT '1个号码至多只准关联3个账号',
  `reg_time` date NOT NULL,
  `modified_time` date NOT NULL COMMENT '上次更新时间',
  `password` varchar(50) NOT NULL,
  `salt` varchar(20) NOT NULL,
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usrname` (`usrname`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

SELECT * FROM accounts;

DESC accounts;

-- ======================================================

-- 修改
update purchase set is_agree=1,has_take_goods=1  where purchase_id<10;    

alter table accounts change reg_time reg_time date not null;

alter table accounts add password varchar(50) not null;

alter table accounts add salt varchar(20) not null;

alter table accounts ADD COLUMN modified_time date NOT NULL COMMENT '上次更新时间' AFTER reg_time;

alter table accounts modify COLUMN competence int(2) comment '权限,0系统后台管理,1主管,2采购经理,3销售经理';

show full COLUMNs FROM accounts;

alter table accounts change region_department region_department int(5) not null COMMENT '所属区域部门,如11为常川物流部';

-- 修改注释
ALTER TABLE accounts CHANGE region_department region_department int(3) NOT NULL
COMMENT '地区部门,0-滨河,1-上天院,2-鸣皋,3-焦王,4-申坡,5-遵王,6-常海山,7-老君堂,8-鸦岭,9-酒后,10-平等,11-夏堡,12-富留店';

-- 修改注释
ALTER TABLE purchase CHANGE is_enter_store is_enter_store int(1) NOT NULL DEFAULT '0'
COMMENT '是否已取货:0-未取货,1-已取货';

-- 修改注释
ALTER TABLE accounts CHANGE competence competence int(1) NOT NULL COMMENT '岗位类型,0:系统管理,1:总经理,2:采购经理,3:销售经理,4:仓库管理,5:普通雇员';

-- 修改字段名
ALTER TABLE purchase CHANGE is_enter_store has_take_goods int(1) NOT NULL DEFAULT '0'
COMMENT '是否已取货:0-未取货,1-已取货';

select count(usrid) from accounts where phone='181524007';

alter table accounts modify COLUMN competence int(1) comment '权限,0技术运维,1总经理,2采购经理,3销售经理,4仓库主管,5普通雇员';

update accounts set competence=1 where usrid in (4,9);

alter table accounts change competence competence int(2) not null;

SELECT usrname,competence FROM accounts;

SHOW CREATE TABLE accounts;

CREATE TABLE `accounts` (
  `usrid` int(22) NOT NULL AUTO_INCREMENT,
  `usrname` varchar(30) NOT NULL,
  `region_department` int(5) NOT NULL COMMENT '所属区域部门,如11为常川物流部',
  `competence` int(2) NOT NULL,
  `active_status` int(2) NOT NULL DEFAULT '1' COMMENT '激活状态 0:sleep,1:awake',
  `phone` char(30) NOT NULL COMMENT '1个号码至多只准关联3个账号',
  `reg_time` date NOT NULL,
  `modified_time` date NOT NULL COMMENT '上次更新时间',
  `password` varchar(50) NOT NULL,
  `salt` varchar(20) NOT NULL,
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usrname` (`usrname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8

ALTER TABLE accounts MODIFY COLUMN competence int(1) COMMENT '权限,0技术运维,1总经理,2采购经理,3销售经理,4仓库主管,5普通雇员';

-- -- -- -- -- -- 
CREATE TABLE `accounts` (
  `usrid` int(22) NOT NULL AUTO_INCREMENT,
  `usrname` varchar(30) NOT NULL,
  `region_department` int(5) NOT NULL COMMENT '所属区域部门,如11为常川物流部',
  `competence` int(1) DEFAULT NULL COMMENT '权限,0技术运维,1总经理,2采购经理,3销售经理,4仓库主管,5普通雇员',
  `active_status` int(2) NOT NULL DEFAULT '1' COMMENT '激活状态 0:sleep,1:awake',
  `phone` char(30) NOT NULL COMMENT '1个号码至多只准关联3个账号',
  `reg_time` date NOT NULL,
  `modified_time` date NOT NULL COMMENT '上次更新时间',
  `password` varchar(50) NOT NULL,
  `salt` varchar(20) NOT NULL,
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usrname` (`usrname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8

ALTER TABLE accounts MODIFY COLUMN active_status int(1) DEFAULT '1' COMMENT '激活状态 0-Sleep,1-Awake';

-- 
CREATE TABLE `accounts` (
  `usrid` int(22) NOT NULL AUTO_INCREMENT,
  `usrname` varchar(30) NOT NULL,
  `region_department` int(5) NOT NULL COMMENT '所属区域部门,如11为常川物流部',
  `competence` int(1) DEFAULT NULL COMMENT '权限,0技术运维,1总经理,2采购经理,3销售经理,4仓库主管,5普通雇员',
  `active_status` int(1) DEFAULT '1' COMMENT '激活状态 0-Sleep,1-Awake',
  `phone` char(30) NOT NULL COMMENT '1个号码至多只准关联3个账号',
  `reg_time` date NOT NULL,
  `modified_time` date NOT NULL COMMENT '上次更新时间',
  `password` varchar(50) NOT NULL,
  `salt` varchar(20) NOT NULL,
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usrname` (`usrname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--  -----------------------------------------------
SELECT usrid,usrname FROM accounts;

SELECT usrid,usrname,competence,active_status FROM accounts;

UPDATE accounts SET active_status=0 WHERE usrid IN(17,18,19);

SELECT salt FROM accounts WHERE usrid IN(16,17,18);

SELECT usrid,usrname FROM accounts ORDER BY usrid ASC;

SELECT usrname,password FROM accounts WHERE usrid=7;

--  按地区部门搜索
SELECT usrid,usrname,region_department,phone,competence,active_status,reg_time,modified_time FROM accounts WHERE region_department='111';

SELECT region_department FROM accounts ORDER BY region_department ASC;

--  按职权搜索
SELECT usrid,usrname,region_department,phone,competence,active_status,reg_time,modified_time FROM accounts WHERE competence='1';

--  按已激活/已注销
SELECT usrid,usrname,region_department,phone,competence,active_status,reg_time,modified_time FROM accounts WHERE active_status='1';

--  按名模糊查询
SELECT usrid,usrname,region_department,phone,competence,active_status,reg_time,modified_time FROM accounts WHERE usrname LIKE '%r%';

SELECT usrid,usrname,region_department,phone,competence,active_status,DATE_FORMAT(reg_time,'%Y年%m月%d日'),modified_time FROM accounts WHERE usrname LIKE '%r%';

--  修改字段注释
ALTER TABLE accounts MODIFY COLUMN `region_department` int(4) NOT NULL COMMENT '地方区域部门,如0-15为常川分部,16-21为滨河分部';

ALTER TABLE accounts MODIFY COLUMN `competence` int(1) DEFAULT NULL COMMENT '岗位对应之权限,0:管理员,1:总经理,2:采购经理,3:销售经理,4:仓库主管,5普通雇员';

SHOW CREATE TABLE accounts;

-- -------------------------------------------------------------------------------------

insert into purchase (commodity, is_agree,
      supplier, quantity, amount_money,
      payment_method, is_pay, is_enter_store,
      operator, purchase_time)
values ('单例设计模式',
    	1,
    	'java',
    	'120',
    	'100',
    	0,
    	1,
    	1,
    	'admin',
    	'2011-11-11 11:50:02');

-- 修改列注释
ALTER TABLE accounts MODIFY COLUMN `active_status` int(1) DEFAULT '1' COMMENT '激活状态 0-已注销,1-已激活';

ALTER TABLE accounts MODIFY COLUMN `reg_time` date NOT NULL COMMENT '帐号注册时间';

ALTER TABLE accounts MODIFY COLUMN `password` varchar(50) NOT NULL COMMENT '密码';
ALTER TABLE accounts MODIFY COLUMN `salt` varchar(20) NOT NULL COMMENT '盐值';

ALTER TABLE accounts MODIFY COLUMN `phone` char(30) NOT NULL COMMENT 
'电话号码,1个电话号码至多准许绑定1个账号';

select * from purchase where operator ='user333';

-- update
update purchase set
commodity='铁马秋风',supplier='大山芽',quantity='60',amount_money='100.22',
payment_method='3',is_enter_store='1',purchase_time='2011-12-18 00:15:25' where purchase_id=3;

-- 新增1列
alter table purchase add classify int(2) not null comment 
'货品分类:0-电器,1-食品,2-服装,3-日用品,4-饮品,5-其它';

-- 创表
CREATE TABLE `t_stock`(
  id BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  purchase_id INT(13) NOT NULL COMMENT '采购申请单ID',
  store_commodity varchar(45) NOT NULL COMMENT '存储货物之名',
  store_quantity MEDIUMINT NOT NULL comment '存储数量',
  unit_price DECIMAL NOT NULL comment '单价',
  stock_type_area TINYINT(2) NOT NULL comment '存储类型区域:0-电器区,1-食品区,2-服装区,3-日用品区,4-饮品区,5-混装区',
  stock_operator VARCHAR(30) NOT NULL comment '入库经办仓管',
  enter_stock_time TIMESTAMP NOT NULL comment '入库时间',
  remark CHAR(70) DEFAULT NULL comment '备注',
  agree_enter_stock TINYINT(1) comment '同意入库与否:0-否,1-可',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pid` (`purchase_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '货仓存储表';

-- 已取已批
select purchase_id from purchase where has_take_goods=1 and is_agree=1;  
+-------------+
| purchase_id |
+-------------+
| 11          |
| 12          |
| 13          |
| 14          |
| 15          |
| 16          |
| 17          |
| 18          |
| 19          |
+-------------+

-- 从上面结果集合中获得
purchase_sale_stock> select purchase_id pid from t_stock;
+-----+
| pid |
+-----+
| 11  |
| 12  |
| 13  |
| 14  |
| 17  |
| 19  |
+-----+

--ts集合可以说属于全集p,查找ts集合的补集(属于p,且不属于ts)
--差集操作
select purchase_id from purchase where purchase.purchase_id not in(select purchase_id from t_stock);

--差集操作改进
select purchase_id purchaseId from purchase where purchase.purchase_id not in
(select purchase_id from t_stock) and purchase.has_take_goods=1 and purchase.is_agree=1;

select * from purchase where purchase.purchase_id not in(select purchase_id from t_stock) 
and purchase.has_take_goods=1 and purchase.is_agree=1;

-- 更新
update purchase set is_agree=1,has_take_goods=1 where purchase_id>20 && purchase_id<30;  

-- 修改采进表注释
ALTER TABLE purchase MODIFY `classify` int(2) NOT NULL COMMENT '货品分类:0-电器,1-食品,2-服装,3-日用品,4-饮品,5-其它,6-玩具,7-家具,8-药品';

-- 修改字段注释
ALTER TABLE t_stock MODIFY `stock_type_area` tinyint(2) NOT NULL COMMENT 
'存储类型区域:0-电器区,1-食品区,2-服装区,3-日用品区,4-饮品区,5-混装区,6-家具区,7-玩具区,8-药品区';

ALTER TABLE t_stock MODIFY `stock_type_area` tinyint(2) NOT NULL COMMENT 
'存储类型区域:0-电器区,1-食品区,2-服装区,3-日用品区,4-饮品区,5-混装区,6-家具区,7-玩具区,8-药品区,9-仓库外临时区';

-- 修改字段注释
ALTER TABLE t_stock MODIFY `agree_enter_stock` tinyint(1) NOT NULL COMMENT '同意入库与否:0-否,1-可';

update t_stock set agree_enter_stock=1 WHERE id<30;

-- 新增1列,再修改
ALTER TABLE t_stock ADD lastest_modifier varchar(30) DEFAULT null comment '上次修改者';

ALTER TABLE t_stock MODIFY lastest_modifier varchar(30) comment '上次修改者';

-- 
ALTER TABLE t_stock ADD lastest_modified_time timestamp DEFAULT null comment '上次修改时间';

ALTER TABLE t_stock MODIFY lastest_modified_time timestamp comment '上次修改时间';

-- MODIFY修改字段类型和约束
ALTER TABLE t_stock MODIFY unit_price decimal(13,2) NOT NULL COMMENT '单价';

-- 修改字段类型和约束和注释
ALTER TABLE purchase MODIFY `is_agree` int(1) NOT NULL COMMENT '是否已获批:0-未获批,1-已获批,2-已阅被驳回';

ALTER TABLE accounts MODIFY `competence` int(1) NOT NULL COMMENT '岗位类型,0:技术管理,1:总经理,2:采购经理,3:销售经理,4:仓库管理,5:普通雇员';

ALTER TABLE accounts MODIFY `competence` int(1) NOT NULL COMMENT '岗位类型,0:技术管理,1:审查员,2:采购经理,3:销售经理,4:仓库管理,5:普通雇员';

ALTER TABLE t_approval MODIFY `approve_operates` tinyint(1) NOT NULL COMMENT '审批操作:0.不同意;1.已同意';

-- ..............

select classify,

count(purchase_id) as 数量

from purchase

group by classify order by classify asc;

+----------+------+
| classify | 数量 |
+----------+------+
| 0        | 8    |
| 1        | 9    |
| 2        | 3    |
| 3        | 8    |
| 4        | 7    |
| 5        | 6    |
| 6        | 5    |
| 7        | 4    |
| 8        | 2    |
+----------+------+


-- ..............

select classify,count(purchase_id) as num from purchase 
where is_agree=1 group by classify order by classify asc;  

+----------+-----+
| classify | num |
+----------+-----+
| 0        | 6   |
| 1        | 6   |
| 2        | 3   |
| 3        | 8   |
| 4        | 7   |
| 5        | 6   |
| 6        | 4   |
| 7        | 2   |
| 8        | 1   |
+----------+-----+

-- ..............

-- 改字段注释
ALTER TABLE accounts MODIFY `region_department` int(3) NOT NULL COMMENT  
'部门所处地区:0-滨河,1-上天院,2-鸣皋,3-焦王,4-申坡,5-遵王,6-常海山,7-老君堂,8-鸦岭,9-酒后,10-平等,11-夏堡,12-富留店';

-- 新增1列
alter table t_sale add surplus_demand int(11) not null comment '剩余需求量';

-- 新增1列
alter table t_sale add has_submitted_approval smallint(1) not null default 0 comment '是否已送审:0未送,1已送';

-- 新增1列
alter table t_sale add warehouse_goods_order bigint(20) not null comment '对应的仓储库货物序号';

-- 增加约束
alter table t_sale add unique(warehouse_goods_order);

-- 更新
update t_sale set warehouse_goods_order = 1 where id=1;
+----+-----------------------+
| id | warehouse_goods_order |
+----+-----------------------+
| 1  | 0                     |
| 2  | 0                     |
| 3  | 0                     |
| 4  | 0                     |
| 5  | 0                     |
| 6  | 0                     |
| 7  | 0                     |
| 8  | 0                     |
| 9  | 0                     |
| 10 | 0                     |
| 11 | 0                     |
| 12 | 0                     |
| 13 | 0                     |
| 14 | 0                     |
| 15 | 0                     |
| 16 | 0                     |
+----+-----------------------+


-- 修改
update t_sale set surplus_demand = 100 where id>0;

select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 0,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 1  | 111.11       |
| 2  | 250.14       |
| 3  | 360.14       |
+----+--------------+

select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 1,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 2  |  250.14      |
| 3  |  360.14      |
| 4  | 2540.78      |
+----+--------------+

select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 2,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 3  |  360.14      |
| 4  | 2540.78      |
| 5  | 1261.04      |
+----+--------------+

-- 翻页,第一页0,n
select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 0,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 1  | 111.11       |
| 2  | 250.14       |
| 3  | 360.14       |
+----+--------------+

-- 第二页n,n
select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 3,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 4  | 2540.78      |
| 5  | 1261.04      |
| 6  | 1261.04      |
+----+--------------+

-- 第三页2n,n
select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 6,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 7  |  252.14      |
| 8  | 2052.14      |
| 9  |  152.14      |
+----+--------------+

-- 第四页3n,n
select id,amount_money from t_sale ORDER BY sale_time,id ASC limit 9,3;
+----+--------------+
| id | amount_money |
+----+--------------+
| 10 | 15211.1      |
| 11 |   211.14     |
| 12 |  1211.4      |
+----+--------------+

-- 改注释
ALTER TABLE t_sale CHANGE is_enough_stock `is_enough_stock` smallint(1) NOT NULL 
COMMENT '是否有足够存货(0-无,1-少量,2-半数左右,3-勉强供应,4-完全满足)';

-- 新增1列
alter table t_out add has_approval_handle tinyint(1) not null comment 
'是否经过审批部处理,false-未经过,true-已经过';

-- 新增1列
alter table t_out add has_stock_handle tinyint(1) not null comment 
'是否经过仓管处理,false-未经过,true-已经过';

-- 创表
CREATE TABLE `t_out`
(
`id` int(22) NOT NULL AUTO_INCREMENT COMMENT '主键',

`store_commodity` varchar(45) NOT NULL COMMENT '货物物品之名',

`store_order` bigint(20) unsigned NOT NULL COMMENT '货品仓储主键',

`quantity` int(11) NOT NULL COMMENT '货物之数量',

`store_area` tinyint(2) NOT NULL COMMENT '原存储区域:0-电器区,1-食品区,2-服装区,3-日用品区,4-饮品区,5-混装区,6-家具区,7-玩具区,8-药品区,9-仓库外临时区',

`classify` int(2) NOT NULL COMMENT '货品类型:0-电器,1-食品,2-服装,3-日用品,4-饮品,5-其它,6-玩具,7-家具,8-药品',

`approver_is_agree` tinyint(1) NOT NULL COMMENT '审批部门人员是否同意,false-不准,true-准许',

`destination` int(3) NOT NULL COMMENT '目的地:0-滨河,1-上天院,2-鸣皋,3-焦王,4-申坡,5-遵王,6-常海山,7-老君堂,8-
鸦岭,9-酒后,10-平等,11-夏堡,12-富留店',

`sale_order` int(22) NOT NULL COMMENT '对应销售记录主键',

`stocker_is_agree` tinyint(1) NOT NULL COMMENT '仓管是否同意出库,false-不准,true-准许',

`out_time` timestamp NOT NULL COMMENT '出库时间',

`sale_operator` int(22) NOT NULL COMMENT '出库经办人,对应账号表usrid',

`applicant` int(22) NOT NULL COMMENT '提货申请者,对应账号表usrid',

`remarks` varchar(80) COMMENT '备注', 

PRIMARY KEY (`id`),

UNIQUE KEY `sale_order` (`sale_order`),

UNIQUE KEY `store_order` (`store_order`)  

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库记录表';

-- 分页查询
SELECT purchase_id from purchase WHERE is_agree=0 
ORDER BY amount_money,purchase_id ASC LIMIT pageNum*row,row;

-- 分页查询
SELECT purchase_id,is_agree from purchase WHERE is_agree=0  
ORDER BY amount_money,purchase_id ASC LIMIT 1,4;  

-- 移除唯一约束
ALTER TABLE t_out DROP INDEX sale_order;

ALTER TABLE t_sale DROP INDEX warehouse_goods_order;
