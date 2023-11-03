package com.example.tyc.sercive;

import com.example.tyc.pojo.QYSource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SourceService {

    //获取所有企业
    @Select("select * from qysource")
    List<QYSource> getqyData();

    //保存企业
    @Insert("INSERT INTO qysource (url, qymc, lsxzbgxf, lsbzxr, zjlarq,photo,lssxbzxr) VALUES (#{url}, #{qymc}, #{lsxzbgxf}, #{lsbzxr}, #{zjlarq},#{photo},#{lssxbzxr})")
    void saveDate(QYSource qySource);

    //判断企业是否存在
    @Select("select count(*) from qysource where url = {url} ")
    int existence(String url);


}
