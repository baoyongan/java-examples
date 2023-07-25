-- sam_saleproject表增加字段product_type、cash_trans_in_flag、divd_cash_flag
prompt
prompt 检查 sam_saleproject 表 是否存在 product_type 字段, 不存在则增加......
prompt 检查 sam_saleproject 表 是否存在 cash_trans_in_flag 字段, 不存在则增加......
prompt 检查 sam_saleproject 表 是否存在 divd_cash_flag 字段, 不存在则增加......
declare v_rowcount number(10);
begin
    select count(1) into v_rowcount from user_tables where table_name = upper('sam_saleproject');
    if v_rowcount > 0 then
        select count(1) into v_rowcount from col where tname = upper('sam_saleproject') and cname = upper('product_type');
        if v_rowcount = 0 then
            execute immediate 'alter table hs_sam.sam_saleproject add product_type varchar2(3) default null null';
            execute immediate 'comment on column sam_saleproject.product_type is ''产品类型''';
        end if;
        select count(1) into v_rowcount from col where tname = upper('sam_saleproject') and cname = upper('cash_trans_in_flag');
        if v_rowcount = 0 then
            execute immediate 'alter table hs_sam.sam_saleproject add cash_trans_in_flag varchar2(3) default null null';
            execute immediate 'comment on column sam_saleproject.cash_trans_in_flag is ''是否可五行添益转入''';
        end if;
        select count(1) into v_rowcount from col where tname = upper('sam_saleproject') and cname = upper('divd_cash_flag');
        if v_rowcount = 0 then
            execute immediate 'alter table hs_sam.sam_saleproject add divd_cash_flag varchar2(3) default null null';
            execute immediate 'comment on column sam_saleproject.divd_cash_flag is ''是否分配到五行添益''';
        end if;
    end if;
end;