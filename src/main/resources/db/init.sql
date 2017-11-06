insert into p_config (ID, KEY1, VALUE1, REMARK, WHEN_CREATED)
values ('1', 'SYSTEM_NAME', '格事化门户系统', '系统名称', STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));
insert into p_config (ID, KEY1, VALUE1, REMARK, WHEN_CREATED)
  values ('2', 'COPYRIGHT', '2017-2018 lyy. All rights reserved.', '版权信息', STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));
insert into p_role (ID, NAME, TYPE, SCOPE, IS_STD_INFO, VERSION, WHEN_CREATED, WHEN_MODIFIED, CODE)
values (1, '系统管理员', '1', 'xx', '1', 1, STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'), 'SYS_SCHOOL');

insert into p_user (ID, USERNAME, PASSWORD, TRUENAME, SALT, TYPE, SEX, STATUS, EMAIL, MOBILE, ADDRESS, PHOTO_ID, DEPT_ID, VERSION, WHEN_CREATED, WHEN_MODIFIED)
values (1, 'admin', '3407e516120df1202a900c4113664e62d69950fc', '管理员', 'af0a010d9aaa4978', '1', '男', '1', null, null, null, null, null, 1, STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));
insert into p_role_perm (ROLE_ID, PERM_STRING) values (1, 'system:manage');
insert into p_user_role (USER_ID, ROLE_ID) values (1, 1);

-- 字典
insert into p_code_kind (ID, KIND_NAME, STATUS, DISPLAY_WIDTH, CODE_GROUP, WHEN_CREATED, WHEN_MODIFIED)
values ('20001', '性别', '1', null, '系统代码', STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));
insert into p_code_item (ID, ITEM_NAME, STATUS, ORDER_NO, KIND_ID, TRAN_CODE, PARENT_ID, WHEN_CREATED)
values ('200101', '男', '1', null, '20001', '1', '20001', STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));
insert into p_code_item (ID, ITEM_NAME, STATUS, ORDER_NO, KIND_ID, TRAN_CODE, PARENT_ID, WHEN_CREATED)
values ('2000102', '女', '1', null, '20001', '0', '20001', STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));

-- 部门
insert into p_dept (ID, NAME, NICK_NAME, CODE, TYPE, STATUS, ORDER_NUMBER, EMAIL, LXR, IS_JXDW, FATHER_ID, VERSION, WHEN_CREATED, WHEN_MODIFIED)
values (1, '公安局', '', '1', 'xy', '1', 1, '', '110', '1', null, 1, STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s'));

-- 初始化栏目

-- STR_TO_DATE('2001-4-2 15:3:28','%Y-%m-%d %H:%i:%s')
-- STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s')