
ALTER TABLE `fx_db`.`sop_file`   
	  CHANGE `fil_uri` `fil_uri` VARCHAR(300) CHARSET utf8 COLLATE utf8_bin NULL  COMMENT '存储地址';
	  
	  
ALTER TABLE `fx_db`.`sop_file` ADD COLUMN `interview_record_id` bigint(20) NULL COMMENT '关联访谈ID';
ALTER TABLE `fx_db`.`sop_file` ADD COLUMN `meeting_record_id` bigint(20) NULL COMMENT '关联会议ID';


alter table `fx_db`.`sop_interview_record` modify column result_reason varchar(50) COMMENT '原因';

alter table `fx_db`.`sop_meeting_record` modify column result_reason varchar(50) COMMENT '原因';

alter table `fx_db`.`sop_interview_record` modify column reason_other varchar(50) COMMENT '其他原因';

alter table `fx_db`.`sop_meeting_record` modify column reason_other varchar(50) COMMENT '其他原因';

UPDATE dict SET NAME = '人事尽职调查报告' WHERE NAME= '人力资源尽职调查报告';


UPDATE sop_project  SET business_type_code='TZ' WHERE business_type_code IS NULL;
